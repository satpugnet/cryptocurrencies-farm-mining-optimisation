package com.company.crypto_currencies.currencies_retrieval;

import com.company.Server.JsonFormat.General.MinedCurrencyShortName;

import java.util.List;

public interface CurrencyInformationRetriever {

    public double getLiveExchange(MinedCurrencyShortName currencyComparedFrom);
    public void setDataMinUpdatingRate(long timeInMilliseconds);
    double getDifficulty(MinedCurrencyShortName currentCurrencyShortNames);
    double getBlockReward(MinedCurrencyShortName currentCurrencyShortNames);
    public List<MinedCurrencyShortName> getOrderedListRecommendedMining();
}
