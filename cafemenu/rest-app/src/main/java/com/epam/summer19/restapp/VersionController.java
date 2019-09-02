package com.epam.summer19.restapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Home MVC + cmd readme
 */
@RestController
public class VersionController {

    private final static String CMVERSION = "1.0.0";

    @Value("${restReadme}")
    private String restReadme;

    /**
     * Version.
     *
     * @return app version
     */
    @GetMapping(value = "/version")
    public String version() {
        return CMVERSION;
    }

    @GetMapping(value = "/cafemenurest")
    public String restReadme() {
        return restReadme;
    }
}