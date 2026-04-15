package org.example.shoppingcart;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CartService {

    private CartService() {
        throw new IllegalStateException("Utility class");
    }

    private static final Logger LOGGER = Logger.getLogger(CartService.class.getName());

    private static final String INSERT_CART =
            "INSERT INTO cart_records (total_items, total_cost, language) VALUES (?, ?, ?)";

    private static final String INSERT_ITEM =
            "INSERT INTO cart_items (cart_record_id, item_number, price, quantity, subtotal) " +
                    "VALUES (?, ?, ?, ?, ?)";

    public static long saveCart(int totalItems, double totalCost, String language, List<CartItem> items) {

        long cartRecordId = -1;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement cartStmt = conn.prepareStatement(INSERT_CART, Statement.RETURN_GENERATED_KEYS)) {

            cartStmt.setInt(1, totalItems);
            cartStmt.setDouble(2, totalCost);
            cartStmt.setString(3, language);
            cartStmt.executeUpdate();

            try (ResultSet rs = cartStmt.getGeneratedKeys()) {
                if (rs.next()) {
                    cartRecordId = rs.getLong(1);
                }
            }

            try (PreparedStatement itemStmt = conn.prepareStatement(INSERT_ITEM)) {

                itemStmt.setLong(1, cartRecordId);

                for (CartItem item : items) {
                    itemStmt.setInt(2, item.getItemNumber());
                    itemStmt.setDouble(3, item.getPrice());
                    itemStmt.setInt(4, item.getQuantity());
                    itemStmt.setDouble(5, item.getSubtotal());
                    itemStmt.addBatch();
                }

                itemStmt.executeBatch();
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error while saving cart", e);
        }

        return cartRecordId;
    }
}
