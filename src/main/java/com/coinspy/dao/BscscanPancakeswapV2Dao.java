package com.coinspy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class BscscanPancakeswapV2Dao extends ExplorerDao{

    @Autowired
    protected BscscanPancakeswapV2Dao(RestTemplate restTemplate,
                                        @Value("${bscscan.root}") String root,
                                        @Value("${bscscan.apikey}") List<String> apiKey,
                                        @Value("${bscscan.pancakeswapV2.topic.pair.created}") String pairCreatedTopic,
                                        @Value("${bscscan.pancakeswapV2.topic.pair.minted}") String pairMintedTopic,
                                        @Value("${bscscan.pancakeswapV2.factory}") String factoryAddress) {
        super(restTemplate, root, apiKey, pairCreatedTopic, pairMintedTopic, factoryAddress);
    }
}
