package com.epam.summer19.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Item controller
 */
@Controller
public class ItemController {
    /**
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/Item")
    public final String Item(Model model) {
        return "order";
    }

    /**
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/Item")
    public final String gotoAddItemPage(Model model) {
        return "Item";
    }
}
