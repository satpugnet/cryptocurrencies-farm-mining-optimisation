package com.company.crypto_currencies;

import com.company.Server.JsonFormat.General.MinedCurrencyShortName;
import com.company.crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;

public abstract class Currency {

    protected MinedCurrencyShortName currentMinedCurrencyShortNames;
    protected CurrencyInformationRetriever currencyInformationRetriever;

    public Currency(CurrencyInformationRetriever currencyInformationRetriever) {
        this.currencyInformationRetriever = currencyInformationRetriever;
    }

    public double getLiveValue() {
        return currencyInformationRetriever.getLiveExchange(currentMinedCurrencyShortNames);
    }
}
