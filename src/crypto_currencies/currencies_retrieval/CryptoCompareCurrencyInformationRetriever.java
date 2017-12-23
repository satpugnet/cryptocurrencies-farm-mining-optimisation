package crypto_currencies.currencies_retrieval;

import Utils.URLConnection;
import crypto_currencies.CurrenciesShortNames;

public class CryptoCompareCurrencyInformationRetriever implements CurrencyInformationRetriever {

    @Override
    public Float getLiveValue(CurrenciesShortNames currencyComparedFrom, CurrenciesShortNames currencyComparedTo) {
        System.out.println(URLConnection.getRequest("https://min-api.cryptocompare.com/data/price?fsym=" +
            currencyComparedFrom + "&tsyms=" + currencyComparedTo));
        return null;
    }

    @Override
    public void changeCurrencyComparedTo(CurrenciesShortNames shortNames) {

    }
}
