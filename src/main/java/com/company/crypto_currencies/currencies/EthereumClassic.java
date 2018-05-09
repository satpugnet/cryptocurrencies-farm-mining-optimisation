package com.company.crypto_currencies.currencies;

import com.company.crypto_currencies.CryptoCurrencies;
import com.company.crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;

import static com.company.crypto_currencies.CurrencyShortName.ETC;

public class EthereumClassic extends CryptoCurrencies {

    public EthereumClassic(CurrencyInformationRetriever currencyInformationRetriever) {
        super(currencyInformationRetriever);
        currentCurrencyShortNames = ETC;
    }
}
