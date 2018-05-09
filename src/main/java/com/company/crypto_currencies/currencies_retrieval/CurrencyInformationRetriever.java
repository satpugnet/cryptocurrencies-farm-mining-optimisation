package com.company.crypto_currencies.currencies_retrieval;

import com.company.crypto_currencies.CurrencyShortName;

import java.util.List;

public interface CurrencyInformationRetriever {

    public double getLiveExchange(CurrencyShortName currencyComparedFrom);
    public void setDataMinUpdatingRate(long timeInMilliseconds);
    double getDifficulty(CurrencyShortName currentCurrencyShortNames);
    double getBlockReward(CurrencyShortName currentCurrencyShortNames);
    public List<CurrencyShortName> getOrderedListRecommendedMining();
}
