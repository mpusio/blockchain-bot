package com.coinspy.telegram.thread;

import com.coinspy.telegram.Dex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ThreadFactory {

    @Autowired
    RunnableUniswapV2 runnableUniswapV2;

    @Autowired
    RunnableUniswapV3 runnableUniswapV3;

    @Autowired
    RunnableSushiswapV2 runnableSushiswapV2;

    @Autowired
    RunnablePancakeswapV2 runnablePancakeswapV2;

    @Autowired
    RunnableQuickswapV2 runnableQuickswapV2;

    @Autowired
    RunnableSpookyswap runnableSpookyswap;

    @Autowired
    RunnableSpiritswap runnableSpiritswap;

    @Autowired
    RunnableTraderjoe runnableTraderjoe;

    @Autowired
    RunnablePangolin runnablePangolin;

    public Thread createThread(Dex typeOfDex){
        if (typeOfDex.equals(Dex.UniSwapV2)) return new Thread(runnableUniswapV2);

        if (typeOfDex.equals(Dex.UniSwapV3)) return new Thread(runnableUniswapV3);

        if (typeOfDex.equals(Dex.SushiSwapV2)) return new Thread(runnableSushiswapV2);

        if (typeOfDex.equals(Dex.PancakeSwapV2)) return new Thread(runnablePancakeswapV2);

        if (typeOfDex.equals(Dex.QuickSwapV2)) return new Thread(runnableQuickswapV2);

        if (typeOfDex.equals(Dex.SpookySwap)) return new Thread(runnableSpookyswap);

        if (typeOfDex.equals(Dex.SpiritSwap)) return new Thread(runnableSpiritswap);

        if (typeOfDex.equals(Dex.TraderJoe)) return new Thread(runnableTraderjoe);

        if (typeOfDex.equals(Dex.Pangolin)) return new Thread(runnablePangolin);

        throw new IllegalArgumentException("Cannot create message from this parameters");
    }
}
