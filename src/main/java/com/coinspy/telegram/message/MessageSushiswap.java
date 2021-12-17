package com.coinspy.telegram.message;

import com.coinspy.token.Token;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Map;

public class MessageSushiswap extends MessageComponents implements Message {

    private final Token token;

    public MessageSushiswap(Token token) {
        this.token = token;
    }

    @Override
    public SendMessage parseToSendMessage(String chatId) {
        return parse(buildTextMessage(), chatId);
    }

    @Override
    public String buildTextMessage(){
        return MessageCreator.MessageCreatorBuilder.builder(token)
                .title("New coin at Sushiswap!")
                .tokenInfo()
                .socialMedia()
                .liquidityPool()
                .charts(Map.of("Dextools: ", dextoolsSushiUrl + token.getLiquidityPair().getPairAddress(),
                               "Dexguru: ", dexGuruUrl + token.getAddress() + "-eth"))
                .dex(sushiswapUrl,
                     sushiswapUrl,
                    sushiswapInfoUrl + token.getLiquidityPair().getPairAddress())
                .explorer(Map.of(
                        "Token contract: ", etherscanContractCodeUrl + token.getAddress() + "#code",
                        "Token hodlers: ", etherscanBalancesUrl + token.getAddress() + "#balances",
                        "Similar contracts: ", etherscanSimilarContracts + token.getAddress()))
                .lock(Map.of(
                        "Unicrypt: ", unicryptLockSushiswapV2Url + token.getAddress(),
                        "Trustswap: ", trustswapLockUrl + token.getAddress(),
                        "DxSale: ",  dxSaleLockUrl))
                .security()
                .build();
    }
}
