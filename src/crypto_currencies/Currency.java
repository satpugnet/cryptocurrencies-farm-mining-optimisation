package crypto_currencies;

import crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;

import static crypto_currencies.AllCurrenciesShortNames.USD;

public abstract class Currency {

    protected AllCurrenciesShortNames currentCurrencyShortNames;
    protected CurrencyInformationRetriever currencyInformationRetriever;

    public Currency(CurrencyInformationRetriever currencyInformationRetriever) {
        this.currencyInformationRetriever = currencyInformationRetriever;
    }

    public Float getLiveValue(){
        return getLiveValue(USD);
    }

    public Float getLiveValue(AllCurrenciesShortNames currencyComparedFrom) {
        return currencyInformationRetriever.getLiveValue(currencyComparedFrom, currentCurrencyShortNames);
    }
}
