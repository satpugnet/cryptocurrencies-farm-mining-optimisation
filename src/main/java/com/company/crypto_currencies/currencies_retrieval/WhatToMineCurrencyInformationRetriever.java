package com.company.crypto_currencies.currencies_retrieval;

import com.company.Server.JsonFormat.General.MinedCurrencyShortName;
import com.company.Utils.URLConnection;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import static com.company.Server.JsonFormat.General.MinedCurrencyShortName.BTC;

public class WhatToMineCurrencyInformationRetriever implements CurrencyInformationRetriever {

    final static Logger logger = Logger.getLogger(WhatToMineCurrencyInformationRetriever.class);


    @Override
    public double getLiveExchange(MinedCurrencyShortName currencyComparedFrom) {
        double exchangeRate = findInformationInData(currencyComparedFrom, "exchange_rate");
        if(currencyComparedFrom != BTC) {
            exchangeRate *= getLiveExchange(BTC);
        }
        logger.info("WhatToMine, Getting a live exchange rate for " + currencyComparedFrom + " of " + exchangeRate);
        return exchangeRate;
    }


    @Override
    public void setDataMinUpdatingRate(long timeInMilliseconds) {

    }

    @Override
    public double getDifficulty(MinedCurrencyShortName currentMinedCurrencyShortNames) {
        double difficulty = findInformationInData(currentMinedCurrencyShortNames, "difficulty");
        logger.info("WhatToMine, Getting a difficulty for " + currentMinedCurrencyShortNames + " of " + difficulty);
        return difficulty;
    }

    @Override
    public double getBlockReward(MinedCurrencyShortName currentMinedCurrencyShortNames) {
        double blockReward = findInformationInData(currentMinedCurrencyShortNames, "block_reward");
        logger.info("WhatToMine, Getting block reward for " + currentMinedCurrencyShortNames + " of " + blockReward);
        return blockReward;
    }

    @Override
    public List<MinedCurrencyShortName> getOrderedListRecommendedMining() {
        logger.info("WhatToMine, Getting an ordered list of recommended mining cryptocurrencies");
        List<MinedCurrencyShortName> currencies = new LinkedList<>();
        String allCoinData = URLConnection.getRequest("http://WhatToMine.com/coins.json");
        int currentIndex;

        while((currentIndex = allCoinData.indexOf("tag")) != -1) {
            allCoinData = allCoinData.substring(currentIndex + 6);
            String shortName = allCoinData.substring(0, allCoinData.indexOf("\""));
            MinedCurrencyShortName minedCurrencyShortName;
            try {
                minedCurrencyShortName = MinedCurrencyShortName.valueOf(shortName);
            } catch (Exception e) {
                continue;
            }
            currencies.add(minedCurrencyShortName);
        }
        logger.info("WhatToMine, Completed the getting of an ordered list of recommended mining cryptocurrencies: " + currencies);
        return currencies;
    }

    private double findInformationInData(MinedCurrencyShortName currencyComparedFrom, String informationToFind) {
        JSONObject json = getCoinJson(currencyComparedFrom);
        try {
            return json.getDouble(informationToFind);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private JSONObject getCoinJson(MinedCurrencyShortName currencyComparedFrom) {
        int coinvalue = convertNameToValue(currencyComparedFrom);
        String jsonReply = URLConnection.getRequest("http://WhatToMine.com/coins/" + coinvalue + ".json");
        JSONObject json = null;
        try {
            json = new JSONObject(jsonReply);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    private int convertNameToValue(MinedCurrencyShortName currencyComparedFrom) {
        switch (currencyComparedFrom) {
            case ETH: return 151;
            case BTC: return 1;
            case BTG: return 214;
            case ETC: return 162;
            case XMR: return 101;
            case ZEC: return 166;
            case ZEN: return 185;
        }
        return 0;
    }
}
