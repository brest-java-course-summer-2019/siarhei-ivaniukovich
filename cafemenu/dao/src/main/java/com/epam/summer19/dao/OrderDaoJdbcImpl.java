package com.epam.summer19.dao;

import com.epam.summer19.model.Order;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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
            "select order_id, order_employee_id, order_status from order_table order by 1";
    private final static String ADD_ORDER =
            "insert into order_table (order_employee_id, order_status) values"
          + " (:orderEmployeeId, :orderStatus)";
    private final static String DELETE_ORDER =
            "delete from order_table where order_id = :orderId";
    private final static String UPDATE_ORDER =
            "update order_table set order_employee_id = :orderEmployeeId,"
          + " order_status = :orderStatus where order_id = :orderId";
    private final static String FIND_ORDER_BY_ID =
            "select order_id, order_employee_id, order_status from order_table "
          + "where order_id = :orderId";
    private static final String ORDER_ID = "orderId";
    private static final String ORDER_EMPLOYEE_ID = "orderEmployeeId";
    private static final String ORDER_STATUS = "orderStatus";


    public OrderDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    @Override
    public Order add(Order order) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(ORDER_EMPLOYEE_ID, order.getOrderEmployeeId());
        parameters.addValue(ORDER_STATUS, order.getOrderStatus());

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
    public List<Order> findAll() {
        List<Order> order =
                namedParameterJdbcTemplate.query(SELECT_ALL, new OrderDaoJdbcImpl.OrderRowMapper());
        return order;
    }

    @Override
    public Optional<Order> findOrderById(Integer orderId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(ORDER_ID, orderId);
        List<Order> results = namedParameterJdbcTemplate.query(FIND_ORDER_BY_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(Order.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    private class OrderRowMapper implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet resultSet, int i) throws SQLException {
            Order order = new Order();
            order.setOrderId(resultSet.getInt("order_id"));
            order.setOrderId(resultSet.getInt("order_employee_id"));
            order.setOrderId(resultSet.getInt("order_status"));
            return order;
        }
    }
}