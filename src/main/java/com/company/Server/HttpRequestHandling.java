package com.company.Server;

import com.company.Database.DatabaseAccessor;
import com.company.Server.AccessSecurity.RequestAuthorisation;
import com.company.Server.JsonFormat.ClientJson.MiningConfigurationRequest;
import com.company.Server.JsonFormat.ClientJson.ReportMiningDiagnosisRequest;
import com.company.Server.JsonFormat.ServerJson.MiningConfigurationResponse;
import com.company.Server.RequestHandler.CryptoCurrencyOptimalMiningConfigHandler;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;


// TODO: look at jetty and use itpor
public class HttpRequestHandling {

    private final static Logger logger = Logger.getLogger(HttpRequestHandling.class);

    //TODO clean this function
    public static void startServer(int portNumber) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(portNumber), 0);
        server.createContext("/", new ClientConfigOptimal());
        server.setExecutor(null); // creates a default executor
        server.start();
        logger.info("Http server running on port " + portNumber);
    }

    public static class ClientConfigOptimal implements HttpHandler {
        @Override
        public void handle(HttpExchange t) {
            try {
                if (t.getRequestMethod().equalsIgnoreCase("PUT")) {
                    handleMiningDiagnosis(t);
                } else if (t.getRequestMethod().equalsIgnoreCase("POST")) {
                    handleMiningConfigRequest(t);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        private void handleMiningConfigRequest(HttpExchange t) throws IOException {
            logger.info("Optimal mining config request received");
            // TODO: convert request body bytes to String text
            InputStream rb = t.getRequestBody();
            String requestBody = IOUtils.toString(rb, StandardCharsets.US_ASCII);
            rb.close();
            Gson g = new Gson();
            MiningConfigurationRequest properties = g.fromJson(requestBody, MiningConfigurationRequest.class);
            if (!RequestAuthorisation.hasAuthorisedId(properties.getUserEmail())) {
                t.sendResponseHeaders(404, 0);
                t.getResponseBody().close();
                logger.warn("Unauthorised request received");
                return;
            } else {
                MiningConfigurationResponse miningConfigurationResponse = new CryptoCurrencyOptimalMiningConfigHandler().handle(properties);
                String response = g.toJson(miningConfigurationResponse, MiningConfigurationResponse.class);
                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }

            logger.info("Optimal mining config request completed");
        }

        private void handleMiningDiagnosis(HttpExchange t) throws IOException {
            logger.info("Mining diagnosis request received");
            InputStream rb = t.getRequestBody();
            String requestBody = IOUtils.toString(rb, StandardCharsets.US_ASCII);
            rb.close();
            Gson g = new Gson();
            ReportMiningDiagnosisRequest properties = g.fromJson(requestBody, ReportMiningDiagnosisRequest.class);
            DatabaseAccessor db = new DatabaseAccessor(properties.getUserEmail());
            db.updateOrInsertWorkerGraphicCard(properties.getWorkerName(), properties.getData().getGpu(), properties.getData().getCurrency().toString(), properties.getData().getHashRate());
            t.sendResponseHeaders(200, 0);
            t.getResponseBody().close();
            logger.info("Mining diagnosis request completed");
        }
    }
}
