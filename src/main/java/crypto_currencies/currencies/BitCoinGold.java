package crypto_currencies.currencies;

import crypto_currencies.CryptoCurrencies;
import crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;

import static crypto_currencies.AllCurrenciesShortNames.BTG;

public class BitCoinGold extends CryptoCurrencies {

    public BitCoinGold(CurrencyInformationRetriever currencyInformationRetriever) {
        super(currencyInformationRetriever);
        currentCurrencyShortNames = BTG;
    }
}
