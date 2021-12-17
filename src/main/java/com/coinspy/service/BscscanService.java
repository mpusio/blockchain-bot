package com.coinspy.service;

import com.coinspy.dao.BscscanPancakeswapV2Dao;
import com.coinspy.token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BscscanService extends ExplorerService{

    private final BscscanPancakeswapV2Dao bscscanPancakeswapV2Dao;

    @Autowired
    public BscscanService(BscscanPancakeswapV2Dao bscscanPancakeswapV2Dao) {
        this.bscscanPancakeswapV2Dao = bscscanPancakeswapV2Dao;
    }

    public List<Token> getCreatedPairPancakeswapV2(String fromBlock) {
        return getCreatedPair(bscscanPancakeswapV2Dao, fromBlock);
    }

    public String getBlockNumber(){return getBlockNumber(bscscanPancakeswapV2Dao);}
}
