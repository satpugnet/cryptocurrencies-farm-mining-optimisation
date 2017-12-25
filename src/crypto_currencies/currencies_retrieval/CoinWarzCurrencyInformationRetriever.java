package crypto_currencies.currencies_retrieval;

import Utils.URLConnection;
import crypto_currencies.AllCurrenciesShortNames;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static crypto_currencies.AllCurrenciesShortNames.BTC;

public class CoinWarzCurrencyInformationRetriever implements CurrencyInformationRetriever {

    final static Logger logger = Logger.getLogger(CoinWarzCurrencyInformationRetriever.class);

    private JSONObject profitabilityData;
    private long lastUpdate;
    private long updateRate;

    public CoinWarzCurrencyInformationRetriever(int updateRate) {
        profitabilityData = getProfitabilityData();
        this.updateRate = updateRate;
    }

    @Override
    public double getLiveExchange(AllCurrenciesShortNames currencyComparedFrom) {
        double exchangeRate = findInformationInData(currencyComparedFrom.toString(), "ExchangeRate");
        if(currencyComparedFrom != BTC) {
            exchangeRate *= findInformationInData(BTC.toString(), "ExchangeRate");
        }
        logger.info("Getting a live exchange rate for " + currencyComparedFrom + " of " + exchangeRate);
        return exchangeRate;
    }

    @Override
    public double getDifficulty(AllCurrenciesShortNames currentCurrencyShortNames) {
        double difficulty = findInformationInData(currentCurrencyShortNames.toString(), "Difficulty");
        logger.info("Getting a difficulty for " + currentCurrencyShortNames + " of " + difficulty);
        return difficulty;
    }

    private double findInformationInData(String CoinToFind, String valueToCompare) {
        try {
            JSONObject response = getProfitabilityData();
            JSONArray allCoinData = response.getJSONArray("Data");
            for (int i = 0; i < allCoinData.length(); i++) {
                JSONObject currentCoin = allCoinData.getJSONObject(i);
                if(currentCoin.getString("CoinTag").equals(CoinToFind)) {
                    return currentCoin.getDouble(valueToCompare);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void setDataUpdatingRate(long timeInMilliseconds) {

    }


    private JSONObject getProfitabilityData(){
        if(System.currentTimeMillis() - lastUpdate > updateRate) {
            logger.info("Re-updating the profitability data value");
            try {
                profitabilityData = new JSONObject("{}");
                profitabilityData = new JSONObject(URLConnection.getRequest("https://www.coinwarz.com/v1/api/profitability/?apikey=fc2b8d6412954170a9b1901c2c177566&algo=all"));
                if(!profitabilityData.getBoolean("Success")) {
                    logger.error("The data from Coinwarz could not be retrieved successfully and returned with the followning message :" + profitabilityData.getString("Message"));
                }
            } catch (JSONException e) {
                logger.error("The data from Coinwarz could not be loaded and parsed to a JSON object.");
                e.printStackTrace();
            }
        }
        lastUpdate = System.currentTimeMillis();
        return profitabilityData;
    }
}
