package Server;

import OptimalMining.MiningConfig.MiningConfiguration;
import Server.AccessSecurity.RequestAuthorisation;
import Server.RequestHandler.CryptoCurrencyOptimalMiningConfigHandler;
import Server.dataExchangeAnalyser.DataExchangeMedium;
import Server.dataExchangeAnalyser.DataExchangeMediumFactory;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import crypto_currencies.currencies_retrieval.CoinWarzCurrencyInformationRetriever;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import static Server.AccessSecurity.AuthorisedRequests.OptimalCryptoMining;

public class HttpRequestHandling {

    private final static Logger logger = Logger.getLogger(CoinWarzCurrencyInformationRetriever.class);

    //TODO clean this function
    public static void main(String[] args) throws Exception {
        int portNumber = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(portNumber), 0);
        server.createContext("/", new ClientConfigOptimal());
        server.setExecutor(null); // creates a default executor
        server.start();
        logger.info("Http server running on port " + portNumber);
    }

    static class ClientConfigOptimal implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            // TODO: convert request body bytes to String text
            String requestBody = t.getRequestBody().toString();
            DataExchangeMedium dataExchangeMedium = DataExchangeMediumFactory.getDataExchangeMedium(requestBody);
            if (dataExchangeMedium == null || !RequestAuthorisation.hasAuthorisedId(dataExchangeMedium.getUserId(), OptimalCryptoMining)) {
                t.sendResponseHeaders(404, 0);
                t.getResponseBody().close();
                logger.warn("Unauthorised request received");
                return;
            }

            MiningConfiguration responseConfig = new CryptoCurrencyOptimalMiningConfigHandler().handle(dataExchangeMedium);
            String response = responseConfig.toJson().toString();
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}
