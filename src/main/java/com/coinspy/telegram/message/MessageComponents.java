package com.coinspy.telegram.message;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

class MessageComponents {
    //general
    protected String dexscreenerFantomUrl = "https://dexscreener.com/fantom/";
    protected String dexscreenerBscUrl = "https://dexscreener.com/bsc/";
    protected String dexscreenerAvalancheUrl = "https://dexscreener.com/avalanche/";
    protected String dexscreenerPolygonUrl = "https://dexscreener.com/polygon/";
    protected String dexscreenerEthereumUrl = "https://dexscreener.com/ethereum/";

    //uniswap
    protected String dextoolsUniswapUrl = "https://www.dextools.io/app/uniswap/pair-explorer/";
    protected String dexGuruUrl = "https://dex.guru/token/";

    protected String uniswapBuyUrl = "https://app.uniswap.org/#/swap?outputCurrency=";
    protected String uniswapSellUrl = "https://app.uniswap.org/#/swap?inputCurrency=";
    protected String uniswapInfoUrl = "https://info.uniswap.org/#/tokens/";

    protected String etherscanContractCodeUrl = "https://etherscan.io/address/";
    protected String etherscanBalancesUrl = "https://etherscan.io/token/";
    protected String etherscanSimilarContracts = "https://etherscan.io/find-similar-contracts?a=";

    //sushiswap
    protected String dextoolsSushiUrl = "https://www.dextools.io/app/sushiswap/pair-explorer/";
    protected String sushiswapUrl = "https://app.sushi.com/swap";
    protected String sushiswapInfoUrl = "https://analytics.sushi.com/pairs/";

    //pancake
    protected String dextoolsPancakeUrl = "https://www.dextools.io/app/pancakeswap/pair-explorer/";
    protected String pancakeBuyUrl = "https://exchange.pancakeswap.finance/#/swap?outputCurrency=";
    protected String pancakeSellUrl = "https://exchange.pancakeswap.finance/#/swap?inputCurrency=";
    protected String pancakeInfoUrl = "https://pancakeswap.info/pool/";

    protected String bscscanContractCodeUrl = "https://bscscan.com/address/";
    protected String bscscanBalancesUrl = "https://bscscan.com/token/";
    protected String bscscanSimilarContracts = "https://bscscan.com/find-similar-contracts?a=";

    //quickswap
    protected String poocoinPolygonChart = "https://polygon.poocoin.app/tokens/";
    protected String quickswapBuyUrl = "https://quickswap.exchange/#/swap?outputCurrency=";
    protected String quickswapSellUrl = "https://quickswap.exchange/#/swap?inputCurrency=";
    protected String quickswapInfoUrl = "https://info.quickswap.exchange/token/";

    protected String polygonscanContractCodeUrl = "https://polygonscan.com/address/";
    protected String polygonscanBalancesUrl = "https://polygonscan.com/token/";
    protected String polygonscanSimilarContracts = "https://polygonscan.com/find-similar-contracts?a=";

    //fantom
    protected String spookyBuyUrl = "https://spookyswap.finance/swap?outputCurrency=";
    protected String spookySellUrl = "https://spookyswap.finance/swap?inputCurrency=";
    protected String spookyInfoUrl = "https://info.spookyswap.finance/pair/";

    protected String spiritBuyUrl = "https://swap.spiritswap.finance/#/swap?outputCurrency=";
    protected String spiritSellUrl = "https://swap.spiritswap.finance/#/swap?intputCurrency=";
    protected String spiritInfoUrl = "https://info.spiritswap.finance/pair/";

    protected String ftmscanContractCodeUrl = "https://ftmscan.com/address/";
    protected String ftmscanBalancesUrl = "https://ftmscan.com/token/";
    protected String ftmscanSimilarContracts = "https://ftmscan.com/find-similar-contracts?a=";

    //avalanche
    protected String traderjoeBuyUrl = "https://traderjoexyz.com/#/trade?outputCurrency=";
    protected String traderjoeSellUrl = "https://traderjoexyz.com/#/trade?inputCurrency=";
    protected String traderjoeInfoUrl = "https://analytics.traderjoexyz.com/tokens/";

    protected String pangolinBuyUrl = "https://app.pangolin.exchange/#/swap?outputCurrency=";
    protected String pangolinSellUrl = "https://app.pangolin.exchange/#/swap?inputputCurrency=";
    protected String pangolinInfoUrl = "https://info.pangolin.exchange/#/token/";

    protected String snowtraceContractCodeUrl = "https://snowtrace.io/address/";
    protected String snowtraceBalancesUrl = "https://snowtrace.io/token/";
    protected String snowtraceSimilarContracts = "https://snowtrace.io/find-similar-contracts?a=";

    //liquidity lock
    protected String unicryptLockUniswapV2Url = "https://app.unicrypt.network/amm/uni-v2/token/";
    protected String unicryptLockSushiswapV2Url = "https://app.unicrypt.network/amm/sushi-v1//token/";
    protected String unicryptLockQuickswapV2Url = "https://app.unicrypt.network/amm/quickswap-v1/token/";
    protected String unicryptLockPancakeswapV2Url = "https://app.unicrypt.network/amm/pancake-v2/token/";
    protected String trustswapLockUrl = "https://team.finance/view-coin/";
    protected String dxSaleLockUrl = "https://dxsale.app/app/v2_9/dxlock";


    public SendMessage parse(String textMessage, String chatId) {
        var sendMessage = new SendMessage(chatId, textMessage);

        var markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        var inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText("Website");
        inlineKeyboardButton.setCallbackData("Website");
        inlineKeyboardButton.setUrl("https://google.com");
        rowInline.add(0, inlineKeyboardButton);
        rowsInline.add(rowInline);

        // Add it to the message
        markupInline.setKeyboard(rowsInline);
        sendMessage.setReplyMarkup(markupInline);
        return sendMessage;
    }
}
