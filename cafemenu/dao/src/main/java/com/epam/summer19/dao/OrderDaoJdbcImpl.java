package com.epam.summer19.dao;

import com.epam.summer19.model.Order;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderDaoJdbcImpl implements OrderDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final static String SELECT_ALL =
            "select o.order_id, o.order_name from order o order by 2";
    private final static String ADD_ORDER =
            "insert into order (order_id) values (:orderId)";
    private final static String DELETE_ORDER =
            "delete from order where order_id = :orderId";
    private final static String UPDATE_ORDER =
            "update order set order_id = :orderId where order_id = :orderId";
    private static final String ORDER_ID = "orderId";

    public OrderDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Order add(Order order) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("orderId", order.getOrderId());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_ORDER, parameters, generatedKeyHolder);
        order.setOrderId(generatedKeyHolder.getKey().intValue());
        return order;
    }

    @Override
    public void update(Order order) {
        Optional.of(namedParameterJdbcTemplate.update(UPDATE_ORDER, new BeanPropertySqlParameterSource(order)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update order in DB"));
    }

    @Override
    public void delete(Integer orderId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(ORDER_ID, orderId);
        Optional.of(namedParameterJdbcTemplate.update(DELETE_ORDER, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete order from DB"));
    }

    @Override
    public List<Order findAll() {
        List<Order> order =
                namedParameterJdbcTemplate.query(SELECT_ALL, new OrdersListDaoJdbcImpl.OrderRowMapper());
        return order;
    }

    private class OrderRowMapper implements RowMapper<Order> {
    @Override
    public List<Order> findAll() {
        List<Order> orders =
                namedParameterJdbcTemplate.query(SELECT_ALL, new OrderRowMapper());
        return orders;
    }

}