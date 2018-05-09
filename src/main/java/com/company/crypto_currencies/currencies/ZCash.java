package com.company.crypto_currencies.currencies;

import com.company.crypto_currencies.CryptoCurrencies;
import com.company.crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;

import static com.company.crypto_currencies.CurrencyShortName.ZEC;

public class ZCash extends CryptoCurrencies {

    public ZCash(CurrencyInformationRetriever currencyInformationRetriever) {
        super(currencyInformationRetriever);
        currentCurrencyShortNames = ZEC;
    }
}
