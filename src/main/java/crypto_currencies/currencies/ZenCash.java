package crypto_currencies.currencies;

import crypto_currencies.CryptoCurrencies;
import crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;

import static crypto_currencies.CurrenciesShortName.ZEN;

public class ZenCash extends CryptoCurrencies {

    public ZenCash(CurrencyInformationRetriever currencyInformationRetriever) {
        super(currencyInformationRetriever);
        currentCurrencyShortNames = ZEN;
    }
}
