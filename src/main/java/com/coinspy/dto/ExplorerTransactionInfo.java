package com.coinspy.dto;

import lombok.Getter;

@Getter
public class ExplorerTransactionInfo {
    private String jsonrpc;
    private String id;
    private ExplorerResult result;

    @Getter
    public static class ExplorerResult {
        private String blockHash;
        private String blockNumber;
        private String from;
        private String gas;
        private String gasPrice;
        private String hash;
        private String input;
        private String nonce;
        private String to;
        private String transactionIndex;
        private String value;
        private String type;
        private String v;
        private String r;
        private String s;
    }
}
