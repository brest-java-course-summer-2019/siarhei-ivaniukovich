package com.epam.summer19.webapp.consumers;

import com.epam.summer19.dto.OrderDTO;
import com.epam.summer19.model.Order;
import com.epam.summer19.service.OrderService;
import com.epam.summer19.webapp.OrderController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Order Consumer (for REST)
 */
public class OrderRestConsumer implements OrderService {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private String url;

    private RestTemplate restTemplate;

    /**
     * OrderRestConsumer constructor.
     * @param url
     * @param restTemplate
     */
    public OrderRestConsumer(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    /**
     * findAll() - get all orders
     * @return
     */
    @Override
    public List<Order> findAll() {
        LOGGER.debug("findAll()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/all_", List.class);
        return (List<Order>) responseEntity.getBody();
    }

    @Override
    public List<OrderDTO> findAllDTO() {
        LOGGER.debug("findAllDTO()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/all", List.class);
        return (List<OrderDTO>) responseEntity.getBody();
    }

    /**
     * add() new multiple orders.
     * @param
     */
    @Override
    public void add(Order... orders) {
        LOGGER.debug("Multiple orders add({})");
        for(Order order : orders) {
            restTemplate.postForEntity(url, order, Order.class);
        }
    }

    /**
     * add() new order.
     * @param
     */
    @Override
    public void add(Order order) {
        LOGGER.debug("add({})", order);
        restTemplate.postForEntity(url, order, Order.class);
    }

    /**
     * update() order
     * @param
     */
    @Override
    public void update(Order order) {
        LOGGER.debug("update({})", order);
        restTemplate.put(url, order);

    }

    /**
     * delete() order
     * @param
     */
    @Override
    public void delete(Integer orderId) {
        LOGGER.debug("delete({})", orderId);
        restTemplate.delete(url + "/order/" + orderId);
    }

    /**
     * findOrderById() order
     * @param orderId
     * @return
     */
    @Override
    public Order findOrderById(Integer orderId) {
        LOGGER.debug("findOrderById({})", orderId);
        ResponseEntity<Order> responseEntity = restTemplate.getForEntity(url + "/" + orderId, Order.class);
        return responseEntity.getBody();
    }

    /**
     * findOrderByDateTime()
     * @param startDateTime
     * @param endDateTime
     * @return
     */
    @Override
    public List<Order> findOrdersByDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        LOGGER.debug("findOrderById({})", startDateTime+"<->"+endDateTime);
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/" + startDateTime + "/" + endDateTime, List.class);
        return (List<Order>) responseEntity.getBody();
    }

}