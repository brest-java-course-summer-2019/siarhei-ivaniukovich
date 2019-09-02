package com.epam.summer19.restapp;

import com.epam.summer19.model.Item;
import com.epam.summer19.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class ItemRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemRestController.class);

    @Autowired
    private ItemService itemService;

    /**
     * curl -H "Content-Type: application/json" -X POST -d '{"itemName":"Something","itemPrice":"8.8"}' -v http://localhost:8082/item
     */
    @PostMapping(value = "/item")
    public void add(@RequestBody Item item) {
        LOGGER.debug("REST Add item({})", item);
        itemService.add(item);
    }

    @PutMapping(value = "/item")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void update(@RequestBody Item item) {
        LOGGER.debug("REST Update item({})", item);
        itemService.update(item);
    }

    @DeleteMapping(value = "/items/{id}")
    public void delete(@PathVariable("id") int id) {
        LOGGER.debug("REST Delete item ({})", id);
        itemService.delete(id);
    }

    @GetMapping(value = "/items")
    public Collection<Item> findAll() {
        LOGGER.debug("REST List all items");
        return itemService.findAll();
    }

    @GetMapping(value = "/items/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Item findItemById(@PathVariable("id") Integer id) {
        LOGGER.debug("REST Find item by itemId({})", id);
        return itemService.findItemById(id);
    }
}