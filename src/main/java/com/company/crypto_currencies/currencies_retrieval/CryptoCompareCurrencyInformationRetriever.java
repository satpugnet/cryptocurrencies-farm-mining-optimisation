package com.company.crypto_currencies.currencies_retrieval;

import com.company.Server.JsonFormat.General.MinedCurrencyShortName;

import java.util.List;

public class CryptoCompareCurrencyInformationRetriever implements CurrencyInformationRetriever {

    @Override
    public double getLiveExchange(MinedCurrencyShortName currencyComparedFrom) {
//        System.out.println(URLConnection.getRequest("https://min-api.cryptocompare.com/data/price?fsym=USD&tsyms=" + currencyComparedFrom));
        return -1;
    }

    @Override
    public void setDataMinUpdatingRate(long timeInMilliseconds) {

    }

    @Override
    public double getDifficulty(MinedCurrencyShortName currentCurrencyShortNames) {
        return 0;
    }

    @Override
    public double getBlockReward(MinedCurrencyShortName currentCurrencyShortNames) {
        return 0;
    }

    @Override
    public List<MinedCurrencyShortName> getOrderedListRecommendedMining() {
        return null;
    }
}
