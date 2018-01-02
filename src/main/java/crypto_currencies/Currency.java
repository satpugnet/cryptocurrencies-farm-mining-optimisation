package crypto_currencies;

import crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;

public abstract class Currency {

    protected AllCurrenciesShortNames currentCurrencyShortNames;
    protected CurrencyInformationRetriever currencyInformationRetriever;

    public Currency(CurrencyInformationRetriever currencyInformationRetriever) {
        this.currencyInformationRetriever = currencyInformationRetriever;
    }

    public double getLiveValue() {
        return currencyInformationRetriever.getLiveExchange(currentCurrencyShortNames);
    }
}
