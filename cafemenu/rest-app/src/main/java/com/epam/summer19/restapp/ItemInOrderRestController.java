package com.epam.summer19.restapp;

import com.epam.summer19.model.ItemInOrder;
import com.epam.summer19.service.ItemInOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class ItemInOrderRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemInOrderRestController.class);

    @Autowired
    private ItemInOrderService itemInOrderService;

    @PostMapping(value = "/iio")
    public void add(@RequestBody ItemInOrder itemInOrder) {
        LOGGER.debug("REST Add iio({})", itemInOrder);
        itemInOrderService.add(itemInOrder);
    }

    @PutMapping(value = "/iios/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void update(@PathVariable("id") int id, @RequestBody ItemInOrder itemInOrder) {
        LOGGER.debug("REST Update iio({})", itemInOrder);
        itemInOrderService.update(itemInOrder);
    }

    @DeleteMapping(value = "/iios/{orderId}/{itemId}")
    public void delete(
            @PathVariable("orderId") Integer orderId,
            @PathVariable("itemId") Integer itemId) {
        LOGGER.debug("REST Delete iio({},{})", orderId, itemId);
        itemInOrderService.delete(orderId,itemId);
    }

    @GetMapping(value = "/iios")
    public Collection<ItemInOrder> findAll() {
        LOGGER.debug("REST List all iios");
        return itemInOrderService.findAll();
    }

    @GetMapping(value = "/iios/{orderId}")
    @ResponseStatus(value = HttpStatus.OK)
    public Collection<ItemInOrder> findOrderById(@PathVariable("orderId") Integer orderId) {
        LOGGER.debug("REST Find iio by orderId({})", orderId);
        Collection<ItemInOrder> result = itemInOrderService.findIioByOrderId(orderId);
        return result;
    }

    @GetMapping(value = "/iios/{orderId}/{itemId}")
    @ResponseStatus(value = HttpStatus.OK)
    public ItemInOrder findItemInOrderByOrderItemId(
            @PathVariable("orderId") Integer orderId,
            @PathVariable("itemId") Integer itemId) {
        LOGGER.debug("REST Find iio by orderId({}) & itemId({})", orderId, itemId);
        return itemInOrderService.findIioByOrderItemId(orderId, itemId);
    }
}