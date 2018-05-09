package com.company.crypto_currencies;

import com.company.crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;

public abstract class Currency {

    protected CurrencyShortName currentCurrencyShortNames;
    protected CurrencyInformationRetriever currencyInformationRetriever;

    public Currency(CurrencyInformationRetriever currencyInformationRetriever) {
        this.currencyInformationRetriever = currencyInformationRetriever;
    }

    public double getLiveValue() {
        return currencyInformationRetriever.getLiveExchange(currentCurrencyShortNames);
    }
}
