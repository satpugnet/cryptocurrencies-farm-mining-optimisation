package com.company.Database;

import org.apache.log4j.Logger;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

import static com.company.Database.QueryType.QUERY;
import static com.company.Database.QueryType.UPDATE;
import static com.company.Variables.WORKER_TABLE_DATA_TIMEOUT;


public class DatabaseAccessor {

    final static Logger logger = Logger.getLogger(DatabaseAccessor.class);

    private String userEmail;
    private static Connection conn;

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

    public void reactualiseWorkerTable() {
        logger.info("Cleaning workers table");
        String sql = "UPDATE workers SET hashrate=0.0 WHERE timestamp < (CURRENT_TIMESTAMP - INTERVAL ' " + WORKER_TABLE_DATA_TIMEOUT +  " milliseconds');";
        executeRequest(sql, UPDATE, null);
    }

    public void insertWorkerTable(String workerName, String currency, Float hashrate) {
        logger.info("Updating worker table with: " + workerName + ", " + currency + ", " + hashrate);
        String sql = "INSERT INTO workers (user_id, worker_name, mined_currency, hashrate, timestamp) " +
                "VALUES ((SELECT id FROM users WHERE email='" + userEmail + "'), '" + workerName + "', '" + currency + "', " + hashrate + ", CURRENT_TIMESTAMP) " +
                "ON CONFLICT (user_id, worker_name) " +
                "DO UPDATE SET user_id=(SELECT id FROM users WHERE email='" + userEmail + "'), " +
                "    worker_name='" + workerName + "', mined_currency='" + currency + "', hashrate=" + hashrate + ", timestamp=CURRENT_TIMESTAMP";
        executeRequest(sql, UPDATE, null);
    }

    public String getConfigFieldString(String field) {
        logger.info("Getting config field: " + field);
        String sql = "SELECT * " +
                "FROM worker_configurations " +
                "JOIN users ON users.id=worker_configurations.user_id " +
                "JOIN mined_cryptocurrencies ON worker_configurations.id=mined_cryptocurrencies.configuration_id " +
                "WHERE email=\'" + userEmail + "\'";
        return executeRequest(sql, QUERY, field);
    }

    public Boolean getConfigFieldBoolean(String field) {
        String booleanField = getConfigFieldString(field);
        if(booleanField != null) {
            return booleanField.equals("t");
        }
        return false;
    }

    private String executeRequest(String sql, QueryType requestType, String field) {

        Statement stmt = null;
        ResultSet rs = null;
        String result = "";

        try {
            stmt = conn.createStatement();

            if(requestType == QUERY) {
                rs = stmt.executeQuery(sql);
                rs.next();
                result = rs.getString(field);
            } else if(requestType == UPDATE) {
                stmt.executeUpdate(sql);
            }

        } catch (SQLException e) {
            logger.warn("SQL exception for request " + sql + " in the database request: \n" + e);
        } finally {
            try {
                if (rs != null) { rs.close(); }
                if (stmt != null) { stmt.close(); }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private Connection getConnection() throws URISyntaxException, SQLException {
        String databaseURL = System.getenv("DATABASE_URL") != null ?
                System.getenv("DATABASE_URL"):
                "postgres://lncgqbiyoivknm:16497d98c045a638262b080a515986d172cadc0799e23f7ebc1cd225556116a7@ec2-54-217-214-201.eu-west-1.compute.amazonaws.com:5432/dabhdlnlb316fm?ssl=true";
        URI dbUri = new URI(databaseURL);

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        String dbUrlExtended = dbUrl + "?sslmode=require&user=" + username + "&password=" + password;

        return DriverManager.getConnection(dbUrlExtended);
    }
}
