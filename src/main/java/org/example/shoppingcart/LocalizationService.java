package org.example.shoppingcart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LocalizationService {

    private static final Logger LOGGER = Logger.getLogger(LocalizationService.class.getName());

    // Private constructor to prevent instantiation (SonarQube requirement)
    private LocalizationService() {
        throw new IllegalStateException("Utility class");
    }

    private static final String QUERY =
            "SELECT `key`, value FROM localization_strings WHERE language = ?";

    public static Map<String, String> getLocalizedStrings(Locale locale) {
        Map<String, String> map = new HashMap<>();

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
            String msg = "Failed to load localized strings for language: " + lang;
            LOGGER.log(Level.SEVERE, msg, e);
        }
        return map;
    }
}
