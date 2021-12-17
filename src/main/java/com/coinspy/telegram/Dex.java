package com.coinspy.telegram;

public enum Dex {
    UniSwapV2("UniSwapV2"),
    UniSwapV3("UniSwapV3"),
    SushiSwapV2("SushiSwapV2"),
    PancakeSwapV2("PancakeSwapV2"),
    QuickSwapV2("QuickSwapV2"),
    SpookySwap("SpookySwap"),
    SpiritSwap("SpiritSwap"),
    TraderJoe("TraderJoe"),
    Pangolin("Pangolin");

    String value;

    Dex(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
