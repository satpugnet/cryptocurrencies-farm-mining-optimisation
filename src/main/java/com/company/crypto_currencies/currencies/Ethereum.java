package com.company.crypto_currencies.currencies;

import com.company.crypto_currencies.CryptoCurrencies;
import com.company.crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;

import static com.company.Server.JsonFormat.General.MinedCurrencyShortName.ETH;

public class Ethereum extends CryptoCurrencies {

    public Ethereum(CurrencyInformationRetriever currencyInformationRetriever) {
        super(currencyInformationRetriever);
        currentMinedCurrencyShortNames = ETH;
    }
}
