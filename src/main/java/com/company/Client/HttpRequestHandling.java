package com.company.Client;

import com.company.CryptoExchange.Binance.BinanceApiInterface;
import com.google.common.collect.ImmutableMap;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpRequestHandling {

    private final static Logger logger = Logger.getLogger(HttpRequestHandling.class);

    public static String executeRequest(String urlToRead, String requestMethod, ImmutableMap<String, String> headers) {
        logger.trace("Sending a post request for the following url " + urlToRead);
        try{
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlToRead);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod(requestMethod);
            if(headers != null) {
                headers.forEach((k,v)->conn.setRequestProperty(k, v));
            }

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();

            logger.trace("Post request got the following response: " + result);
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Get request failed to the following url " + urlToRead);
            return "";
        }
    }
}
