package com.company.CryptoExchange.Bittrex;

import com.company.CryptoExchange.Bittrex.Json.Balances.BittrexBalances;
import com.company.CryptoExchange.Bittrex.Json.Balances.Result.CurrencyData;
import com.company.CryptoExchange.Bittrex.Json.OrderBook.BittrexOrderBookData;
import com.company.CryptoExchange.Bittrex.Json.OrderBook.Result.BuyData.BuyData;
import com.company.CryptoExchange.Bittrex.Json.SellLimit.BittrexSellLimit;
import com.company.CryptoExchange.Exchange;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

import static com.company.Client.HttpRequestHandling.executeRequest;
import static com.company.Utils.HmacEncoding.encode;
import static com.company.Variables.BITTREX_API_KEY;
import static com.company.Variables.BITTREX_API_SECRET;

public class BittrexApiInterface implements Exchange {

    private final static Logger logger = Logger.getLogger(BittrexApiInterface.class);

    public void convertFundToBitcoin() {
        logger.info("Converting fund to BTC on Bittrex");
        Gson g = new Gson();
        String balances = executeApiRequest("https://bittrex.com/api/v1.1/account/getbalances?apikey=" + BITTREX_API_KEY + "&nonce=nonce");
        BittrexBalances bittrexBalances = g.fromJson(balances, BittrexBalances.class);
        if(!bittrexBalances.isSuccess()) {
            logger.error("Could not get the balances available on Bittrex account, the following message was returned: " + bittrexBalances.getMessage());
        }

        for(CurrencyData currencyData: bittrexBalances.getResult()) {
            if(!currencyData.getCurrency().equals("BTC")) {
                String rate = findRate(currencyData.getBalance());
                String quantity = currencyData.getBalance().toPlainString();
                String currency = currencyData.getCurrency();

                String sellResult = executeApiRequest("https://bittrex.com/api/v1.1/market/selllimit?apikey=" + BITTREX_API_KEY + "&market=BTC-"
                        + currency + "&quantity=" + quantity + "&rate=" + rate + "&nonce=nonce");
                BittrexSellLimit bittrexSellLimit = g.fromJson(sellResult, BittrexSellLimit.class);

                if(bittrexSellLimit.isSuccess()) {
                    logger.info("Successfully converted " + quantity + " " + currency + " to BTC on Bittrex");
                } else {
                    logger.warn("Could not convert " + currency + " to BTC, the following message was returned: " + bittrexSellLimit.getMessage()
                            + ", " + currencyData.getBalance() + " " + currency + " left on Bittrex");
                }
            }
        }
        logger.info("Done converting fund on Bittrex");
    }

    private String findRate(BigDecimal quantity) {
        Gson g = new Gson();

        String orderBook = executeApiRequest("https://bittrex.com/api/v1.1/public/getorderbook?market=BTC-LTC&type=both");
        BittrexOrderBookData bittrexOrderBookData = g.fromJson(orderBook, BittrexOrderBookData.class);
        if(!bittrexOrderBookData.isSuccess()) {
            logger.error("Could not get the order book on  Bittrex, the following message was returned: " + bittrexOrderBookData.getMessage());
        }

        BigDecimal totalQuantity = BigDecimal.ZERO;
        List<BuyData> buyDatas = bittrexOrderBookData.getResult().getBuy();
        for (BuyData buyData: buyDatas) {
            totalQuantity = totalQuantity.add(buyData.getQuantity());
            if(totalQuantity.compareTo(quantity) > 0) {
                return buyData.getRate().toPlainString();
            }
        }
        return buyDatas.get(buyDatas.size() - 1).getRate().toPlainString();
    }

    private String executeApiRequest(String url) {
        String signature = encode(BITTREX_API_SECRET, url, "HmacSHA512");
        ImmutableMap<String, String> apisignheader = new ImmutableMap.Builder<String, String>().put("apisign", signature).build();
        String result = executeRequest(url, "GET", apisignheader);

        return result;
    }

}