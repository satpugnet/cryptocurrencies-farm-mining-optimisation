package crypto_currencies.currencies;

import crypto_currencies.CryptoCurrencies;
import crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;

import static crypto_currencies.CurrenciesShortName.XMR;

public class Monero extends CryptoCurrencies {

    public Monero(CurrencyInformationRetriever currencyInformationRetriever) {
        super(currencyInformationRetriever);
        currentCurrencyShortNames = XMR;

    }
}
