package com.company.crypto_currencies.currencies;

import com.company.crypto_currencies.CryptoCurrencies;
import com.company.crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;

import static com.company.Server.JsonFormat.General.MinedCurrencyShortName.BTG;


public class BitCoinGold extends CryptoCurrencies {

    public BitCoinGold(CurrencyInformationRetriever currencyInformationRetriever) {
        super(currencyInformationRetriever);
        currentMinedCurrencyShortNames = BTG;
    }
}
