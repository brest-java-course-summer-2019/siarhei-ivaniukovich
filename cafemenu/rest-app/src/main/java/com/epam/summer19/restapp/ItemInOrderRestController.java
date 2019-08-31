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

    /** ADD
     *  curl -H "Content-Type: application/json" -X POST -d '{"iioOrderId":"4","iioItemId":"1","iioItemName":"Burger","iioItemPrice":"5.0","iioItemCount":"2"}' -v http://localhost:8082/iteminorder
     *  */
    @PostMapping(value = "/iteminorder")
    public void add(@RequestBody ItemInOrder itemInOrder) {
        LOGGER.debug("REST Add iteminorder({})", itemInOrder);
        itemInOrderService.add(itemInOrder);
    }

    /** UPDATE (EDIT)
     *  curl -H "Content-Type: application/json" -X PUT -d '{"iioOrderId":"4","iioItemId":"2","iioItemName":"Nuggets","iioItemPrice":"3.0","iioItemCount":"5"}' -v http://localhost:8082/iteminorders/4
     *  */
    @PutMapping(value = "/iteminorders/{orderId}/{itemId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void update(@RequestBody ItemInOrder itemInOrder) {
        LOGGER.debug("REST Update iteminorder({})", itemInOrder);
        itemInOrderService.update(itemInOrder);
    }

    /** DELETE
     *  curl -X DELETE -v http://localhost:8082/iteminorders/1/4
     *  */
    @DeleteMapping(value = "/iteminorders/{orderId}/{itemId}")
    public void delete(
            @PathVariable("orderId") Integer orderId,
            @PathVariable("itemId") Integer itemId) {
        LOGGER.debug("REST Delete iteminorder({},{})", orderId, itemId);
        itemInOrderService.delete(orderId, itemId);
    }

    /** LIST ALL
     *  curl -X GET -v http://localhost:8082/iteminorders
     *  */
    @GetMapping(value = "/iteminorders")
    public Collection<ItemInOrder> findAll() {
        LOGGER.debug("REST List all iteminorders");
        return itemInOrderService.findAll();
    }

    @GetMapping(value = "/iteminorders/{orderId}")
    @ResponseStatus(value = HttpStatus.OK)
    public Collection<ItemInOrder> findOrderById(@PathVariable("orderId") Integer orderId) {
        LOGGER.debug("REST Find iteminorder by orderId({})", orderId);
        Collection<ItemInOrder> result = itemInOrderService.findIioByOrderId(orderId);
        return result;
    }

    @GetMapping(value = "/iteminorders/{orderId}/{itemId}")
    @ResponseStatus(value = HttpStatus.OK)
    public ItemInOrder findItemInOrderByOrderItemId(
            @PathVariable("orderId") Integer orderId,
            @PathVariable("itemId") Integer itemId) {
        LOGGER.debug("REST Find iteminorder by orderId({}) & itemId({})", orderId, itemId);
        return itemInOrderService.findIioByOrderItemId(orderId, itemId);
    }
}