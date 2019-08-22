package com.epam.summer19.webapp;

import com.epam.summer19.model.Item;
import com.epam.summer19.service.ItemService;
import com.epam.summer19.validators.ItemValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Item controller
 */
@Controller
public class ItemController {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ItemService itemService;

    @Autowired
    ItemValidator itemValidator;

    /**
     * Items list page
     * @param model
     * @return
     */
    @GetMapping(value = "/items")
    public final String Item(Model model) {
        LOGGER.debug("findAll({})", model);
        model.addAttribute("items", itemService.findAll());
        return "items";
    }

    /**
     * Edit item page
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/item/{id}")
    public final String gotoEditItemPage(@PathVariable Integer id, Model model) {

        LOGGER.debug("gotoEditItemPage({},{})", id, model);
        Department department = departmentService.findById(id);
        model.addAttribute("department", department);
        return "department";
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
