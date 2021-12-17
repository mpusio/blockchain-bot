package com.coinspy.telegram;

import com.coinspy.token.Pair;
import com.coinspy.token.Token;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void checkTest() {
        String typeOfDEX = "Uniswap";

        Map<String, String> urls = new HashMap<>();

        //Social media
        urls.put("twitterName", "");
        urls.put("redditName", "");
        urls.put("4chanName", "");
        urls.put("twitterSymbol", "");
        urls.put("redditSymbol", "");
        urls.put("4chanSymbol", "");

        //DEX
        urls.put("dexBuy", "");
        urls.put("dexSell", "");
        urls.put("dexInfo", "");

        //Explorer
        urls.put("explorerContract", "");
        urls.put("explorerHolders", "");

        //Charts
        urls.put("chart1", "");
        urls.put("chart2", "");


//        Token token = new Token.TokenBuilder()
//                .tokenName("Uniqly")
//                .tokenSymbol("UNIQ")
//                .address("0x3758e00b100876c854636ef8db61988931bb8025")
//                .totalSupply("13000000000000000000000000")
//                .tokenDecimal("18")
//                .liquidityPair(new Pair("0xc447aaa230af55b2eaa75227521768914ec590aa",
//                                        "0x3758e00b100876c854636ef8db61988931bb8025",
//                                        "UNIQ",
//                                        "420000",
//                                        "0xc02aaa39b223fe8d0a0e5c4f27ead9083c756cc2",
//                                        "WETH",
//                                        "69"))
//                .build();


//        Message message = new Message(typeOfDEX, token, urls);
    }

}