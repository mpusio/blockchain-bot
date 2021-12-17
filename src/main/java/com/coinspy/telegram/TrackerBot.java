package com.coinspy.telegram;

import com.coinspy.telegram.thread.ThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.EnumMap;
import java.util.Map;

@Component
public class TrackerBot extends TelegramLongPollingBot {

    private final String botApiKey;
    private final String botName;
    private final ThreadFactory threadFactory;
    private final Map<Dex, Thread> threads = new EnumMap<>(Dex.class);
    private final Logger logger = LoggerFactory.getLogger(TrackerBot.class);


    @Autowired
    public TrackerBot(@Value("${telegram.bot.apikey}") String botApiKey,
                      @Value("${telegram.bot.name}") String botName,
                      ThreadFactory threadFactory) {
        this.botApiKey = botApiKey;
        this.botName = botName;
        this.threadFactory = threadFactory;
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
    public void onRegister() {
        threads.put(Dex.UniSwapV2, threadFactory.createThread(Dex.UniSwapV2));
        threads.put(Dex.UniSwapV3, threadFactory.createThread(Dex.UniSwapV3));
        threads.put(Dex.SushiSwapV2, threadFactory.createThread(Dex.SushiSwapV2));
        threads.put(Dex.PancakeSwapV2, threadFactory.createThread(Dex.PancakeSwapV2));
        threads.put(Dex.QuickSwapV2, threadFactory.createThread(Dex.QuickSwapV2));
        threads.put(Dex.SpookySwap, threadFactory.createThread(Dex.SpookySwap));
        threads.put(Dex.SpiritSwap, threadFactory.createThread(Dex.SpiritSwap));
        threads.put(Dex.TraderJoe, threadFactory.createThread(Dex.TraderJoe));
        threads.put(Dex.Pangolin, threadFactory.createThread(Dex.Pangolin));
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            if(update.getMessage().getText().equals("/runAll")) {
                threads.get(Dex.UniSwapV2).start();
                threads.get(Dex.UniSwapV3).start();
                threads.get(Dex.SushiSwapV2).start();
                threads.get(Dex.PancakeSwapV2).start();
                threads.get(Dex.QuickSwapV2).start();
                logger.info("Started all test");
            }
            if(update.getMessage().getText().equals("/runUniSwapV2Test")) {
                threads.get(Dex.UniSwapV2).start();
                logger.info("Started uniswapV2 test");
            }
            if(update.getMessage().getText().equals("/runUniSwapV3Test")) {
                threads.get(Dex.UniSwapV3).start();
                logger.info("Started uniswapV3 test");
            }
            if(update.getMessage().getText().equals("/runSushiSwapV2Test")) {
                threads.get(Dex.SushiSwapV2).start();
                logger.info("Started sushiswapV2 test");
            }
            if(update.getMessage().getText().equals("/runPancakeSwapV2Test")) {
                threads.get(Dex.PancakeSwapV2).start();
                logger.info("Started pancakeswapV2 test");
            }
            if(update.getMessage().getText().equals("/runQuickSwapV2Test")) {
                threads.get(Dex.QuickSwapV2).start();
                logger.info("Started quickswapV2 test");
            }
            if(update.getMessage().getText().equals("/runSpookySwapV2Test")) {
                threads.get(Dex.SpookySwap).start();
                logger.info("Started spookyswap test");
            }
            if(update.getMessage().getText().equals("/runSpiritSwapV2Test")) {
                threads.get(Dex.SpiritSwap).start();
                logger.info("Started spiritswap test");
            }
            if(update.getMessage().getText().equals("/runTraderJoeV2Test")) {
                threads.get(Dex.TraderJoe).start();
                logger.info("Started traderjoe test");
            }
            if(update.getMessage().getText().equals("/runPangolinV2Test")) {
                threads.get(Dex.Pangolin).start();
                logger.info("Started pangolin test");
            }
        }
    }
}
