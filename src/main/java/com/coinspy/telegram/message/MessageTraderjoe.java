package com.coinspy.telegram.message;

import com.coinspy.token.Token;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Map;

public class MessageTraderjoe extends MessageComponents implements Message {

    private final Token token;

    public MessageTraderjoe(Token token) {
        this.token = token;
    }

    @Override
    public SendMessage parseToSendMessage(String chatId) {
        return parse(buildTextMessage(), chatId);
    }

    @Override
    public String buildTextMessage(){
        return MessageCreator.MessageCreatorBuilder.builder(token)
                .title("New coin at Traderjoe!")
                .tokenInfo()
                .socialMedia()
                .liquidityPool()
                .charts(Map.of("Dexscreener: ", dexscreenerAvalancheUrl + token.getLiquidityPair().getPairAddress()))
                .dex(traderjoeBuyUrl + token.getAddress(),
                        traderjoeSellUrl + token.getAddress(),
                        traderjoeInfoUrl + token.getAddress())
                .explorer(Map.of(
                        "Token contract: ", snowtraceContractCodeUrl + token.getAddress() + "#code",
                        "Token hodlers: ", snowtraceBalancesUrl + token.getAddress() + "#balances",
                        "Similar contracts: ", snowtraceSimilarContracts + token.getAddress()))
                .security()
                .build();
    }
}
