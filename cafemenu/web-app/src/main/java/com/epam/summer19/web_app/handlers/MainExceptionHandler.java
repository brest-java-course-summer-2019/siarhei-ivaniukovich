package com.epam.summer19.web_app.handlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class MainExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final String handleAnyException(Exception ex, RedirectAttributes redirAttrs) {
        redirAttrs.addFlashAttribute("exception", ex);
        return "exception";
    }

}