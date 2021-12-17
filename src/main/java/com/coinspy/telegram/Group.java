package com.coinspy.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class Group {
    private static final Map<String, String> idAndName = new HashMap<>();

    @Autowired
    public Group(
            @Value("${telegram.channel.ethereum.id}") String ethPairCreated,
            @Value("${telegram.channel.bsc.id}") String bscPairCreated,
            @Value("${telegram.channel.polygon.id}") String maticPairCreated) {
        idAndName.put(ethPairCreated, "Coinspy ETH network new pairs");
        idAndName.put(bscPairCreated, "Coinspy BSC network new pairs");
        idAndName.put(maticPairCreated, "Coinspy MATIC network new pairs");
    }

    public static String findById(String id){
        return idAndName.get(id);
    }
}

