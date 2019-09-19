package com.epam.summer19.web_app.handlers;
/**
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.web.client.HttpServerErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerExceptionHandlerTest {

    private ServerExceptionHandler serverExceptionHandlerUnderTest;

    @BeforeEach
    public void setUp() {
        serverExceptionHandlerUnderTest = new ServerExceptionHandler();
    }

    @Test
    public void testHandleServerException() {
        // Setup
        final HttpServerErrorException ex = null;
        final Model model = null;
        final String expectedResult = "result";

        // Run the test
        final String result = serverExceptionHandlerUnderTest.handleServerException(ex, model);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
**/