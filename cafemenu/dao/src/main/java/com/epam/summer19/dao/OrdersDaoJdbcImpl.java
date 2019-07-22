package com.epam.summer19;

import com.epam.summer19.model.Orders;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class OrdersDaoJdbcImpl implements OrdersDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final static String SELECT_ALL =
            "select o.orders_id, o.orders_name from orders d order by 2";

    private final static String ADD_DEPARTMENT = "insert into orders (orders_name) values (:ordersName)";

    public OrdersDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Orders add(Orders orders) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ordersName", orders.getOrdersName());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_DEPARTMENT, parameters, generatedKeyHolder);
        orders.setOrdersId(generatedKeyHolder.getKey().intValue());
        return orders;
    }

    @Override
    public void update(Orders orders) {

    }

    @Override
    public void delete(Integer ordersId) {

    }

    @Override
    public List<Orders> findAll() {
        List<Orders> orderss =
                namedParameterJdbcTemplate.query(SELECT_ALL, new OrdersRowMapper());
        return orderss;
    }

    private class OrdersRowMapper implements RowMapper<Orders> {
        @Override
        public Orders mapRow(ResultSet resultSet, int i) throws SQLException {
            Orders orders = new Orders();
            orders.setOrdersId(resultSet.getInt("orders_id"));
            orders.setOrdersName(resultSet.getString("orders_name"));
            return orders;
        }
    }

}