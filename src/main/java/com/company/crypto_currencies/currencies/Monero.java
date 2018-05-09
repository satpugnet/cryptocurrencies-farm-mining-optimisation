package com.company.crypto_currencies.currencies;

import com.company.crypto_currencies.CryptoCurrencies;
import com.company.crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;

import static com.company.crypto_currencies.CurrencyShortName.XMR;

public class Monero extends CryptoCurrencies {

    public Monero(CurrencyInformationRetriever currencyInformationRetriever) {
        super(currencyInformationRetriever);
        currentCurrencyShortNames = XMR;
    }
}
