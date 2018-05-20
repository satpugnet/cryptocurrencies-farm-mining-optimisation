package com.company.crypto_currencies;

import com.company.crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;

public abstract class CryptoCurrencies extends Currency {

    public CryptoCurrencies(CurrencyInformationRetriever currencyInformationRetriever) {
        super(currencyInformationRetriever);
    }

    public double getDifficulty() {
        return currencyInformationRetriever.getDifficulty(currentMinedCurrencyShortNames);
    }

    public double getBlockReward() {
        return currencyInformationRetriever.getBlockReward(currentMinedCurrencyShortNames);
    }
}
