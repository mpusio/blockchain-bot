package com.coinspy.telegram.message;

import com.coinspy.token.Token;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Map;

public class MessagePancakeswap extends MessageComponents implements Message {

    private final Token token;

    public MessagePancakeswap(Token token) {
        this.token = token;
    }

    @Override
    public SendMessage parseToSendMessage(String chatId) {
        return parse(buildTextMessage(), chatId);
    }

    @Override
    public String buildTextMessage(){
        return MessageCreator.MessageCreatorBuilder.builder(token)
                .title("New coin at Pancakeswap!")
                .tokenInfo()
                .socialMedia()
                .liquidityPool()
                .charts(Map.of("Dextools: ", dextoolsPancakeUrl + token.getLiquidityPair().getPairAddress(),
                        "Dexguru: ", dexGuruUrl + token.getAddress() + "-bsc"))
                .dex(pancakeBuyUrl + token.getAddress(),
                        pancakeSellUrl + token.getAddress(),
                        pancakeInfoUrl + token.getLiquidityPair().getPairAddress())
                .explorer(Map.of(
                        "Token contract: ", bscscanContractCodeUrl + token.getAddress() + "#code",
                        "Token hodlers: ", bscscanBalancesUrl + token.getAddress() + "#balances",
                        "Similar contracts: ", bscscanSimilarContracts + token.getAddress()))
                .lock(Map.of(
                        "Unicrypt: ", unicryptLockPancakeswapV2Url + token.getAddress(),
                        "Trustswap: ", trustswapLockUrl + token.getAddress(),
                        "DxSale: ",  dxSaleLockUrl))
                .security()
                .build();
    }
}
