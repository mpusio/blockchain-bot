package com.coinspy.service;

import com.coinspy.dao.EtherscanSushiswapV2Dao;
import com.coinspy.dao.EtherscanUniswapV2Dao;
import com.coinspy.dao.EtherscanUniswapV3Dao;
import com.coinspy.token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtherscanService extends ExplorerService{

    private final EtherscanUniswapV2Dao etherscanUniswapV2Dao;
    private final EtherscanUniswapV3Dao etherscanUniswapV3Dao;
    private final EtherscanSushiswapV2Dao etherscanSushiswapV2Dao;

    @Autowired
    public EtherscanService(EtherscanUniswapV2Dao etherscanUniswapV2Dao, EtherscanUniswapV3Dao etherscanUniswapV3Dao, EtherscanSushiswapV2Dao etherscanSushiswapV2Dao) {
        this.etherscanUniswapV2Dao = etherscanUniswapV2Dao;
        this.etherscanUniswapV3Dao = etherscanUniswapV3Dao;
        this.etherscanSushiswapV2Dao = etherscanSushiswapV2Dao;
    }

    public List<Token> getCreatedPairUniswapV2(String fromBlock){
        return getCreatedPair(etherscanUniswapV2Dao, fromBlock);
    }

    public List<Token> getCreatedPairUniswapV3(String fromBlock) {
        return getCreatedPair(etherscanUniswapV3Dao, fromBlock);
    }

    public List<Token> getCreatedPairSushiswapV2(String fromBlock) {
        return getCreatedPair(etherscanSushiswapV2Dao, fromBlock);
    }

    public String getBlockNumber(){return getBlockNumber(etherscanUniswapV2Dao);}
}
