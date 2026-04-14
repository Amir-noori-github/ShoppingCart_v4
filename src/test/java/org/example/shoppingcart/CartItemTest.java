package org.example.shoppingcart;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CartItemTest {

    @Test
    void testConstructorAndGetters() {
        CartItem item = new CartItem(1, 9.99, 3, 29.97);

        assertEquals(1, item.getItemNumber());
        assertEquals(9.99, item.getPrice());
        assertEquals(3, item.getQuantity());
        assertEquals(29.97, item.getSubtotal());
    }

    @Test
    void testZeroValues() {
        CartItem item = new CartItem(0, 0.0, 0, 0.0);

        assertEquals(0, item.getItemNumber());
        assertEquals(0.0, item.getPrice());
        assertEquals(0, item.getQuantity());
        assertEquals(0.0, item.getSubtotal());
    }

    @Test
    void testNegativeValues() {
        CartItem item = new CartItem(-1, -5.0, -2, -10.0);

        assertEquals(-1, item.getItemNumber());
        assertEquals(-5.0, item.getPrice());
        assertEquals(-2, item.getQuantity());
        assertEquals(-10.0, item.getSubtotal());
    }
}
