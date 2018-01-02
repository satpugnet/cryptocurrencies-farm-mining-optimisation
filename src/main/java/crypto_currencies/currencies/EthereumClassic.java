package crypto_currencies.currencies;

import crypto_currencies.CryptoCurrencies;
import crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;

import static crypto_currencies.AllCurrenciesShortNames.ETC;

public class EthereumClassic extends CryptoCurrencies {

    public EthereumClassic(CurrencyInformationRetriever currencyInformationRetriever) {
        super(currencyInformationRetriever);
        currentCurrencyShortNames = ETC;
    }
}
