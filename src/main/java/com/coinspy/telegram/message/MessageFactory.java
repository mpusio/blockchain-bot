package com.coinspy.telegram.message;

import com.coinspy.telegram.Dex;
import com.coinspy.token.Token;
import org.springframework.stereotype.Component;

@Component
public class MessageFactory {

    public Message createMessage(Dex typeOfDex, Token token){
        if (typeOfDex.equals(Dex.UniSwapV2)) return new MessageUniswap(token);
        if (typeOfDex.equals(Dex.UniSwapV3)) return new MessageUniswap(token);
        if (typeOfDex.equals(Dex.SushiSwapV2)) return new MessageSushiswap(token);
        if (typeOfDex.equals(Dex.PancakeSwapV2)) return new MessagePancakeswap(token);
        if (typeOfDex.equals(Dex.QuickSwapV2)) return new MessageQuickswap(token);
        if (typeOfDex.equals(Dex.SpookySwap)) return new MessageSpookyswap(token);
        if (typeOfDex.equals(Dex.SpiritSwap)) return new MessageSpiritswap(token);
        if (typeOfDex.equals(Dex.TraderJoe)) return new MessageTraderjoe(token);
        if (typeOfDex.equals(Dex.Pangolin)) return new MessagePangolin(token);

        throw new IllegalArgumentException("Cannot create message from this parameters");
    }
}
