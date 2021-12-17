package com.coinspy.telegram.thread;

import org.springframework.stereotype.Component;

@Component
public class RunnableUniswapV3 extends RunnableComponents implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(1000000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
