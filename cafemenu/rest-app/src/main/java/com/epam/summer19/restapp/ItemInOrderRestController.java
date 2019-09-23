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
    @PostMapping(value = "/iteminorders")              /** value = "/iteminorder" **/
    public void add(@RequestBody ItemInOrder itemInOrder) {
        LOGGER.debug("ItemInOrderRestController: add({})", itemInOrder);
        itemInOrderService.add(itemInOrder);
    }

    /** UPDATE (EDIT)
     *  curl -H "Content-Type: application/json" -X PUT -d '{"iioOrderId":"4","iioItemId":"2","iioItemName":"Nuggets","iioItemPrice":"3.0","iioItemCount":"5"}' -v http://localhost:8082/iteminorders/4
     *  */
    @PutMapping(value = "/iteminorders")               /** value = "/iteminorder" **/
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void update(@RequestBody ItemInOrder itemInOrder) {
        LOGGER.debug("ItemInOrderRestController: update({})", itemInOrder);
        itemInOrderService.update(itemInOrder);
    }

    /** DELETE
     *  curl -X DELETE -v http://localhost:8082/iteminorders/1/4
     *  */
    @DeleteMapping(value = "/iteminorders/{orderId}/{itemId}")
    public void delete(
            @PathVariable("orderId") Integer orderId,
            @PathVariable("itemId") Integer itemId) {
        LOGGER.debug("ItemInOrderRestController: delete({},{})", orderId, itemId);
        itemInOrderService.delete(orderId, itemId);
    }

    /** LIST ALL
     *  curl -X GET -v http://localhost:8082/iteminorders
     *  */
    @GetMapping(value = "/iteminorders")
    public Collection<ItemInOrder> findAll() {
        LOGGER.debug("ItemInOrderRestController: findAll()");
        return itemInOrderService.findAll();
    }

    /**
     * Find iio by orderId
     * curl -X GET -v http://localhost:8082/iteminorders/1
     * @param orderId
     * @return
     */
    @GetMapping(value = "/iteminorders/{orderId}")
    @ResponseStatus(value = HttpStatus.OK)
    public Collection<ItemInOrder> findItemInOrderByOrderId(@PathVariable("orderId") Integer orderId) {
        LOGGER.debug("ItemInOrderRestController: findItemInOrderByOrderId({})", orderId);
        Collection<ItemInOrder> result = itemInOrderService.findIioByOrderId(orderId);
        return result;
    }

    /**
     * Find iio by orderItemId
     * curl -X GET -v http://localhost:8082/iteminorders/1/2
     * @param orderId
     * @param itemId
     * @return
     */
    @GetMapping(value = "/iteminorders/{orderId}/{itemId}")
    @ResponseStatus(value = HttpStatus.OK)
    public ItemInOrder findItemInOrderByOrderItemId(
            @PathVariable("orderId") Integer orderId,
            @PathVariable("itemId") Integer itemId) {
        LOGGER.debug("ItemInOrderRestController: findItemInOrderByOrderItemId({}, {})", orderId, itemId);
        return itemInOrderService.findIioByOrderItemId(orderId, itemId);
    }
}