package com.company.crypto_currencies.currencies;

import com.company.crypto_currencies.CryptoCurrencies;
import com.company.crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;

import static com.company.crypto_currencies.CurrencyShortName.BTC;

public class Bitcoin extends CryptoCurrencies {

    public Bitcoin(CurrencyInformationRetriever currencyInformationRetriever) {
        super(currencyInformationRetriever);
        currentCurrencyShortNames = BTC;
    }
}
