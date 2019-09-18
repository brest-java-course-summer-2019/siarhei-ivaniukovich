package com.epam.summer19.web_app.validators;
/**
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemInOrderValidatorTest {

    private ItemInOrderValidator itemInOrderValidatorUnderTest;

    @BeforeEach
    public void setUp() {
        itemInOrderValidatorUnderTest = new ItemInOrderValidator();
    }

    @Test
    public void testSupports() {
        // Setup
        final Class<?> clazz = null;

        // Run the test
        final boolean result = itemInOrderValidatorUnderTest.supports(clazz);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testValidate() {
        // Setup
        final Object target = null;
        final Errors errors = null;

        // Run the test
        itemInOrderValidatorUnderTest.validate(target, errors);

        // Verify the results
    }
}
**/