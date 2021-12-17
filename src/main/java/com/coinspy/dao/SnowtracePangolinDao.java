package com.coinspy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class SnowtracePangolinDao extends ExplorerDao {
    @Autowired
    protected SnowtracePangolinDao(RestTemplate restTemplate,
                                   @Value("${avalanche.root}") String root,
                                   @Value("${avalanche.apikey}") List<String> apiKey,
                                   @Value("${avalanche.pangolin.topic.pair.created}") String pairCreatedTopic,
                                   @Value("${avalanche.pangolin.topic.pair.minted}") String pairMintedTopic,
                                   @Value("${avalanche.pangolin.factory}") String factoryAddress) {
        super(restTemplate, root, apiKey, pairCreatedTopic, pairMintedTopic, factoryAddress);
    }
}
