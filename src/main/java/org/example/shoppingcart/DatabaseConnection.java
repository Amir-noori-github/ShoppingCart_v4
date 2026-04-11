package org.example.shoppingcart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Private constructor to prevent instantiation (SonarQube requirement)
    private DatabaseConnection() {
        throw new IllegalStateException("Utility class");
    }

    // Local development database URL
    private static final String URL =
            "jdbc:mariadb://localhost:3306/shopping_cart_localization";

    // Load credentials from environment variables (SonarQube requirement)
    private static final String USER = System.getenv("DB_USER");
    private static final String PASS = System.getenv("DB_PASSWORD");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
