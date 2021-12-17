package com.coinspy.token;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Token {
    private String address;
    private String tokenName;
    private String tokenSymbol;
    private String tokenDecimal;
    private String totalSupply;
    private String blockNumber;
    private Pair liquidityPair;
    private Security security;
}
