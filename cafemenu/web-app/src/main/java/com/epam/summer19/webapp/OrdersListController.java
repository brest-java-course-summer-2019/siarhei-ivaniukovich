package com.epam.summer19.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * OrdersList controller
 */
@Controller
public class OrdersListController {
    /**
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/orderslist")
    public final String orderslist(Model model) {
        return "order";
    }

    /**
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/orderslist")
    public final String gotoAddOrdersListPage(Model model) {
        return "orderslist";
    }
}
