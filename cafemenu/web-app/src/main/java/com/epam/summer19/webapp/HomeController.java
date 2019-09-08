package com.epam.summer19.webapp;

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
        return "redirect:main";
    }

}

