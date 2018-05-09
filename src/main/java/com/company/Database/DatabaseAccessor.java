package com.company.Database;

import com.company.Server.JsonFormat.ConfigRequest.SystemConfig.GPU.GraphicCard;
import com.company.crypto_currencies.CurrencyShortName;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static com.company.Database.QueryType.QUERY;
import static com.company.Database.QueryType.UPDATE;
import static com.company.Variables.WORKER_TABLE_DATA_TIMEOUT;


public class DatabaseAccessor {

    final static Logger logger = Logger.getLogger(DatabaseAccessor.class);

    private String userEmail;
    private static Connection conn;
    private Statement stmt = null;
    private ResultSet rs = null;

    // TODO: refactor (use of email in constructor might not be best structure)
    public DatabaseAccessor(String userEmail) {
        this.userEmail = userEmail;
        try {
            if(conn == null) {
                conn = getConnection();
            }
        } catch (URISyntaxException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<GraphicCardLiveData> getOrderedListCurrencyToMine(GraphicCard graphicCard) {
        logger.info("Getting ordered list of currencies to mine for graphic card: " + graphicCard);
        String sql = "SELECT * " +
                "FROM \"" + graphicCard + "_live_data\" " +
                "ORDER BY ranking";
        ResultSet graphicCardLiveData = executeRequest(sql, QUERY);

        List<GraphicCardLiveData> result = new LinkedList<>();
        try {
            while(graphicCardLiveData.next()) {
                CurrencyShortName currency = CurrencyShortName.valueOf(graphicCardLiveData.getString("currency"));
                BigDecimal profit_per_second = new BigDecimal(graphicCardLiveData.getString("profit_per_second"));
                result.add(GraphicCardLiveData.builder().currencyShortName(currency).profitPerSecond(profit_per_second).build());
            }
        } catch (SQLException e) {
            logger.warn("SQL exception while getting ordered list of currencies (" + sql + "): " + e);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        closeStatementAndResultSet();

        return result;
    }

    public void reactualiseWorkerTable() {
        logger.info("Cleaning workers table");
        String sql = "UPDATE workers SET hashrate=0.0 WHERE timestamp < (CURRENT_TIMESTAMP - INTERVAL ' " + WORKER_TABLE_DATA_TIMEOUT +  " milliseconds');";
        executeRequest(sql, UPDATE);
        // TODO: clean the workers_configuration as well
    }

    public void updateOrInsertWorker(String workerName, String currency, Float hashrate) {
        logger.info("Updating worker table with: " + workerName + ", " + currency + ", " + hashrate);
        // Create or update worker
        String sql = "INSERT INTO workers (user_id, worker_name, mined_currency, hashrate, timestamp) " +
                "VALUES ((SELECT id FROM users WHERE email='" + userEmail + "'), '" + workerName + "', '" + currency + "', " + hashrate + ", CURRENT_TIMESTAMP) " +
                "ON CONFLICT (user_id, worker_name) " +
                "DO UPDATE SET user_id=(SELECT id FROM users WHERE email='" + userEmail + "'), " +
                "    worker_name='" + workerName + "', mined_currency='" + currency + "', hashrate=" + hashrate + ", timestamp=CURRENT_TIMESTAMP " +
                "RETURNING workers.id";
        ResultSet responseWorkerTable = executeRequest(sql, QUERY);
        String worker_id = null;
        try {
            worker_id = responseWorkerTable.getString("id");
        } catch (SQLException e) {
            logger.warn("SQL exception while inserting worker configurations (" + sql + "): " + e);
        }
        closeStatementAndResultSet();
        // TODO: find a better way to initialise (no need to redo on every hashrate update) (make the client send on request when booting to create the record in the db same for workers

        logger.info("Inserting into workers_configuration");
        // Create workers_configuration if non-existing
        String sql2 = "INSERT INTO workers_configuration (worker_id,activate_mining) " +
                "VALUES (" + worker_id + ", true) " +
                "ON CONFLICT (worker_id) DO UPDATE SET worker_id=excluded.worker_id " +
                "RETURNING workers_configuration.id";
        ResultSet responseWorkerConfiguration = executeRequest(sql2, QUERY);
        String worker_configuration_id = null;
        try {
            worker_configuration_id = responseWorkerConfiguration.getString("id");
        } catch (SQLException e) {
            logger.warn("SQL exception while getting the worker configurations (" + sql + "): " + e);
        }
        closeStatementAndResultSet();

        logger.info("Inserting into mined_cryptocurrencies");
        String sql3 = "INSERT INTO mined_cryptocurrencies (worker_configuration_id) " +
                "VALUES (" + worker_configuration_id + ") " +
                "ON CONFLICT DO NOTHING";
        executeRequest(sql3, UPDATE);
    }

    public String getWorkerConfigFieldString(String workerName, String field) {
        logger.info("Getting config field: " + field);
        String sql = "SELECT * " +
                "FROM users " +
                "JOIN workers ON users.id=workers.user_id " +
                "JOIN workers_configuration ON workers.id=workers_configuration.worker_id " +
                "JOIN mined_cryptocurrencies ON workers_configuration.id=mined_cryptocurrencies.worker_configuration_id " +
                "WHERE workers.worker_name=\'" + workerName + "\' AND users.email=\'" + userEmail + "\'";
        ResultSet response = executeRequest(sql, QUERY);
        try {
            return response.getString(field);
        } catch (SQLException e) {
            logger.warn("SQL exception while getting the worker config fields " + field + "(" + sql + "): " + e);
        }
        closeStatementAndResultSet();
        return null;
    }

    public Boolean getWorkerConfigFieldBoolean(String workerName, String field) {
        String booleanField = getWorkerConfigFieldString(workerName, field);
        if(booleanField != null) {
            return booleanField.equals("t");
        }
        return false;
    }

    private ResultSet executeRequest(String sql, QueryType requestType) {

        stmt = null;
        rs = null;

        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            if(requestType == QUERY) {
                rs = stmt.executeQuery(sql);
                rs.next();
            } else if(requestType == UPDATE) {
                stmt.executeUpdate(sql);
            }

        } catch (SQLException e) {
            logger.warn("SQL exception for request " + sql + " in the database request: \n" + e);
        }

        return rs;
    }

    private Connection getConnection() throws URISyntaxException, SQLException {
        String databaseURL = System.getenv("HEROKU_POSTGRESQL_MAROON_URL") != null ?
                System.getenv("HEROKU_POSTGRESQL_MAROON_URL"):
                "postgres://lmxhpacdmmgnfr:0f78fab407cdf1699b50b2fec55a742f65ab1a5cfbbb2c166394a09eb6acf652@ec2-54-247-89-189.eu-west-1.compute.amazonaws.com:5432/denvqvnkc5gm9j?ssl=true";
        URI dbUri = new URI(databaseURL);

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        String dbUrlExtended = dbUrl + "?sslmode=require&user=" + username + "&password=" + password;

        return DriverManager.getConnection(dbUrlExtended);
    }

    private void closeStatementAndResultSet() {
        try {
            if (rs != null) { rs.close(); }
            if (stmt != null) { stmt.close(); }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
