package com.coinspy.telegram.message;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface Message {
    SendMessage parseToSendMessage(String chatId);
    String buildTextMessage();
}
