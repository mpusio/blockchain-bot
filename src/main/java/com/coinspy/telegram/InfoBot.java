package com.coinspy.telegram;

import com.coinspy.controller.UserController;
import com.coinspy.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.groupadministration.CreateChatInviteLink;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.ChatInviteLink;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class InfoBot extends TelegramLongPollingBot {
    private final String botApiKey;
    private final String botName;
    private final UserController userController;
    private final Logger logger = LoggerFactory.getLogger(InfoBot.class);

    @Autowired
    public InfoBot(@Value("${telegram.info_bot.apikey}") String botApiKey,
                   @Value("${telegram.info_bot.name}") String botName, UserController userController) {
        this.botApiKey = botApiKey;
        this.botName = botName;
        this.userController = userController;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botApiKey;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(!update.getMessage().getNewChatMembers().isEmpty())
            addUsersToDatabase(update.getMessage().getNewChatMembers(), update.getMessage().getChat().getId());

        if (update.getMessage().hasText()){
            if(update.getMessage().getText().equals("/start")) start(update);
            if(update.getMessage().getText().equals("/buy")) buy(update);
            if(update.getMessage().getText().equals("/subscription")) subscription(update);
            if(update.getMessage().getText().equals("/website")) website(update);
            if(update.getMessage().getText().equals("/guide")) guide(update);
        }
    }

    private void addUsersToDatabase(List<User> users, Long groupId){
        LocalDateTime until = LocalDateTime.now().plusDays(30);

        users.forEach(user -> {
            ResponseEntity<UserEntity> userEntity = userController.getUsersByTelegramUserId(String.valueOf(user.getId()));

            if (userEntity.getStatusCodeValue() == HttpStatus.OK.value()) {
                Map<Long, LocalDateTime> channels = Objects.requireNonNull(userEntity.getBody()).getGroups();
                channels.put(groupId, until);
                userController.updateUserChannels(new com.coinspy.dto.User(String.valueOf(user.getId()), channels));
                logger.info(String.format("User %s prolong subscription!", user.getId()));
            }
            if (userEntity.getStatusCodeValue() == HttpStatus.NOT_FOUND.value()) {
                userController.addUser(new com.coinspy.dto.User(String.valueOf(user.getId()), Map.of(groupId, until)));
                logger.info(String.format("User %s was added!", user.getId()));
            }
        });
    }

    private void start(Update update){
        String message_text =
                        "Hi!  \n" +
                        "I am Spycoin Bot. Below you will see available commands to use,\n" +
                        "Just click or write them at this chat: \n" +
                        "/buy - buy subscription \n" +
                        "/subscription - check your subscribed channels and time access \n" +
                        "/website - open our website \n" +
                        "/guide - guide how to analyze data from tools";

        SendMessage sendMessage = new SendMessage(String.valueOf(update.getMessage().getChatId()), message_text);
        send(sendMessage);
    }

    private void guide(Update update) {
        String message_text = "Soon.";
        SendMessage sendMessage = new SendMessage(String.valueOf(update.getMessage().getChatId()), message_text);
        send(sendMessage);
    }

    private void website(Update update){
        String message_text = "Click below for website.";
        SendMessage sendMessage = new SendMessage(String.valueOf(update.getMessage().getChatId()), message_text);
        addButtons(sendMessage, Map.of("Website", "https://google.com"));
        send(sendMessage);
    }

    private void buy(Update update){
        String message_text = "Click below for buy.";
        SendMessage sendMessage = new SendMessage(String.valueOf(update.getMessage().getChatId()), message_text);
        addButtons(sendMessage, Map.of("Buy here", "https://google.com"));
        send(sendMessage);
    }

    private void subscription(Update update){
        StringBuilder message_text = new StringBuilder();
        Long id = update.getMessage().getFrom().getId();
        ResponseEntity<UserEntity> userEntity = userController.getUsersByTelegramUserId(String.valueOf(id));

        if (userEntity.getStatusCodeValue() == HttpStatus.OK.value()) {
            Map<Long, LocalDateTime> channels = userEntity.getBody().getGroups();
            if (channels.isEmpty())
                message_text.append("Right now you have not any subscription.");
            else {
                channels.forEach((k, v) -> {
                    Duration duration = Duration.between(LocalDateTime.now(), v);
                    message_text
                            .append("For channel ")
                            .append(Group.findById(String.valueOf(k)))
                            .append(" remain ")
                            .append(duration.toDays()).append(" days \n");
                });
            }
        }
        else message_text.append("You have never had a subscription before.");

        SendMessage sendMessage = new SendMessage(String.valueOf(update.getMessage().getChatId()), message_text.toString());
        send(sendMessage);
    }

    private String createOneTimePadJoinLink(String id){
        LocalDateTime time = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        long epoch = time.atZone(zoneId).toEpochSecond();

        CreateChatInviteLink inviteLink = CreateChatInviteLink.builder()
                .chatId(id)
                .memberLimit(1)
                .expireDate(Math.toIntExact(epoch))
                .build();

        ChatInviteLink chat = null;
        try {
            chat = this.execute(inviteLink);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        return chat.getInviteLink();
    }

    private void send(SendMessage sendMessage){
        try {
            this.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void addButtons(SendMessage sendMessage, Map<String, String> nameUrl) {
        var markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        AtomicInteger i = new AtomicInteger();
        nameUrl.forEach((k,v) -> {
            var inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText(k);
            inlineKeyboardButton.setCallbackData("");
            inlineKeyboardButton.setUrl(v);
            rowInline.add(i.get(), inlineKeyboardButton);
            rowsInline.add(rowInline);
            i.getAndIncrement();
        });

        markupInline.setKeyboard(rowsInline);
        sendMessage.setReplyMarkup(markupInline);
    }
}
