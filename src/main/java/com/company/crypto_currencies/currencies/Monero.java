package com.company.crypto_currencies.currencies;

import com.company.crypto_currencies.CryptoCurrencies;
import com.company.crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;

import static com.company.Server.JsonFormat.General.MinedCurrencyShortName.XMR;

public class Monero extends CryptoCurrencies {

    public Monero(CurrencyInformationRetriever currencyInformationRetriever) {
        super(currencyInformationRetriever);
        currentMinedCurrencyShortNames = XMR;
    }
}
