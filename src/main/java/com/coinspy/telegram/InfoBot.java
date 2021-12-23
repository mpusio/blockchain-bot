package com.coinspy.telegram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.groupadministration.CreateChatInviteLink;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.ChatInviteLink;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class InfoBot extends TelegramLongPollingBot {
    private final String botApiKey;
    private final String botName;
    private final Logger logger = LoggerFactory.getLogger(InfoBot.class);

    @Autowired
    public InfoBot(@Value("${telegram.info_bot.apikey}") String botApiKey,
                   @Value("${telegram.info_bot.name}") String botName) {
        this.botApiKey = botApiKey;
        this.botName = botName;
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
        if (update.getMessage().hasText()){
            if(update.getMessage().getText().equals("/start")) start(update);
            if(update.getMessage().getText().equals("/buy")) buy(update);
            if(update.getMessage().getText().equals("/website")) website(update);
            if(update.getMessage().getText().equals("/guide")) guide(update);
        }
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
