package com.epam.summer19.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Cafemenu root controller
 */
@Controller
public class HomeController {

    /**
     * Redirect to default page -> main.html
     *
     * @return redirect path
     */
    @GetMapping(value = "/")
    public String defaultPageRedirect() {
        return "redirect:main";
    }

