package org.example.shoppingcart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LocalizationService {

    // Private constructor to prevent instantiation (SonarQube requirement)
    private LocalizationService() {
        throw new IllegalStateException("Utility class");
    }

    private static final String QUERY =
            "SELECT `key`, value FROM localization_strings WHERE language = ?";

    public static Map<String, String> getLocalizedStrings(Locale locale) {
        Map<String, String> map = new HashMap<>();

        // Use modern API instead of deprecated Locale constructor
        String lang = locale.getLanguage(); // "en", "ar", "fi", etc.

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {

            stmt.setString(1, lang);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String key = rs.getString("key");
                    String value = rs.getString("value");
                    map.put(key, value);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map;
    }
}
