package com.coinspy.token;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pair{
    private String pairAddress;
    private String address1;
    private String tokenSymbol1;
    private String value1;
    private String address2;
    private String tokenSymbol2;
    private String value2;
}
