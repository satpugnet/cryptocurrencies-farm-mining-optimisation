package com.company.crypto_currencies.currencies;

import com.company.crypto_currencies.CryptoCurrencies;
import com.company.crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;

import static com.company.Server.JsonFormat.General.MinedCurrencyShortName.ETC;

public class EthereumClassic extends CryptoCurrencies {

    public EthereumClassic(CurrencyInformationRetriever currencyInformationRetriever) {
        super(currencyInformationRetriever);
        currentMinedCurrencyShortNames = ETC;
    }
}
