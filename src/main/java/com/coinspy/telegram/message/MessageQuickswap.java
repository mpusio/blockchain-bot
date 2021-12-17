package com.coinspy.telegram.message;

import com.coinspy.token.Token;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Map;

public class MessageQuickswap extends MessageComponents implements Message {

    private final Token token;

    public MessageQuickswap(Token token) {
        this.token = token;
    }

    @Override
    public SendMessage parseToSendMessage(String chatId) {
        return parse(buildTextMessage(), chatId);
    }

    @Override
    public String buildTextMessage(){
        return MessageCreator.MessageCreatorBuilder.builder(token)
                .title("New coin at Quickswap!")
                .tokenInfo()
                .socialMedia()
                .liquidityPool()
                .charts(Map.of("Poocoin: ", poocoinPolygonChart + token.getAddress(),
                        "Dexguru: ", dexGuruUrl + token.getAddress() + "-polygon"))
                .dex(quickswapBuyUrl + token.getAddress(),
                        quickswapSellUrl + token.getAddress(),
                        quickswapInfoUrl + token.getAddress())
                .explorer(Map.of(
                        "Token contract: ", polygonscanContractCodeUrl + token.getAddress() + "#code",
                        "Token hodlers: ", polygonscanBalancesUrl + token.getAddress() + "#balances",
                        "Similar contracts: ", polygonscanSimilarContracts + token.getAddress()))

                .lock(Map.of(
                        "Unicrypt: ", unicryptLockQuickswapV2Url + token.getAddress(),
                        "Trustswap: ", trustswapLockUrl + token.getAddress(),
                        "DxSale: ",  dxSaleLockUrl))
                .security()
                .build();
    }
}
