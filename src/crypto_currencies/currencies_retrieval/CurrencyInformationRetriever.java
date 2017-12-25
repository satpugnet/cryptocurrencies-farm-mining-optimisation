package crypto_currencies.currencies_retrieval;

import crypto_currencies.AllCurrenciesShortNames;

public interface CurrencyInformationRetriever {

    public double getLiveExchange(AllCurrenciesShortNames currencyComparedFrom);
    public void setDataUpdatingRate(long timeInMilliseconds);
    double getDifficulty(AllCurrenciesShortNames currentCurrencyShortNames);
}
