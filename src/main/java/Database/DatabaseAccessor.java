package Database;

import lombok.SneakyThrows;
import org.apache.log4j.Logger;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

public class DatabaseAccessor {

    final static Logger logger = Logger.getLogger(DatabaseAccessor.class);

    String userEmail;

    public DatabaseAccessor(String userEmail) {
        this.userEmail = userEmail;
    }

    public Boolean getConfigFieldBoolean(String field) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * " +
                    "FROM configurations " +
                    "JOIN users ON users.id=configurations.user_id " +
                    "JOIN mined_cryptocurrencies ON configurations.id=mined_cryptocurrencies.configuration_id " +
                    "WHERE email=\'" + userEmail + "\'";
            rs = stmt.executeQuery(sql);

            rs.next();
            Boolean fieldValue = rs.getBoolean(field);

            return fieldValue;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            logger.warn("SQL exception for field " + field + " in the database request");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getConfigFieldString(String field) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * " +
                    "FROM configurations " +
                    "JOIN users ON users.id=configurations.user_id " +
                    "JOIN mined_cryptocurrencies ON configurations.id=mined_cryptocurrencies.configuration_id " +
                    "WHERE email=\'" + userEmail + "\'";
            rs = stmt.executeQuery(sql);

            rs.next();
            String fieldValue = rs.getString(field);

            return fieldValue;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            logger.warn("SQL exception for field " + field + " in the database request");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
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
