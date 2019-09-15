package com.epam.summer19.web_app.handlers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class ServerExceptionHandler {

    @ExceptionHandler(HttpServerErrorException.class)
    public final String handleServerException(HttpServerErrorException ex, Model model) {
        model.addAttribute("exception", ex.getMessage());
        return "exception";
    }

}