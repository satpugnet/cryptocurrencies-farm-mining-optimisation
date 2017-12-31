package Server.dataExchangeAnalyser;

import crypto_currencies.currencies_retrieval.CoinWarzCurrencyInformationRetriever;
import org.apache.log4j.Logger;

public class DataExchangeMediumFactory {

    private final static Logger logger = Logger.getLogger(CoinWarzCurrencyInformationRetriever.class);

    public static DataExchangeMedium getDataExchangeMedium(String request) {
        if (request == null) {
            logger.warn("Empty request content");
            return null;
        }
        if (isJson(request)) {
            return new JsonAnalyser(request);
        } else {
            logger.warn("Unrecognised DataExchangeMedium for factory");
            return null;
        }
    }

    private static boolean isJson(String request) {
        return request.charAt(0) == '{';
    }
}

