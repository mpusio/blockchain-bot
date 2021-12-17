package com.coinspy.dao;

import com.coinspy.dto.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

abstract class ExplorerDao implements ExplorerDaoInterface {

    private final RestTemplate restTemplate;
    private final String root;
    private final List<String> apiKeys;
    private final String pairCreatedTopic;
    private final String pairMintedTopic;
    private final String factoryAddress;

    protected ExplorerDao(RestTemplate restTemplate, String root, List<String> apiKeys, String pairCreatedTopic, String pairMintedTopic, String factoryAddress) {
        this.restTemplate = restTemplate;
        this.root = root;
        this.apiKeys = apiKeys;
        this.pairCreatedTopic = pairCreatedTopic;
        this.pairMintedTopic = pairMintedTopic;
        this.factoryAddress = factoryAddress;
    }

    public ExplorerLastBlock callBlockNumberUrl(){
        String lastBlockNumberUrl = ExplorerApiUrl.ExplorerApiUrlBuilder
                .builder(root)
                .module("proxy")
                .action("eth_blockNumber")
                .apiKey(randomApiKey(apiKeys))
                .build().getFinalUrl();

        return restTemplate.getForObject(lastBlockNumberUrl, ExplorerLastBlock.class);
    }

    public ExplorerTransactionInfo callTransactionInfoUrl(String transactionHash){
        String transactionInfoUrl = ExplorerApiUrl.ExplorerApiUrlBuilder
                .builder(root)
                .module("proxy")
                .action("eth_getTransactionByHash")
                .txHash(transactionHash)
                .apiKey(randomApiKey(apiKeys))
                .build().getFinalUrl();

        return restTemplate.getForObject(transactionInfoUrl, ExplorerTransactionInfo.class);
    }

    public ExplorerPairCreated callLastCreatedPairsUrl(String fromBlock){
        String pairCreatedUrl = ExplorerApiUrl.ExplorerApiUrlBuilder
                .builder(root)
                .module("logs")
                .action("getLogs")
                .fromBlock(fromBlock)
                .toBlock("latest")
                .address(factoryAddress)
                .topic0(pairCreatedTopic)
                .apiKey(randomApiKey(apiKeys))
                .build().getFinalUrl();

        return restTemplate.getForObject(pairCreatedUrl, ExplorerPairCreated.class);
    }

    public ExplorerPairMinted callMintedPairUrl (String pairAddress){
        String mintedPairUrl = ExplorerApiUrl.ExplorerApiUrlBuilder
                .builder(root)
                .module("logs")
                .action("getLogs")
                .fromBlock("latest")
                .toBlock("latest")
                .address(pairAddress)
                .topic0(pairMintedTopic)
                .apiKey(randomApiKey(apiKeys))
                .build().getFinalUrl();

        return restTemplate.getForObject(mintedPairUrl, ExplorerPairMinted.class);
    }

    public ExplorerTokenInfo callTokenInfoUrl(String tokenAddress){
        String tokenInfoUrl = ExplorerApiUrl.ExplorerApiUrlBuilder
                .builder(root)
                .module("account")
                .action("tokentx")
                .contractAddress(tokenAddress)
                .sort("asc")
                .page("1")
                .offset("1")
                .apiKey(randomApiKey(apiKeys))
                .build().getFinalUrl();

        return restTemplate.getForObject(tokenInfoUrl, ExplorerTokenInfo.class);
    }

    public ExplorerTokenBalance callTokenBalanceInContract(String contractAddress, String tokenAddress){
        String tokenBalanceUrl = ExplorerApiUrl.ExplorerApiUrlBuilder
                .builder(root)
                .module("account")
                .action("tokenbalance")
                .contractAddress(contractAddress)
                .address(tokenAddress)
                .apiKey(randomApiKey(apiKeys))
                .build().getFinalUrl();

        return restTemplate.getForObject(tokenBalanceUrl, ExplorerTokenBalance.class);
    }

    public ExplorerContractSourceCode callContractSourceCode(String contractAddress){
        String contractSourceCodeUrl = ExplorerApiUrl.ExplorerApiUrlBuilder
                .builder(root)
                .module("contract")
                .action("getsourcecode")
                .address(contractAddress)
                .apiKey(randomApiKey(apiKeys))
                .build().getFinalUrl();

        return restTemplate.getForObject(contractSourceCodeUrl, ExplorerContractSourceCode.class);
    }

    public ExplorerContractByteCode callContractBytecode(String contractAddress){
        String contractByteCodeUrl = ExplorerApiUrl.ExplorerApiUrlBuilder
                .builder(root)
                .module("proxy")
                .action("eth_getCode")
                .address(contractAddress)
                .apiKey(randomApiKey(apiKeys))
                .build().getFinalUrl();

        return restTemplate.getForObject(contractByteCodeUrl, ExplorerContractByteCode.class);
    }

    public ExplorerPairCreated callLogs(String fromBlock, String address, String topic){
        String pairCreatedUrl = ExplorerApiUrl.ExplorerApiUrlBuilder
                .builder(root)
                .module("logs")
                .action("getLogs")
                .fromBlock(fromBlock)
                .toBlock("latest")
                .address(address)
                .topic0(topic)
                .apiKey(randomApiKey(apiKeys))
                .build().getFinalUrl();

        return restTemplate.getForObject(pairCreatedUrl, ExplorerPairCreated.class);
    }

    private String randomApiKey(List<String> apiKeys){
        Random rand = new Random();
        return apiKeys.get(rand.nextInt(apiKeys.size()));
    }
}
