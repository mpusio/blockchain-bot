package com.coinspy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class FtmscanSpiritswapDao extends ExplorerDao{
    @Autowired
    protected FtmscanSpiritswapDao(RestTemplate restTemplate,
                                   @Value("${fantom.root}") String root,
                                   @Value("${fantom.apikey}") List<String> apiKey,
                                   @Value("${fantom.spiritswap.topic.pair.created}") String pairCreatedTopic,
                                   @Value("${fantom.spiritswap.topic.pair.minted}") String pairMintedTopic,
                                   @Value("${fantom.spiritswap.factory}") String factoryAddress) {
        super(restTemplate, root, apiKey, pairCreatedTopic, pairMintedTopic, factoryAddress);
    }
}
