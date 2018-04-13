package com.company.Utils;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLConnection {

    final static Logger logger = Logger.getLogger(URLConnection.class);

    //TODO: remove hardcoded data
    public static String getRequest(String urlToRead) {
        try{
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlToRead);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            conn.addRequestProperty("Accept", "*/*");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            logger.trace("Sending a get request for the following url " + urlToRead + " with a response of " + result);
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Get request failed to the following url " + urlToRead);
            return null;
        }
    }

    public static String getTestRequest(String urlToRead) {
        return "{\"Success\":true,\"Message\":\"996 api calls remaining.\",\"Data\":[{\"CoinName\":\"Ethereum\",\"CoinTag\":\"ETH\",\"Algorithm\":\"EtHash\",\"Difficulty\":2.09296931161813E+15,\"BlockReward\":3,\"BlockCount\":4897805,\"ProfitRatio\":138.23831985400963,\"AvgProfitRatio\":112.22945823798447,\"Exchange\":\"CEX.io\",\"ExchangeRate\":0.09154385,\"ExchangeVolume\":2612.243383,\"IsBlockExplorerOnline\":true,\"IsExchangeOnline\":true,\"Message\":\"\",\"BlockTimeInSeconds\":15,\"HealthStatus\":\"Healthy\"},{\"CoinName\":\"Ethereum-Classic\",\"CoinTag\":\"ETC\",\"Algorithm\":\"EtHash\",\"Difficulty\":101346450673565.98,\"BlockReward\":4,\"BlockCount\":5191318,\"ProfitRatio\":100.82628511183562,\"AvgProfitRatio\":84.1280586378334,\"Exchange\":\"Bittrex\",\"ExchangeRate\":0.00249528,\"ExchangeVolume\":511345.34243673,\"IsBlockExplorerOnline\":true,\"IsExchangeOnline\":true,\"Message\":\"\",\"BlockTimeInSeconds\":15,\"HealthStatus\":\"Healthy\"},{\"CoinName\":\"Bitcoin\",\"CoinTag\":\"BTC\",\"Algorithm\":\"SHA-256\",\"Difficulty\":1931136454487.72,\"BlockReward\":12.5,\"BlockCount\":503925,\"ProfitRatio\":100,\"AvgProfitRatio\":100,\"Exchange\":\"CEX.io\",\"ExchangeRate\":15020.36,\"ExchangeVolume\":67248.25806437,\"IsBlockExplorerOnline\":true,\"IsExchangeOnline\":true,\"Message\":\"\",\"BlockTimeInSeconds\":600,\"HealthStatus\":\"Healthy\"}]}";
    }
}
