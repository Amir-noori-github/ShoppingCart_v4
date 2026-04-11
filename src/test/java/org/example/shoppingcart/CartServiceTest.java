package org.example.shoppingcart;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CartServiceTest {

    @Test
    void testSaveCartDoesNotThrow() {
        List<CartItem> items = List.of(
                new CartItem(1, 10.0, 2, 20.0),
                new CartItem(2, 5.0, 1, 5.0)
        );

        assertDoesNotThrow(() ->
                CartService.saveCart(3, 25.0, "en", items)
        );
    }
}
