package crypto_currencies.currencies_retrieval;

import crypto_currencies.CurrenciesShortName;

import java.util.List;

public interface CurrencyInformationRetriever {

    public double getLiveExchange(CurrenciesShortName currencyComparedFrom);
    public void setDataMinUpdatingRate(long timeInMilliseconds);
    double getDifficulty(CurrenciesShortName currentCurrencyShortNames);
    double getBlockReward(CurrenciesShortName currentCurrencyShortNames);
    public List<CurrenciesShortName> getOrderedListRecommendedMining();
}
