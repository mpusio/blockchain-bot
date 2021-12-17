package com.coinspy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class EtherscanUniswapV3Dao extends ExplorerDao{

    @Autowired
    protected EtherscanUniswapV3Dao(RestTemplate restTemplate,
                                    @Value("${etherscan.root}") String root,
                                    @Value("${etherscan.apikey}") List<String> apiKey,
                                    @Value("${etherscan.uniswapV3.topic.pair.created}") String pairCreatedTopic,
                                    @Value("${etherscan.uniswapV3.topic.pair.minted}") String pairMintedTopic,
                                    @Value("${etherscan.uniswapV3.factory}") String factoryAddress) {
        super(restTemplate, root, apiKey, pairCreatedTopic, pairMintedTopic, factoryAddress);
    }
}
