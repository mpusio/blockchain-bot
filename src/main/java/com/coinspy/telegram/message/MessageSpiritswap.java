package com.coinspy.telegram.message;

import com.coinspy.token.Token;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Map;

public class MessageSpiritswap extends MessageComponents implements Message {

    private final Token token;

    public MessageSpiritswap(Token token) {
        this.token = token;
    }

    @Override
    public SendMessage parseToSendMessage(String chatId) {
        return parse(buildTextMessage(), chatId);
    }

    @Override
    public String buildTextMessage(){
        return MessageCreator.MessageCreatorBuilder.builder(token)
                .title("New coin at Spookyswap!")
                .tokenInfo()
                .socialMedia()
                .liquidityPool()
                .charts(Map.of("Dexscreener: ", dexscreenerFantomUrl + token.getLiquidityPair().getPairAddress()))
                .dex(spiritBuyUrl + token.getAddress(),
                        spiritSellUrl + token.getAddress(),
                        spiritInfoUrl + token.getLiquidityPair().getPairAddress())
                .explorer(Map.of(
                        "Token contract: ", ftmscanContractCodeUrl + token.getAddress() + "#code",
                        "Token hodlers: ", ftmscanBalancesUrl + token.getAddress() + "#balances",
                        "Similar contracts: ", ftmscanSimilarContracts + token.getAddress()))
                .security()
                .build();
    }
}
