package com.coinspy.service;

import com.coinspy.dao.ExplorerDaoInterface;
import com.coinspy.dto.ExplorerContractSourceCode;
import com.coinspy.dto.ExplorerPairCreated;
import com.coinspy.dto.ExplorerTokenInfo;
import com.coinspy.entity.CodeEntity;
import com.coinspy.entity.Type;
import com.coinspy.repository.CodeRepository;
import com.coinspy.token.Pair;
import com.coinspy.token.Security;
import com.coinspy.token.Token;
import com.coinspy.useful.DataExtractor;
import com.coinspy.useful.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

abstract class ExplorerService {

    @Autowired
    private DataExtractor dataExtractor;
    @Autowired
    private CodeRepository codeRepository;

    private final Logger logger = LoggerFactory.getLogger(ExplorerService.class);

    protected ExplorerService() {
    }

    protected String getBlockNumber(ExplorerDaoInterface explorer){
        return dataExtractor.extractNumberOfLastBlock(explorer.callBlockNumberUrl());
    }

    protected List<Token> getCreatedPair(ExplorerDaoInterface explorer, String fromBlock){
        ExplorerPairCreated explorerPairCreated;
        try{
            explorerPairCreated = explorer.callLastCreatedPairsUrl(fromBlock);
        }catch (Exception ignore){
            explorerPairCreated = new ExplorerPairCreated();
        }

        List<Token> tokens = new ArrayList<>();

        for (ExplorerPairCreated.ExplorerResult result : explorerPairCreated.getResult()) {
            try {
                String tokenAddress = dataExtractor.extractTokenAddressFromPair(result);
                String tokenLiquidityBaseAddress = dataExtractor.extractBaseLiquidityAddressFromPair(result, tokenAddress);
                String pairAddress = dataExtractor.extractPairAddress(result);

                String tokenAddressBalance = explorer
                        .callTokenBalanceInContract(tokenAddress, pairAddress).getResult();

//                if (tokenAddressBalance=="0") continue;

                String tokenLiquidityBaseBalance = explorer
                        .callTokenBalanceInContract(tokenLiquidityBaseAddress, pairAddress).getResult();

                ExplorerTokenInfo.ExplorerResult tokenInfo = dataExtractor.extractTokenInfo(
                        explorer
                                .callTokenInfoUrl(tokenAddress)
                                .getResult()[0]);

                ExplorerTokenInfo.ExplorerResult tokenLiquidityBaseInfo = dataExtractor.extractTokenInfo(
                        explorer
                                .callTokenInfoUrl(tokenLiquidityBaseAddress)
                                .getResult()[0]);

                ExplorerContractSourceCode.ExplorerResult sourceCode = explorer.callContractSourceCode(tokenAddress).getResult()[0];

                String byteCode = explorer.callContractBytecode(tokenAddress).getResult();
                Type type = Type.UNCHECKED; //compareWithDiffBytecodes(byteCode);

                String blockNumber = Parser.hexToDec(result.getBlockNumber().replace("0x", ""));

                tokens.add(Token.builder()
                        .address(tokenAddress)
                        .tokenName(tokenInfo.getTokenName())
                        .tokenSymbol(tokenInfo.getTokenSymbol())
                        .totalSupply(Parser.divideByDecimals(tokenInfo.getValue(), tokenInfo.getTokenDecimal()))
                        .tokenDecimal(tokenInfo.getTokenDecimal())
                        .blockNumber(blockNumber)
                        .liquidityPair(Pair.builder()
                                        .pairAddress(pairAddress)
                                        .tokenSymbol1(tokenInfo.getTokenSymbol())
                                        .value1(Parser.divideByDecimals(tokenAddressBalance, tokenInfo.getTokenDecimal()))
                                        .address2(tokenLiquidityBaseAddress)
                                        .tokenSymbol2(tokenLiquidityBaseInfo.getTokenSymbol())
                                        .value2(Parser.divideByDecimals(tokenLiquidityBaseBalance, tokenLiquidityBaseInfo.getTokenDecimal()))
                                        .build())
                        .security(Security.builder()
                                .verified(sourceCode.getRuns().equals("200"))
                                .contractName(sourceCode.getContractName())
                                .compilerVersion(sourceCode.getCompilerVersion())
                                .licenseType(sourceCode.getLicenseType())
                                .contractType(type)
                                .build())
                        .build());

            }
            catch (Exception e){
                logger.error("Something went wrong", e);
            }
        }

        return tokens.stream()
                .sorted(Comparator.comparing(Token::getBlockNumber))
                .collect(Collectors.toList());
    }

    private Type compareWithDiffBytecodes(String bytecode){
        if (codeRepository.findByType(Type.SCAM).stream()
                .map(CodeEntity::getByteCode)
                .anyMatch(e -> e.equals(bytecode)))
            return Type.SCAM;

        if (codeRepository.findByType(Type.TRUSTED).stream()
                .map(CodeEntity::getByteCode)
                .anyMatch(e -> e.equals(bytecode)))
            return Type.TRUSTED;

        return Type.UNCHECKED;
    }
}
