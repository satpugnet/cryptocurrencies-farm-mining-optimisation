package crypto_currencies.currencies_retrieval;

import crypto_currencies.CurrenciesShortNames;

public interface CurrencyInformationRetriever {

    public Float getLiveValue(CurrenciesShortNames currencyComparedFrom, CurrenciesShortNames currencyComparedTo);
    public void changeCurrencyComparedTo(CurrenciesShortNames shortNames);
}
