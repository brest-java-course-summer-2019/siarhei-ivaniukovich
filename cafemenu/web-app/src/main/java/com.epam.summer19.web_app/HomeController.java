package com.epam.summer19.web_app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Cafemenu root controller
 */
@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @GetMapping(value = "/")
    public String defaultPageRedirect() {
        LOGGER.debug("defaultPageRedirect()");
        return "redirect:mainpage";
    }

    @GetMapping(value = "/mainpage")
    public String mainPage() {
        LOGGER.debug("defaultPageRedirect()");
        return "mainpage";
    }

}

