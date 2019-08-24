package com.epam.summer19.restapp;

import com.epam.summer19.model.Order;
import com.epam.summer19.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class OrderRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderRestController.class);

    @Autowired
    private OrderService service;

    @GetMapping(value = "/orders")
    public Collection<Order> findAll() {
        LOGGER.debug("List all orders");
        return service.findAll();
    }

    @GetMapping(value = "/orders/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Order findOrderById(@PathVariable Integer id) {
        LOGGER.debug("find order by orderId({})", id);
        return service.findOrderById(id);
    }

    @PutMapping()
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void update(@RequestBody Order order) {
        LOGGER.debug("update order ({})", order);
        service.update(order);
    }

    @DeleteMapping(value = "/orders/{id}")
    public void delete(@PathVariable("id") int id) {
        LOGGER.debug("delete order ({})", id);
        service.delete(id);
    }

    @PostMapping()
    public void add(@RequestBody Order order) {

        LOGGER.debug("add order({})", order);
        service.add(order);
    }

}