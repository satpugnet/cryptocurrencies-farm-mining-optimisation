package com.company.crypto_currencies.currencies_retrieval;

import com.company.Utils.URLConnection;
import com.company.crypto_currencies.CurrencyShortName;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import static com.company.crypto_currencies.CurrencyShortName.BTC;

// TODO: process JSON with a framework
// TODO: remove duplication with whattomine.com
public class CoinWarzCurrencyInformationRetriever implements CurrencyInformationRetriever {

    final static Logger logger = Logger.getLogger(CoinWarzCurrencyInformationRetriever.class);

    private JSONObject profitabilityData;
    private long lastUpdate;
    private long updateRate;

    public CoinWarzCurrencyInformationRetriever() {
        profitabilityData = getProfitabilityData();
        this.updateRate = 10000;
    }

    public CoinWarzCurrencyInformationRetriever(int updateRate) {
        profitabilityData = getProfitabilityData();
        this.updateRate = updateRate;
    }

    @Override
    public double getLiveExchange(CurrencyShortName currencyComparedFrom) {
        double exchangeRate = findInformationInData(currencyComparedFrom.toString(), "ExchangeRate");
        if(currencyComparedFrom != BTC) {
            exchangeRate *= findInformationInData(BTC.toString(), "ExchangeRate");
        }
        logger.info("CoinWarz, Getting a live exchange rate for " + currencyComparedFrom + " of " + exchangeRate);
        return exchangeRate;
    }

    @Override
    public double getDifficulty(CurrencyShortName currentCurrencyShortNames) {
        double difficulty = findInformationInData(currentCurrencyShortNames.toString(), "Difficulty");
        logger.info("CoinWarz, Getting a difficulty for " + currentCurrencyShortNames + " of " + difficulty);
        return difficulty;
    }

    @Override
    public double getBlockReward(CurrencyShortName currentCurrencyShortNames) {
        double blockReward = findInformationInData(currentCurrencyShortNames.toString(), "Difficulty");
        logger.info("CoinWarz, Getting a blockReward for " + currentCurrencyShortNames + " of " + blockReward);
        return blockReward;
    }


    @Override
    public void setDataMinUpdatingRate(long timeInMilliseconds) {

    }

    @Override
    @SneakyThrows
    public List<CurrencyShortName> getOrderedListRecommendedMining() {
        logger.info("CoinWarz, Getting an ordered list of recommended mining cryptocurrencies");
        List<CurrencyShortName> currencies = new LinkedList<>();
        JSONObject response = getProfitabilityData();
        JSONArray allCoinData = response.getJSONArray("Data");
        for (int i = 0; i < allCoinData.length(); i++) {
            JSONObject currentCoin = allCoinData.getJSONObject(i);
            CurrencyShortName currencyShortName;
            try {
                currencyShortName = CurrencyShortName.valueOf(currentCoin.getString("CoinTag"));
            } catch (Exception e) {
                continue;
            }
            currencies.add(currencyShortName);
        }
        logger.info("CoinWarz, Completed the getting of an ordered list of recommended mining cryptocurrencies: " + currencies);
        return currencies;
    }

    private double findInformationInData(String CoinToFind, String valueToCompare) {
        try {
            JSONObject response = getProfitabilityData();
            JSONArray allCoinData = response.getJSONArray("Data");
            for (int i = 0; i < allCoinData.length(); i++) {
                JSONObject currentCoin = allCoinData.getJSONObject(i);
                if(currentCoin.getString("CoinTag").equals(CoinToFind)) {
                    return currentCoin.getDouble(valueToCompare);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private JSONObject getProfitabilityData() {
        if(System.currentTimeMillis() - lastUpdate > updateRate) {
            logger.info("CoinWarz, Re-updating the profitability data value");
            try {
                profitabilityData = new JSONObject("{}");
                String request = URLConnection.getRequest("https://www.coinwarz.com/v1/api/profitability/?apikey=fc2b8d6412954170a9b1901c2c177566&algo=all");
                if(request == null) {
                    logger.error("Coinwarz did not reply most likely all the request allowed for a single day have been used");
                }
                profitabilityData = new JSONObject(request);
                if(!profitabilityData.getBoolean("Success")) {
                    logger.error("CoinWarz, The data could not be retrieved successfully and returned with the followning message :" + profitabilityData.getString("Message"));
                }
            } catch (JSONException e) {
                logger.error("CoinWarz, The data could not be loaded and parsed to a JSON object.");
                e.printStackTrace();
            }
        }
        lastUpdate = System.currentTimeMillis();
        return profitabilityData;
    }
}
