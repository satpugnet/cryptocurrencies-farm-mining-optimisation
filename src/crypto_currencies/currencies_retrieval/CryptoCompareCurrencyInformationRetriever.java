package crypto_currencies.currencies_retrieval;

import Utils.URLConnection;
import crypto_currencies.AllCurrenciesShortNames;

public class CryptoCompareCurrencyInformationRetriever implements CurrencyInformationRetriever {

    @Override
    public double getLiveExchange(AllCurrenciesShortNames currencyComparedFrom) {
        System.out.println(URLConnection.getRequest("https://min-api.cryptocompare.com/data/price?fsym=USD&tsyms=" + currencyComparedFrom));
        return -1;
    }

    @Override
    public void setDataUpdatingRate(long timeInMilliseconds) {

    }
}
