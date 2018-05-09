package com.company.CryptoExchange.Binance;


import com.company.CryptoExchange.Binance.Json.AccountData.Balance.AssetData;
import com.company.CryptoExchange.Binance.Json.AccountData.BinanceAccountData;
import com.company.CryptoExchange.Binance.Json.ExchangeData.BinanceExchangeData;
import com.company.CryptoExchange.Binance.Json.ExchangeData.Symbol.Filter.FilterData;
import com.company.CryptoExchange.Exchange;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Collectors;

import static com.company.Client.HttpRequestHandling.executeRequest;
import static com.company.Utils.HmacEncoding.encode;
import static com.company.Variables.BINANCE_API_KEY;
import static com.company.Variables.BINANCE_API_SECRET;


// TODO: refactor when used
public class BinanceApiInterface implements Exchange {

    private final static Logger logger = Logger.getLogger(BinanceApiInterface.class);

    public void convertFundToBitcoin() {
        ImmutableMap<String, String> apikeyheader = new ImmutableMap.Builder<String, String>().put("X-MBX-APIKEY", BINANCE_API_KEY).build();
        Gson g = new Gson();

        String serverTimeJson = executeRequest("https://www.binance.com/api/v1/time", "GET", apikeyheader);
        String serverTime = serverTimeJson.substring(serverTimeJson.indexOf(':') + 1, serverTimeJson.length() - 1);

        String urlAccount = "https://api.binance.com/api/v3/account";
        String dataAccount = "recvWindow=5000&timestamp=" + serverTime;// + System.currentTimeMillis();
        String signatureAccount = encode(BINANCE_API_SECRET, dataAccount, "HmacSHA256");
        String resultAccount = executeRequest(urlAccount + "?" + dataAccount + "&signature=" + signatureAccount, "GET", apikeyheader);
        BinanceAccountData binanceAccountData = g.fromJson(resultAccount, BinanceAccountData.class);
        binanceAccountData.setBalances(binanceAccountData.getBalances().stream()
                .filter(asset -> asset.getFree().compareTo(BigDecimal.ZERO) != 0 || asset.getLocked().compareTo(BigDecimal.ZERO) != 0)
                .collect(Collectors.toList()));

        String resultExchange = executeRequest("https://www.binance.com/api/v1/exchangeInfo", "GET", apikeyheader);
        BinanceExchangeData binanceExchangeData = g.fromJson(resultExchange, BinanceExchangeData.class);

        for (AssetData asset: binanceAccountData.getBalances()) {
            if(!asset.getName().equals("BTC")) {
                String symbol = asset.getName() + "BTC";
                FilterData symbolFilterData = binanceExchangeData.getSymbols().stream()
                        .filter(currentsymbol -> currentsymbol.getSymbol().equals(symbol))
                        .findFirst().get()
                        .getFilters().stream()
                        .filter(filter -> filter.getFilterType().equals("LOT_SIZE"))
                        .findFirst().get();
                BigDecimal quantity = asset.getFree().divide(symbolFilterData.getStepSize()).setScale(0, RoundingMode.DOWN).multiply(symbolFilterData.getStepSize());
                // TODO: if max quantity is reached then do loop
                if(quantity.compareTo(symbolFilterData.getMaxQty()) <= 0 && quantity.compareTo(symbolFilterData.getMinQty()) >= 0) {
                    String url = "https://api.binance.com/api/v3/order/test";
//                    String url = "https://api.binance.com/api/v3/order";
                    String data = "symbol=" + symbol + "&side=SELL&type=MARKET&quantity=" + quantity.toPlainString() + "&recvWindow=5000&timestamp=" + serverTime;// + System.currentTimeMillis();
                    String signature = encode(BINANCE_API_SECRET, data, "HmacSHA256");
                    String result = executeRequest(url + "?" + data + "&signature=" + signature, "POST", apikeyheader);
                }
            }
        }
    }
}