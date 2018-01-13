package crypto_currencies.currencies;

import crypto_currencies.CryptoCurrencies;
import crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;

import static crypto_currencies.CurrenciesShortName.ZEC;

public class ZCash extends CryptoCurrencies {

    public ZCash(CurrencyInformationRetriever currencyInformationRetriever) {
        super(currencyInformationRetriever);
        currentCurrencyShortNames = ZEC;
    }
}
