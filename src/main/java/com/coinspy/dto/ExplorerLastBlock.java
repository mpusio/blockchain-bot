package com.coinspy.dto;

import lombok.Getter;

@Getter
public class ExplorerLastBlock {
    private String jsonrpc;
    private String id;
    private String result;
}
