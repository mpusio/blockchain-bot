package com.coinspy.service;

import com.coinspy.dao.PolygonscanQuickswapV2Dao;
import com.coinspy.dto.ExplorerPairCreated;
import com.coinspy.dto.ExplorerTransactionInfo;
import com.coinspy.token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolygonscanService extends ExplorerService{

    private final PolygonscanQuickswapV2Dao polygonscanQuickswapV2Dao;

    @Autowired
    public PolygonscanService(PolygonscanQuickswapV2Dao polygonscanQuickswapV2Dao) {
        this.polygonscanQuickswapV2Dao = polygonscanQuickswapV2Dao;
    }

    public List<Token> getCreatedPairQuickswapV2(String fromBlock) {
        return getCreatedPair(polygonscanQuickswapV2Dao, fromBlock);
    }

    public String getBlockNumber(){return getBlockNumber(polygonscanQuickswapV2Dao);}

    public ExplorerTransactionInfo getTransactionInfo(String hashTransaction){
        return polygonscanQuickswapV2Dao.callTransactionInfoUrl(hashTransaction);
    }

    public ExplorerPairCreated getLastTopics(String fromBlock, String address, String topic){
        return polygonscanQuickswapV2Dao.callLogs(fromBlock, address, topic);
    }
}
