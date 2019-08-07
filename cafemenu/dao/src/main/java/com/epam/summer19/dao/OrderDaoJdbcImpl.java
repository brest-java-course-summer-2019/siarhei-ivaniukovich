package com.epam.summer19.dao;

import com.epam.summer19.model.Item;
import com.epam.summer19.model.Order;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class OrderDaoJdbcImpl implements OrderDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final static String SELECT_ALL =
            "select order_id, order_item_id, order_item_count from order order by 1";
    private final static String ADD_ORDER =
            "insert into order (order_id, order_item_id, order_item_count) values (:orderId, :itemId, :itemCount)";
    private final static String DELETE_ORDER =
            "delete from order where order_id = :orderId";
    private final static String UPDATE_ORDER =
            "update order set order_id = :orderId, order_item_id = :itemId,"
          + " order_item_count = :itemCount where order_id = :orderId";
    private static final String ORDER_ID = "orderId";
    //private static final String ORDER_ITEM_ID = "orderItems";
    //private static final String ORDER_ITEM_COUNT = "orderItems";


    public OrderDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Order add(Order order) {

        // ADD THERE MAP INTEGRATION !!!!
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(ORDER_ID, order.getOrderId());

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

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
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
    public List<Order> findAll() {
        List<Order> order =
                namedParameterJdbcTemplate.query(SELECT_ALL, new OrderDaoJdbcImpl.OrderRowMapper());
        return order;
    }

    private class OrderRowMapper implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet resultSet, int i) throws SQLException {
            Order order = new Order();
            order.setOrderId(resultSet.getInt("order_id"));
            //order.setOrderItems(resultSet.getInt("order_items"));
            return order;
        }
    }
}