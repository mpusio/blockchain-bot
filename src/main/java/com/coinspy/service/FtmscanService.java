package com.coinspy.service;

import com.coinspy.dao.FtmscanSpiritswapDao;
import com.coinspy.dao.FtmscanSpookyswapDao;
import com.coinspy.token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FtmscanService extends ExplorerService{

    private final FtmscanSpookyswapDao ftmscanSpookyswapDao;
    private final FtmscanSpiritswapDao ftmscanSpiritswapDao;

    @Autowired
    public FtmscanService(FtmscanSpookyswapDao ftmscanSpookyswapDao, FtmscanSpiritswapDao ftmscanSpiritswapDao) {
        this.ftmscanSpookyswapDao = ftmscanSpookyswapDao;
        this.ftmscanSpiritswapDao = ftmscanSpiritswapDao;
    }

    public List<Token> getCreatedPairSpookyswap(String fromBlock) {
        return getCreatedPair(ftmscanSpookyswapDao, fromBlock);
    }

    public List<Token> getCreatedPairSpiritswap(String fromBlock) {
        return getCreatedPair(ftmscanSpiritswapDao, fromBlock);
    }

    public String getBlockNumber(){return getBlockNumber(ftmscanSpookyswapDao);}
}
