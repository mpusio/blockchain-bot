package com.coinspy.dao;

import com.coinspy.dto.ExplorerPairMinted;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EtherscanUniswapV2DaoTest {

    @Autowired
    EtherscanUniswapV2Dao etherscanUniswapV2Dao;

    private String examplePair = "0x95dcb5b2fc056e28bec4086051bc195d11717763";

    @Test
    public void assertCallMintedPairUrlReturnResponse(){
        ExplorerPairMinted explorerPairMinted = etherscanUniswapV2Dao.callMintedPairUrl(examplePair);
        System.out.println(explorerPairMinted.getResult());
    }

}