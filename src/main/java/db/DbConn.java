package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConn {

    public static Connection connect() {
        try {
            return DriverManager.getConnection(ConnDetails.URL, ConnDetails.USERNAME, ConnDetails.PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}
