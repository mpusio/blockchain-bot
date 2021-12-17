package com.coinspy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class PolygonscanQuickswapV2Dao extends ExplorerDao{

    @Autowired
    protected PolygonscanQuickswapV2Dao(RestTemplate restTemplate,
                                    @Value("${polygonscan.root}") String root,
                                    @Value("${polygonscan.apikey}") List<String> apiKey,
                                    @Value("${polygonscan.quickswapV2.topic.pair.created}") String pairCreatedTopic,
                                    @Value("${polygonscan.quickswapV2.topic.pair.minted}") String pairMintedTopic,
                                    @Value("${polygonscan.quickswapV2.factory}") String factoryAddress) {
        super(restTemplate, root, apiKey, pairCreatedTopic, pairMintedTopic, factoryAddress);
    }
}
