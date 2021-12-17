package com.coinspy.telegram.message;

import com.coinspy.token.Token;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Map;

public class MessageUniswap extends MessageComponents implements Message {

    private final Token token;

    public MessageUniswap(Token token) {
        this.token = token;
    }

    @Override
    public SendMessage parseToSendMessage(String chatId) {
        return parse(buildTextMessage(), chatId);
    }

    @Override
    public String buildTextMessage(){
        return MessageCreator.MessageCreatorBuilder.builder(token)
                .title("New coin at Uniswap!")
                .tokenInfo()
                .socialMedia()
                .liquidityPool()
                .charts(Map.of("Dextools: ", dextoolsUniswapUrl + token.getLiquidityPair().getPairAddress(),
                               "Dexguru: ", dexGuruUrl + token.getAddress() + "-eth"))
                .dex(uniswapBuyUrl + token.getAddress(),
                     uniswapSellUrl + token.getAddress(),
                     uniswapInfoUrl + token.getAddress())
                .explorer(Map.of(
                        "Token contract: ", etherscanContractCodeUrl + token.getAddress() + "#code",
                        "Token hodlers: ", etherscanBalancesUrl + token.getAddress() + "#balances",
                        "Similar contracts: ", etherscanSimilarContracts + token.getAddress()))
                .lock(Map.of(
                        "Unicrypt: ", unicryptLockUniswapV2Url + token.getAddress(),
                        "Trustswap: ", trustswapLockUrl + token.getAddress(),
                        "DxSale: ",  dxSaleLockUrl))
                .security()
                .build();
    }
}
