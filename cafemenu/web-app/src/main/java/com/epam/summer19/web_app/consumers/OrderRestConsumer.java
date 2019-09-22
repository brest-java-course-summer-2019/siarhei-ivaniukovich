package com.epam.summer19.web_app.consumers;

import com.epam.summer19.dto.OrderDTO;
import com.epam.summer19.model.Order;
import com.epam.summer19.service.OrderService;
import com.epam.summer19.web_app.OrderController;
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
        return restTemplate.getForEntity(url, List.class).getBody();
    }

    @Override
    public List<OrderDTO> findAllDTO() {
        LOGGER.debug("findAllDTO()");
        return restTemplate.getForEntity(url+"dto", List.class).getBody();
    }

    /**
     * add() new order.
     * @param
     */
    @Override
    public Order add(Order order) {
        LOGGER.debug("add({})", order);
        return restTemplate.postForEntity(url, order, Order.class).getBody();
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
        restTemplate.delete(url + "/" + orderId);
    }

    /**
     * findOrderById() order
     * @param orderId
     * @return
     */
    @Override
    public Order findOrderById(Integer orderId) {
        LOGGER.debug("findOrderById({})", orderId);
        return restTemplate.getForEntity(url + "/" + orderId, Order.class).getBody();
    }

    /**
     * findOrderByDateTime()
     * @param startDateTime
     * @param endDateTime
     * @return
     */
    @Override
    public List<OrderDTO> findOrdersDTOByDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        LOGGER.debug("findOrdersDTOByDateTime({},{})", startDateTime, endDateTime);
        return restTemplate.getForEntity(
                url + "dto/" + startDateTime + ":00/" + endDateTime + ":00", List.class).getBody();
    }

}