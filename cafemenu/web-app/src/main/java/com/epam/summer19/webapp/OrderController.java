package com.epam.summer19.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Order controller
 */
@Controller
public class OrderController {
    /**
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/order")
    public final String order(Model model) {
        return "order";
    }

    /**
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/order")
    public final String gotoAddOrderPage(Model model) {
        return "order";
    }
}
