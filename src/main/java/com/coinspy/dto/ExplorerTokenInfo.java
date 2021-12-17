package com.coinspy.dto;

import lombok.Getter;

@Getter
public class ExplorerTokenInfo {
    private String status;
    private String message;
    private ExplorerResult[] result;

    @Getter
    public static class ExplorerResult{
        private String blockNumber;
        private String timeStamp;
        private String hash;
        private String nonce;
        private String blockHash;
        private String from;
        private String contractAddress;
        private String to;
        private String value;
        private String tokenName;
        private String tokenSymbol;
        private String tokenDecimal;
        private String transactionIndex;
        private String gas;
        private String priceGas;
        private String gasUsed;
        private String culminativeGasUsed;
        private String input;
        private String confirmation;
    }
}
