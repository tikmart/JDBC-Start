package am.hitech.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private static Connection connection;

    public static final String url = "jdbc:mysql://localhost:3306/group1";
    public static final String username = "root";
    public static final String password = "";


    public static Connection getConnection() {

        try {
            if (connection == null || connection.isClosed()) {
                Class c =Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
            }
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
