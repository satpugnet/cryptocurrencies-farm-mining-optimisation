package crypto_currencies;

import crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;

public abstract class CryptoCurrencies extends Currency {

    public CryptoCurrencies(CurrencyInformationRetriever currencyInformationRetriever) {
        super(currencyInformationRetriever);
    }

    public double getDifficulty() {
        return currencyInformationRetriever.getDifficulty(currentCurrencyShortNames);
    }

    public double getBlockReward() {
        return currencyInformationRetriever.getBlockReward(currentCurrencyShortNames);
    }
}
