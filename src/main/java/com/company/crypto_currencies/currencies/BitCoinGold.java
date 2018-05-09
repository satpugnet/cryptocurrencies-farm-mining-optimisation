package com.company.crypto_currencies.currencies;

import com.company.crypto_currencies.CryptoCurrencies;
import com.company.crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;

import static com.company.crypto_currencies.CurrencyShortName.BTG;

public class BitCoinGold extends CryptoCurrencies {

    public BitCoinGold(CurrencyInformationRetriever currencyInformationRetriever) {
        super(currencyInformationRetriever);
        currentCurrencyShortNames = BTG;
    }
}
