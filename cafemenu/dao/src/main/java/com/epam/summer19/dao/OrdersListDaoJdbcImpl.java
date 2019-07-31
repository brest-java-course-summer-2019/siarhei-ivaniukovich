package com.epam.summer19.dao;

import com.epam.summer19.model.OrdersList;
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
public class OrdersListDaoJdbcImpl implements OrdersListDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final static String SELECT_ALL =
            "select ol.order_id, ol.employee_id from orderslist ol order by 1";
    private final static String ADD_ORDER =
            "insert into orderslist (order_id) values (:orderId)";
    private final static String DELETE_ORDER =
            "delete from orderslist where order_id = :orderId";
    private final static String UPDATE_ORDER =
            "update orderslist set order_status = :orderStatus where order_id = :orderId";
    private final static String FIND_BY_ORDER_ID =
            "select order_id, employee_id, order_status from orderslist where order_id = :orderId";
    private final static String FIND_BY_EMPLOYEE_ID =
            "select order_id, employee_id, order_status from orderslist where employee_id = :employeeId";
    private static final String ORDER_ID = "orderId";
    private static final String EMPLOYEE_ID = "employeeId";
    private static final String ORDER_STATUS = "orderStatus";

    public OrdersListDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public OrdersList add(OrdersList orderslist) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(ORDER_ID, orderslist.getOrderId());
        parameters.addValue(EMPLOYEE_ID, orderslist.getEmployeeId());
        parameters.addValue(ORDER_STATUS, orderslist.getOrderStatus());


        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_ORDER, parameters, generatedKeyHolder);
        orderslist.setOrderId(generatedKeyHolder.getKey().intValue());
        return orderslist;
    }

    @Override
    public void update(OrdersList orderslist) {
        Optional.of(namedParameterJdbcTemplate.update(UPDATE_ORDER, new BeanPropertySqlParameterSource(orderslist)))
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
    public List<OrdersList> findAll() {
        List<OrdersList> orderslist =
                namedParameterJdbcTemplate.query(SELECT_ALL, new OrdersListRowMapper());
        return orderslist;
    }

    @Override
    public Optional<OrdersList> findByOrderId(Integer orderId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(ORDER_ID, orderId);
        List<OrdersList> results = namedParameterJdbcTemplate.query(FIND_BY_ORDER_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(OrdersList.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public Optional<OrdersList> findByEmployeeId(Integer employeeId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(EMPLOYEE_ID, employeeId);
        List<OrdersList> results = namedParameterJdbcTemplate.query(FIND_BY_EMPLOYEE_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(OrdersList.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    private class OrdersListRowMapper implements RowMapper<OrdersList> {
        @Override
        public OrdersList mapRow(ResultSet resultSet, int i) throws SQLException {
            OrdersList orderslist = new OrdersList();
            orderslist.setOrderId(resultSet.getInt("order_id"));
            orderslist.setEmployeeId(resultSet.getInt("employee_id"));
            orderslist.setOrderStatus(resultSet.getInt("order_status"));
            return orderslist;
        }
    }
}