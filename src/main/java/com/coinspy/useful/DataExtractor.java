package com.coinspy.useful;

import com.coinspy.dto.ExplorerLastBlock;
import com.coinspy.dto.ExplorerPairCreated;
import com.coinspy.dto.ExplorerPairMinted;
import com.coinspy.dto.ExplorerTokenInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataExtractor {
    private final List<String> liquidityBaseAddresses = Arrays.asList(
            "0xa0b86991c6218b36c1d19d4a2e9eb0ce3606eb48", //usdc eth
            "0x8ac76a51cc950d9822d68b83fe1ad97b32cd580d", //usdc bsc
            "0xA7D7079b0FEaD91F3e65f86E8915Cb59c1a4C664", //usdc avax
            "0x2791bca1f2de4661ed88a30c99a7a9449aa84174", //usdc polygon
            "0x04068DA6C83AFCFA0e13ba15A6696662335D5B75", //usdc fantom

            "0xdac17f958d2ee523a2206206994597c13d831ec7", //usdt eth
            "0x55d398326f99059ff775485246999027b3197955", //usdt bsc
            "0xc7198437980c041c805a1edcba50c1ce5db95118", //usdt avax
            "0xc2132d05d31c914a87c6611c10748aeb04b58e8f", //usdt polygon
            "0x049d68029688eabf473097a2fc38ef61633a3c7a", //usdt fantom

            "0x99d8a9c45b2eca8864373a26d1459e3dff1e17f3", //mim eth
            "0xfe19f0b51438fd612f6fd59c1dbb3ea319f433ba", //mim bsc
            "0x130966628846BFd36ff31a822705796e8cb8C18D", //mim avax
            "0x82f0b8b456c1a451378467398982d4834b6829c1", //mim fantom

            "0xc02aaa39b223fe8d0a0e5c4f27ead9083c756cc2", //weth
            "0xbb4CdB9CBd36B01bD1cBaEBF2De08d9173bc095c", //wbsc
            "0xe9e7cea3dedca5984780bafc599bd69add087d56", //busd
            "0xb31f66aa3c1e785363f0875a1b74e27b85fd66c7", //wavax
            "0x21be370d5312f44cb42ce377bc9b8a0cef1a4c83", //wftm
            "0x0000000000000000000000000000000000001010", //matic
            "0x0d500b1d8e8ef31e21c99d1db9a6444d3adf1270"  //wmatic

    );

    public DataExtractor(@Value("${addresses.to.filter}") List<String> liquidityBaseAddresses) {
//        this.liquidityBaseAddresses = liquidityBaseAddresses;
    }

    public String extractNumberOfLastBlock(ExplorerLastBlock data){
        return Parser.hexToDec(data.getResult().replace("x", ""));
    }

    public String extractPairAddress(ExplorerPairCreated.ExplorerResult data){
        return Parser.cutAddress(Parser.parseInputFromExplorer(data.getData(), false).get(0));
    }

    public String extractTokenAddressFromPair(ExplorerPairCreated.ExplorerResult data){
        List<String> addresses = liquidityBaseAddresses.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        return Arrays.stream(data.getTopics())
                .skip(1)
                .map(Parser::cutAddress)
                .filter(e -> !addresses.contains(e.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cannot find token address"));
    }

    public String extractBaseLiquidityAddressFromPair(ExplorerPairCreated.ExplorerResult data, String tokenAddress){
        return Arrays.stream(data.getTopics())
                .skip(1)
                .map(Parser::cutAddress)
                .map(String::toLowerCase)
                .filter(e -> !e.equals(tokenAddress.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cannot find liquidity base address"));
    }

    public ExplorerTokenInfo.ExplorerResult extractTokenInfo(ExplorerTokenInfo.ExplorerResult data){
        return data;
    }

    public List<String> extractAddressFromPair(ExplorerPairCreated.ExplorerResult data){
        return Arrays.asList(Parser.cutAddress(data.getTopics()[1]), Parser.cutAddress(data.getTopics()[2]));
    }

    public List<String> extractValuesFromMintedPair(ExplorerPairMinted.ExplorerResult data) {
        return Parser.parseInputFromExplorer(
                        data.getData(),
                        false)
                .stream()
                .map(Parser::hexToDec)
                .collect(Collectors.toList());

    }
}
