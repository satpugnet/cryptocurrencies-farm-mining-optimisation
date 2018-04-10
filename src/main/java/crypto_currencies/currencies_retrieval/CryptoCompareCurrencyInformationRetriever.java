package crypto_currencies.currencies_retrieval;

import Utils.URLConnection;
import crypto_currencies.CurrenciesShortName;

import java.util.List;

public class CryptoCompareCurrencyInformationRetriever implements CurrencyInformationRetriever {

    @Override
    public double getLiveExchange(CurrenciesShortName currencyComparedFrom) {
//        System.out.println(URLConnection.getRequest("https://min-api.cryptocompare.com/data/price?fsym=USD&tsyms=" + currencyComparedFrom));
        return -1;
    }

    @Override
    public void setDataMinUpdatingRate(long timeInMilliseconds) {

    }

    @Override
    public double getDifficulty(CurrenciesShortName currentCurrencyShortNames) {
        return 0;
    }

    @Override
    public double getBlockReward(CurrenciesShortName currentCurrencyShortNames) {
        return 0;
    }

    @Override
    public List<CurrenciesShortName> getOrderedListRecommendedMining() {
        return null;
    }
}
