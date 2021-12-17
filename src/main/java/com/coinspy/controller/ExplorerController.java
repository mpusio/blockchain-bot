package com.coinspy.controller;

import com.coinspy.service.*;
import com.coinspy.telegram.Dex;
import com.coinspy.token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ExplorerController{

    public final EtherscanService etherscanService;
    public final BscscanService bscscanService;
    public final PolygonscanService polygonscanService;
    public final FtmscanService ftmscanService;
    public final SnowtraceService snowtraceService;

    @Autowired
    public ExplorerController(
            EtherscanService etherscanService,
            BscscanService bscscanService,
            PolygonscanService polygonscanService,
            FtmscanService ftmscanService,
            SnowtraceService snowtraceService) {
        this.etherscanService = etherscanService;
        this.bscscanService = bscscanService;
        this.polygonscanService = polygonscanService;
        this.ftmscanService = ftmscanService;
        this.snowtraceService = snowtraceService;
    }

    public String getLastBlockNumber(Dex dex) {
        if (dex.equals(Dex.UniSwapV2) ||
            dex.equals(Dex.UniSwapV3) ||
            dex.equals(Dex.SushiSwapV2)) return etherscanService.getBlockNumber();
        if (dex.equals(Dex.PancakeSwapV2)) return bscscanService.getBlockNumber();
        if (dex.equals(Dex.QuickSwapV2)) return polygonscanService.getBlockNumber();
        if (dex.equals(Dex.SpookySwap) ||
            dex.equals(Dex.SpiritSwap)) return ftmscanService.getBlockNumber();
        if (dex.equals(Dex.TraderJoe) ||
            dex.equals(Dex.Pangolin)) return snowtraceService.getBlockNumber();
        throw new IllegalArgumentException("Cannot get block number from this parameter");
    }

    public List<Token> getLastPairCreated(@RequestParam Dex dex, String fromBlock) {
        if (dex.equals(Dex.UniSwapV2)) return etherscanService.getCreatedPairUniswapV2(fromBlock);
        if (dex.equals(Dex.UniSwapV3)) return etherscanService.getCreatedPairUniswapV3(fromBlock);
        if (dex.equals(Dex.SushiSwapV2)) return etherscanService.getCreatedPairSushiswapV2(fromBlock);
        if (dex.equals(Dex.PancakeSwapV2)) return bscscanService.getCreatedPairPancakeswapV2(fromBlock);
        if (dex.equals(Dex.QuickSwapV2)) return polygonscanService.getCreatedPairQuickswapV2(fromBlock);
        if (dex.equals(Dex.SpookySwap)) return ftmscanService.getCreatedPairSpookyswap(fromBlock);
        if (dex.equals(Dex.SpiritSwap)) return ftmscanService.getCreatedPairSpiritswap(fromBlock);
        if (dex.equals(Dex.TraderJoe)) return snowtraceService.getCreatedPairTraderjoe(fromBlock);
        if (dex.equals(Dex.Pangolin)) return snowtraceService.getCreatedPairTraderjoe(fromBlock);
        throw new IllegalArgumentException("Cannot get block number from this parameter");
    }
}