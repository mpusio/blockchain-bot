package com.coinspy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class SnowtraceTraderjoeDao extends ExplorerDao{
    @Autowired
    protected SnowtraceTraderjoeDao(RestTemplate restTemplate,
                                   @Value("${avalanche.root}") String root,
                                   @Value("${avalanche.apikey}") List<String> apiKey,
                                   @Value("${avalanche.traderjoe.topic.pair.created}") String pairCreatedTopic,
                                   @Value("${avalanche.traderjoe.topic.pair.minted}") String pairMintedTopic,
                                   @Value("${avalanche.traderjoe.factory}") String factoryAddress) {
        super(restTemplate, root, apiKey, pairCreatedTopic, pairMintedTopic, factoryAddress);
    }
}
