package crypto_currencies.currencies_retrieval;

import crypto_currencies.AllCurrenciesShortNames;

public interface CurrencyInformationRetriever {

    public Float getLiveValue(AllCurrenciesShortNames currencyComparedFrom, AllCurrenciesShortNames currencyComparedTo);
    public void changeCurrencyComparedTo(AllCurrenciesShortNames shortNames);
}
