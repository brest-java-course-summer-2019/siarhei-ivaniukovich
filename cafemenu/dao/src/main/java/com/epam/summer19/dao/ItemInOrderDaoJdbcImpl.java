package com.epam.summer19.dao;

import com.epam.summer19.model.ItemInOrder;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;
import java.util.Optional;

public class ItemInOrderDaoJdbcImpl implements ItemInOrderDao {


    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final static String SELECT_ALL =
            "select iio_order_id, iio_item_id, iio_item_name, iio_item_price, "
          + "iio_item_count from item_in_order order by 1, 2";
    private final static String ADD_ITEM_IN_ORDER =
            "insert into item_in_order (iio_order_id, iio_item_id, iio_item_name, "
          + "iio_item_price, iio_item_count) values (:iioOrderId, :iioItemId, "
          + ":iioItemName, :iioItemPrice, :iioItemCount)";
    private final static String DELETE_ITEM_IN_ORDER =
            "delete from item_in_order where iio_order_id = :iioOrderId AND "
          + "iio_item_id = :iioItemId";
    private final static String UPDATE_ITEM_IN_ORDER =
            "update item_in_order set iio_order_id = :iioOrderId, iio_item_id = :iioItemId, "
                    + "iio_item_name = :iioItemName, iio_item_price = :iioItemPrice, "
                    + "iio_item_count = :iioItemCount where iio_order_id = :iioOrderId AND "
                    + "iio_item_id = :iioItemId";
    private final static String FIND_IIO_BY_ORDER_ID =
            "select iio_order_id, iio_item_id, iio_item_name, iio_item_price, "
          + "iio_item_count from item_in_order where iio_order_id = :iioOrderId";
    private final static String FIND_IIO_BY_ORDER_ITEM_ID =
            "select iio_order_id, iio_item_id, iio_item_name, iio_item_price, "
          + "iio_item_count from item_in_order where iio_order_id = :iioOrderId AND "
          + "iio_item_id = :iioItemId";

    private static final String IIO_ORDER_ID = "iioOrderId";
    private static final String IIO_ITEM_ID = "iioItemId";
    private static final String IIO_ITEM_NAME = "iioItemName";
    private static final String IIO_ITEM_PRICE = "iioItemPrice";
    private static final String IIO_ITEM_COUNT = "iioItemCount";

    public ItemInOrderDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    @Override
    public ItemInOrder add(ItemInOrder iteminorder) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(IIO_ORDER_ID, iteminorder.getIioOrderId());
        parameters.addValue(IIO_ITEM_ID, iteminorder.getIioItemId());
        parameters.addValue(IIO_ITEM_NAME, iteminorder.getIioItemName());
        parameters.addValue(IIO_ITEM_PRICE, iteminorder.getIioItemPrice());
        parameters.addValue(IIO_ITEM_COUNT, iteminorder.getIioItemCount());

        namedParameterJdbcTemplate.update(ADD_ITEM_IN_ORDER, parameters);
        return iteminorder;
    }

    @Override
    public void update(ItemInOrder iteminorder) {
        Optional.of(namedParameterJdbcTemplate.update(UPDATE_ITEM_IN_ORDER, new BeanPropertySqlParameterSource(iteminorder)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update itemINorder in DB"));
    }

    @Override
    public void delete(Integer iioOrderId, Integer iioItemId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(IIO_ORDER_ID, iioOrderId);
        mapSqlParameterSource.addValue(IIO_ITEM_ID, iioItemId);
        Optional.of(namedParameterJdbcTemplate.update(DELETE_ITEM_IN_ORDER, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete itemINorder from DB"));

    }

    @Override
    public List<ItemInOrder> findAll() {
        List<ItemInOrder> iteminorderlist =
                namedParameterJdbcTemplate.query(SELECT_ALL, BeanPropertyRowMapper.newInstance(ItemInOrder.class));
        return iteminorderlist;

    }

    @Override
    public List<ItemInOrder> findIioByOrderId(Integer iioOrderId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(IIO_ORDER_ID, iioOrderId);
        List<ItemInOrder> results = namedParameterJdbcTemplate.query(FIND_IIO_BY_ORDER_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(ItemInOrder.class));
        return results;
    }

    @Override
    public Optional<ItemInOrder> findIioByOrderItemId(Integer iioOrderId, Integer iioItemId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(IIO_ORDER_ID, iioOrderId);
        parameters.addValue(IIO_ITEM_ID, iioItemId);
        List<ItemInOrder> results = namedParameterJdbcTemplate.query(FIND_IIO_BY_ORDER_ITEM_ID, parameters,
                BeanPropertyRowMapper.newInstance(ItemInOrder.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }
}
