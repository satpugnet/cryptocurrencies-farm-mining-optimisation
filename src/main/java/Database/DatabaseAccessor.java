package Database;

import lombok.SneakyThrows;
import org.apache.log4j.Logger;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

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

    public Boolean updateWorkerTable(String workerName, String currency, Float hashrate) {
        logger.info("Updating worker table with: " + workerName + ", " + currency + ", " + hashrate);

        Statement stmt = null;
        ResultSet rs = null;
        String sql = null;

        try {
            stmt = conn.createStatement();
            sql = "INSERT INTO workers (user_id, worker_name, mined_currency, hashrate) " +
                    "VALUES ((SELECT id FROM users WHERE email='" + userEmail + "'), '" + workerName + "', '" + currency + "', " + hashrate + ") " +
                    "ON CONFLICT (user_id, worker_name) " +
                    "DO UPDATE SET user_id=(SELECT id FROM users WHERE email='" + userEmail + "'), " +
                    "    worker_name='" + workerName + "', mined_currency='" + currency + "', hashrate=" + hashrate;
            rs = stmt.executeQuery(sql);

            rs.next();

            return true;
        } catch (SQLException e) {
            logger.warn("SQL exception for request " + sql + " in the database request");
        } finally {
            try {
                if (rs != null) { rs.close(); }
                if (stmt != null) { stmt.close(); }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public Boolean getConfigFieldBoolean(String field) {
        String booleanField = getConfigFieldString(field);
        if(booleanField != null) {
            return booleanField.equals("t");
        }
        return false;
    }

    public String getConfigFieldString(String field) {
        logger.info("Getting config field: " + field);

        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            String sql = "SELECT * " +
                    "FROM configurations " +
                    "JOIN users ON users.id=configurations.user_id " +
                    "JOIN mined_cryptocurrencies ON configurations.id=mined_cryptocurrencies.configuration_id " +
                    "WHERE email=\'" + userEmail + "\'";
            rs = stmt.executeQuery(sql);

            rs.next();
            String fieldValue = rs.getString(field);

            return fieldValue;
        } catch (SQLException e) {
            logger.warn("SQL exception for field " + field + " in the database request");
        } finally {
            try {
                if (rs != null) { rs.close(); }
                if (stmt != null) { stmt.close(); }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
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
