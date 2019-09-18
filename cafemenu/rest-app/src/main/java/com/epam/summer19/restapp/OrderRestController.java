package com.epam.summer19.restapp;

import com.epam.summer19.dto.OrderDTO;
import com.epam.summer19.model.Order;
import com.epam.summer19.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;


@RestController
public class OrderRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderRestController.class);

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/orders")              /** value = "/order" **/
    public ResponseEntity<Order> add(@RequestBody Order order) {
        LOGGER.debug("REST Add order({})", order);
        return new ResponseEntity<>(orderService.add(order), HttpStatus.CREATED);
    }


    @PutMapping(value = "/orders")               /** value = "/order" **/
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void update(@RequestBody Order order) {
        LOGGER.debug("REST Update order({})", order);
        orderService.update(order);
    }

    @DeleteMapping(value = "/orders/{id}")
    public void delete(@PathVariable("id") int id) {
        LOGGER.debug("REST Delete order ({})", id);
        orderService.delete(id);
    }

    @GetMapping(value = "/orders")
    public Collection<Order> findAll() {
        LOGGER.debug("REST List all orders");
        return orderService.findAll();
    }

    @GetMapping(value = "/ordersdto")
    public Collection<OrderDTO> findAllDTO() {
        LOGGER.debug("REST List all orders DTO");
        return orderService.findAllDTO();
    }

    @GetMapping(value = "/orders/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Order findOrderById(@PathVariable("id") Integer id) {
        LOGGER.debug("REST Find order by orderId({})", id);
        return orderService.findOrderById(id);
    }

    @GetMapping(value = "/ordersdto/{startDateTime}/{endDateTime}")
    @ResponseStatus(value = HttpStatus.OK)
    public Collection<OrderDTO> findOrdersDTOByDateTime(
            @PathVariable("startDateTime") String startDateTime,
            @PathVariable("endDateTime") String endDateTime) {
        LOGGER.debug("REST Find orders between {} and {}", startDateTime, endDateTime);
        return orderService.findOrdersDTOByDateTime(
                LocalDateTime.parse(startDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                LocalDateTime.parse(endDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}