package com.coinspy.dao;

import com.coinspy.dto.*;

public interface ExplorerDaoInterface {
    ExplorerLastBlock callBlockNumberUrl();

    ExplorerTransactionInfo callTransactionInfoUrl(String transactionHash);

    ExplorerPairCreated callLastCreatedPairsUrl(String fromBlock);

    ExplorerPairMinted callMintedPairUrl (String pairAddress);

    ExplorerTokenInfo callTokenInfoUrl(String tokenAddress);

    ExplorerTokenBalance callTokenBalanceInContract(String contractAddress, String tokenAddress);

    ExplorerContractSourceCode callContractSourceCode(String contractAddress);

    ExplorerContractByteCode callContractBytecode(String contractAddress);
}
