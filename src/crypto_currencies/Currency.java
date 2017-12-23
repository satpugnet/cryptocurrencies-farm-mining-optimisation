package crypto_currencies;

import crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;

import static crypto_currencies.CurrenciesShortNames.USD;

public abstract class Currency {

    protected CurrenciesShortNames currentCurrencyShortNames;
    protected CurrencyInformationRetriever currencyInformationRetriever;

    public Currency(CurrencyInformationRetriever currencyInformationRetriever) {
        this.currencyInformationRetriever = currencyInformationRetriever;
    }

    public Float getLiveValue(){
        return getLiveValue(USD);
    }

    public Float getLiveValue(CurrenciesShortNames currencyComparedTo) {
        return currencyInformationRetriever.getLiveValue(currentCurrencyShortNames, currencyComparedTo);
    }
}
