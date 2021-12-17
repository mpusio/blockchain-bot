package com.coinspy.telegram.thread;

import com.coinspy.controller.ExplorerController;
import com.coinspy.telegram.Dex;
import com.coinspy.telegram.TrackerBot;
import com.coinspy.telegram.message.MessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class RunnablePangolin extends RunnableComponents implements Runnable{

    private final ExplorerController controller;
    private final TrackerBot telegramBot;
    private final MessageFactory messageFactory;
    private final String channelId;

    @Autowired
    public RunnablePangolin(ExplorerController controller,
                             @Lazy TrackerBot telegramBot,
                             MessageFactory messageFactory,
                             @Value("${telegram.channel.avalanche.id}") String channelId) {
        this.controller = controller;
        this.telegramBot = telegramBot;
        this.messageFactory = messageFactory;
        this.channelId = channelId;
    }

    @Override
    public void run() {
        var blockNumber = controller.getLastBlockNumber(Dex.Pangolin);
        while (!Thread.currentThread().isInterrupted()){
            var tokens = controller.getLastPairCreated(Dex.Pangolin, blockNumber);
            if (!tokens.isEmpty()) botExecuteTask(messageFactory, telegramBot, tokens, channelId, Dex.Pangolin);
            blockNumber = getLastBlockNumber(tokens, blockNumber);
            sleepThread(3000);
        }
    }
}
