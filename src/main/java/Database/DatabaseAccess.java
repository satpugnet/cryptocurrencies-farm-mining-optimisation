package Database;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

public class DatabaseAccess {

    public static String getConfigField(String userEmail, String field) {
        try {
            Connection conn = getConnection();

            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM configurations JOIN users ON configurations.user_id=users.id WHERE email=\'" + userEmail + "\'";
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();
            String fieldValue  = rs.getString(field);

            rs.close();
            stmt.close();
            conn.close();

            return fieldValue;
        } catch (URISyntaxException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Connection getConnection() throws URISyntaxException, SQLException {
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
