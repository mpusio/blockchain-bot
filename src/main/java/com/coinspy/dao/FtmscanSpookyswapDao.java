package com.coinspy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class FtmscanSpookyswapDao extends ExplorerDao{
    @Autowired
    protected FtmscanSpookyswapDao(RestTemplate restTemplate,
                                      @Value("${fantom.root}") String root,
                                      @Value("${fantom.apikey}") List<String> apiKey,
                                      @Value("${fantom.spookyswap.topic.pair.created}") String pairCreatedTopic,
                                      @Value("${fantom.spookyswap.topic.pair.minted}") String pairMintedTopic,
                                      @Value("${fantom.spookyswap.factory}") String factoryAddress) {
        super(restTemplate, root, apiKey, pairCreatedTopic, pairMintedTopic, factoryAddress);
    }
}
