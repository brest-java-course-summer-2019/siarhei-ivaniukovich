package com.epam.summer19.web_app.validators;
/**
import com.epam.summer19.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemValidatorTest {

    private ItemValidator itemValidatorUnderTest;

    @BeforeEach
    public void setUp() {
        itemValidatorUnderTest = new ItemValidator();
        itemValidatorUnderTest.itemService = mock(ItemService.class);
    }

    @Test
    public void testSupports() {
        // Setup
        final Class<?> clazz = null;

        // Run the test
        final boolean result = itemValidatorUnderTest.supports(clazz);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testValidate() {
        // Setup
        final Object target = null;
        final Errors errors = null;
        when(itemValidatorUnderTest.itemService.findItemByName("itemName")).thenReturn(null);

        // Run the test
        itemValidatorUnderTest.validate(target, errors);

        // Verify the results
    }
}
**/