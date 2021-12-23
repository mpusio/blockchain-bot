package com.coinspy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ExplorerPairCreated {
    private String status;
    private String message;
    private ExplorerResult[] result;

    @Getter
    public static class ExplorerResult {
        private String address;
        private String[] topics;
        private String data;
        private String blockNumber;
        private String timeStamp;
        private String gasPrice;
        private String gasUsed;
        private String logIndex;
        private String transactionHash;
        private String transactionIndex;
    }
}
