package com.epam.summer19;

import com.epam.summer19.model.Order;
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
public class OrderDaoJdbcImpl implements OrderDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final static String SELECT_ALL =
            "select o.order_id, o.order_name from order d order by 2";

    private final static String ADD_DEPARTMENT = "insert into order (order_name) values (:orderName)";

    public OrderDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Order add(Order order) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("orderName", order.getOrderName());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_DEPARTMENT, parameters, generatedKeyHolder);
        order.setOrderId(generatedKeyHolder.getKey().intValue());
        return order;
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(Integer orderId) {

    }

    @Override
    public List<Order> findAll() {
        List<Order> orders =
                namedParameterJdbcTemplate.query(SELECT_ALL, new OrderRowMapper());
        return orders;
    }

    private class OrderRowMapper implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet resultSet, int i) throws SQLException {
            Order order = new Order();
            order.setOrderId(resultSet.getInt("order_id"));
            order.setOrderName(resultSet.getString("order_name"));
            return order;
        }
    }

}