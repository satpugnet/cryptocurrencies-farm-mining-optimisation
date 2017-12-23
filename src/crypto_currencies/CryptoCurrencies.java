package crypto_currencies;

import crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;

public abstract class CryptoCurrencies extends Currency {

    public CryptoCurrencies(CurrencyInformationRetriever currencyInformationRetriever) {
        super(currencyInformationRetriever);
    }
}
