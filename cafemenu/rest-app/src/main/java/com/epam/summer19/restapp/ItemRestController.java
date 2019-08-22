package com.epam.summer19.restapp;

import com.epam.summer19.model.Item;
import com.epam.summer19.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class ItemRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemRestController.class);

    @Autowired
    private ItemService service;

    @GetMapping(value = "/items")
    public Collection<Item> findAll() {
        LOGGER.debug("List all items");
        return service.findAll();
    }

    @GetMapping(value = "/items/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Item findItemById(@PathVariable Integer id) {
        LOGGER.debug("find item by itemId({})", id);
        return service.findItemById(id);
    }

    @PutMapping()
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void update(@RequestBody Item item) {
        LOGGER.debug("update item ({})", item);
        service.update(item);
    }

    @DeleteMapping(value = "/items/{id}")
    public void delete(@PathVariable("id") int id) {
        LOGGER.debug("delete item ({})", id);
        service.delete(id);
    }

    @PostMapping()
    public ResponseEntity<Item> add(@RequestBody Item item) {

        LOGGER.debug("add item({})", item);
        Item result = service.add(item);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}