package com.epam.summer19.dao;

import com.epam.summer19.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.support.DataAccessUtils;
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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class OrderDaoJdbcImpl implements OrderDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${order.findAll}")
    private String findAllSql;

    @Value("${order.add}")
    private String addSql;

    @Value("${order.delete}")
    private String deleteSql;

    @Value("${order.update}")
    private String updateSql;

    @Value("${order.findById}")
    private String findByIdSql;

    @Value("${order.findOrdersByDateTime}")
    private String findOrdersByDateTimeSql;

    @Value("${order.summaryPriceUpdate}")
    private String summaryPriceUpdateSql;

    private static final String ORDER_ID = "orderId";
    private static final String ORDER_EMPLOYEE_ID = "orderEmployeeId";
    private static final String ORDER_STATUS = "orderStatus";
    private static final String ORDER_DATETIME_START = "orderDateTimeStart";
    private static final String ORDER_DATETIME_END = "orderDateTimeEnd";

    private static final String DB_ORDER_ID = "order_id";
    private static final String DB_ORDER_EMPLOYEE_ID = "order_employee_id";
    private static final String DB_ORDER_TIME = "order_date_time";
    private static final String DB_ORDER_STATUS = "order_status";


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
        namedParameterJdbcTemplate.update(addSql, parameters, generatedKeyHolder);
        order.setOrderId((Integer)generatedKeyHolder.getKeys().get(DB_ORDER_ID));
        order.setOrderDateTime(((Timestamp)generatedKeyHolder.getKeys().get(DB_ORDER_TIME)).toLocalDateTime());

        return order;
    }

    @Override
    public void update(Order order) {
        Optional.of(namedParameterJdbcTemplate.update(updateSql, new BeanPropertySqlParameterSource(order)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update order in DB"));
    }

    @Override
    public void delete(Integer orderId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(ORDER_ID, orderId);
        Optional.of(namedParameterJdbcTemplate.update(deleteSql, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete order from DB"));
    }

    @Override
    public List<Order> findAll() {
        List<Order> order =
                namedParameterJdbcTemplate.query(findAllSql, new OrderDaoJdbcImpl.OrderRowMapper());
        return order;
    }

    @Override
    public Optional<Order> findOrderById(Integer orderId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(ORDER_ID, orderId);
        List<Order> results = namedParameterJdbcTemplate.query(findByIdSql, namedParameters,
                new OrderDaoJdbcImpl.OrderRowMapper());
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public List<Order> findOrdersByDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(ORDER_DATETIME_START, startDateTime);
        mapSqlParameterSource.addValue(ORDER_DATETIME_END, endDateTime);
        List<Order> results = namedParameterJdbcTemplate.query(findOrdersByDateTimeSql, mapSqlParameterSource,
                new OrderDaoJdbcImpl.OrderRowMapper());
        return results;
    }

    @Override
    public void calcSummaryOrderPrice(Integer orderId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(ORDER_ID, orderId);
        Optional.of(namedParameterJdbcTemplate.update(summaryPriceUpdateSql, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to calc summaryOrderPrice in DB"));
     }

    /** BeanPropertyRowMapper.newInstance(Order.class) **/

    private class OrderRowMapper implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet resultSet, int i) throws SQLException {
            Order order = new Order();
            order.setOrderId(resultSet.getInt(DB_ORDER_ID));
            order.setOrderEmployeeId(resultSet.getInt(DB_ORDER_EMPLOYEE_ID));
            order.setOrderDateTime(resultSet.getTimestamp(DB_ORDER_TIME).toLocalDateTime());
            order.setOrderStatus(resultSet.getInt(DB_ORDER_STATUS));
            return order;
        }
    }
}