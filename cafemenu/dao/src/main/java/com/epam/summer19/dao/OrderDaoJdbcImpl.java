package com.epam.summer19.dao;

import com.epam.summer19.model.Order;
import com.epam.summer19.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${order.findOrdersDTOByDateTime}")
    private String findOrdersDTOByDateTimeSql;

    @Value("${orderDTO.findAllWithSum}")
    private String findAllDTOSql;

    private static final String ORDER_ID = "orderId";
    private static final String ORDER_EMPLOYEE_ID = "orderEmployeeId";
    private static final String ORDER_DATETIME_START = "orderDateTimeStart";
    private static final String ORDER_DATETIME_END = "orderDateTimeEnd";

    private static final String DB_ORDER_ID = "order_id";
    private static final String DB_ORDER_EMPLOYEE_ID = "employee_id";
    private static final String DB_ORDER_TIME = "order_date_time";


    public OrderDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Order add(Order order) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(ORDER_EMPLOYEE_ID, order.getOrderEmployeeId());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(addSql, parameters, generatedKeyHolder);
        order.setOrderId((Integer)generatedKeyHolder.getKeys().get(DB_ORDER_ID));
        order.setOrderDateTime(((Timestamp)generatedKeyHolder.getKeys().get(DB_ORDER_TIME)).toLocalDateTime());

        return order;
    }

    @Override
    public void update(Order order) {
        if (namedParameterJdbcTemplate.update(updateSql, new BeanPropertySqlParameterSource(order)) < 1) {
            throw new RuntimeException("ItemInOrder DAO: Failed to delete iteminorder from DB");
        }
    }

    @Override
    public void delete(Integer orderId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(ORDER_ID, orderId);
        if(namedParameterJdbcTemplate.update(deleteSql, mapSqlParameterSource) < 1) {
            throw new RuntimeException("ItemInOrder DAO: Failed to delete iteminorder from DB");
        }
    }

    @Override
    public List<Order> findAll() {
        List<Order> order =
                namedParameterJdbcTemplate.query(findAllSql, new OrderDaoJdbcImpl.OrderRowMapper());
        return order;
    }

    @Override
    public List<OrderDTO> findAllDTO() {
        List<OrderDTO> orderDTO =
                namedParameterJdbcTemplate.query(findAllDTOSql, BeanPropertyRowMapper.newInstance(OrderDTO.class));
        return orderDTO;
    }

    @Override
    public Optional<Order> findOrderById(Integer orderId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(ORDER_ID, orderId);
        List<Order> results = namedParameterJdbcTemplate.query(findByIdSql, namedParameters,
                new OrderDaoJdbcImpl.OrderRowMapper());
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public List<OrderDTO> findOrdersDTOByDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(ORDER_DATETIME_START, startDateTime);
        mapSqlParameterSource.addValue(ORDER_DATETIME_END, endDateTime);
        List<OrderDTO> results = namedParameterJdbcTemplate.query(findOrdersDTOByDateTimeSql, mapSqlParameterSource,
                BeanPropertyRowMapper.newInstance(OrderDTO.class));
        return results;
    }

    private class OrderRowMapper implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet resultSet, int i) throws SQLException {
            Order order = new Order();
            order.setOrderId(resultSet.getInt(DB_ORDER_ID));
            order.setOrderEmployeeId(resultSet.getInt(DB_ORDER_EMPLOYEE_ID));
            order.setOrderDateTime(resultSet.getTimestamp(DB_ORDER_TIME).toLocalDateTime());
            return order;
        }
    }
}