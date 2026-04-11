package org.example.shoppingcart;

import org.junit.jupiter.api.Test;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceTest {

    @Test
    void testLocalizationReturnsMap() {
        Locale locale = Locale.forLanguageTag("en-US");

        Map<String, String> result = LocalizationService.getLocalizedStrings(locale);

        assertNotNull(result);
        assertTrue(result.size() >= 0);
    }
}
