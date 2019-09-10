package com.epam.summer19.web_app;

import com.epam.summer19.model.Item;
import com.epam.summer19.service.ItemService;
import com.epam.summer19.web_app.validators.ItemValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;


/**
 * Item controller
 */
@Controller
public class ItemController {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    @Autowired
    ItemValidator itemValidator;

    /**
     * List all items page
     * @param model
     * @return
     */
    @GetMapping(value = "/items")
    public final String listAllItems(Model model) {
        LOGGER.debug("ItemController: ListAllItems findAll({})", model);
        model.addAttribute("items", itemService.findAll());
        return "items";
    }

    /**
     * GOTO Item add page
     * @param model
     * @return
     */
    @GetMapping(value = "/item")
    public final String gotoAddItemPage(Model model) {
        LOGGER.debug("ItemController: gotoAddItemPage({})", model);
        Item item = new Item();
        model.addAttribute("isNew", true);
        model.addAttribute("item", item);
        return "item";
    }

    /**
     * Add item
     * @param item
     * @param result
     * @return
     */
    @PostMapping(value = "/item")
    public String addItem(@Valid Item item, BindingResult result) {
        LOGGER.debug("ItemController: addItem({}, {})", item, result);
        itemValidator.validate(item, result);
        if (result.hasErrors()) {
            return "item";
        } else {
            this.itemService.add(item);
            return "redirect:/items";
        }
    }

    /**
     * GOTO Edit item page
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/item/{id}")
    public final String gotoEditItemPage(@PathVariable Integer id, Model model) {
        LOGGER.debug("ItemController: gotoEditItemPage({},{})", id, model);
        Item item = itemService.findItemById(id);
        model.addAttribute("isNew", false);
        model.addAttribute("item", item);
        return "item";
    }

    /**
     * Update edited item
     * @param item
     * @param result
     * @return
     */
    @PostMapping(value = "/item/{id}")
    public String updateItem(@Valid Item item, BindingResult result) {
        LOGGER.debug("ItemController: updateItem({})", item);
        itemValidator.validate(item, result);
        if (result.hasErrors()) {
            return "item";
        } else {
            this.itemService.update(item);
            return "redirect:/items";
        }

    }

    /**
     * Delete item
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/items/{id}/delete")
    public final String deleteItem(@PathVariable Integer id, Model model) {
        LOGGER.debug("ItemController: delete({},{})", id, model);
        itemService.delete(id);
        return "redirect:/items";
    }
}
