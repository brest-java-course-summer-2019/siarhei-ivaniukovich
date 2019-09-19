package com.epam.summer19.restapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Home MVC + cmd readme + index
 */
@RestController
public class VersionController {

    @Value("${restReadme}")
    private String restReadmeStr;

    @Value("${version}")
    private String versionStr;

    @Value("${index}")
    private String indexStr;


    @GetMapping(value = "/version")
    public String version() {
        return versionStr;
    }

    @GetMapping(value = "/cafemenurest")
    public String restReadme() {
        return restReadmeStr;
    }

    @GetMapping(value = "/")
    public String index() {
        return indexStr;
    }
}