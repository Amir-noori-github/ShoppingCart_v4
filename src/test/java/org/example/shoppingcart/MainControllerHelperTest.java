package org.example.shoppingcart;

import org.junit.jupiter.api.Test;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class MainControllerHelperTest {

    private final MainController controller = new MainController();

    @Test
    void testNormalizeDigits() {
        String input = "١٢٣";
        String expected = "123";

        String result = controller.normalizeDigits(input);

        assertEquals(expected, result);
    }

    @Test
    void testFormatNumber() {
        String result = controller.formatNumber(123.456, Locale.US);

        assertEquals("123.46", result);
    }
}
