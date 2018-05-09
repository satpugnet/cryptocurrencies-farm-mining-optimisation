package com.company.crypto_currencies.currencies_retrieval;

import com.company.crypto_currencies.CurrencyShortName;

import java.util.List;

public class CryptoCompareCurrencyInformationRetriever implements CurrencyInformationRetriever {

    @Override
    public double getLiveExchange(CurrencyShortName currencyComparedFrom) {
//        System.out.println(URLConnection.getRequest("https://min-api.cryptocompare.com/data/price?fsym=USD&tsyms=" + currencyComparedFrom));
        return -1;
    }

    @Override
    public void setDataMinUpdatingRate(long timeInMilliseconds) {

    }

    @Override
    public double getDifficulty(CurrencyShortName currentCurrencyShortNames) {
        return 0;
    }

    @Override
    public double getBlockReward(CurrencyShortName currentCurrencyShortNames) {
        return 0;
    }

    @Override
    public List<CurrencyShortName> getOrderedListRecommendedMining() {
        return null;
    }
}
