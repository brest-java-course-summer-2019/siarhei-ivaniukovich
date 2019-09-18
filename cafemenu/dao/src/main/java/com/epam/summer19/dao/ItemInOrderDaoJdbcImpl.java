package com.epam.summer19.dao;

import com.epam.summer19.model.ItemInOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ItemInOrderDaoJdbcImpl implements ItemInOrderDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${iio.findAll}")
    private String findAllSql;

    @Value("${iio.add}")
    private String addSql;

    @Value("${iio.delete}")
    private String deleteSql;

    @Value("${iio.update}")
    private String updateSql;

    @Value("${iio.findByOrderId}")
    private String findByOrderIdSql;

    @Value("${iio.findByOrderItemId}")
    private String findByOrderItemIdSql;


    private static final String IIO_ORDER_ID = "iioOrderId";
    private static final String IIO_ITEM_ID = "iioItemId";
    private static final String IIO_ITEM_NAME = "iioItemName";
    private static final String IIO_ITEM_PRICE = "iioItemPrice";
    private static final String IIO_ITEM_COUNT = "iioItemCount";

    public ItemInOrderDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public ItemInOrder add(ItemInOrder iteminorder) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(IIO_ORDER_ID, iteminorder.getIioOrderId());
        parameters.addValue(IIO_ITEM_ID, iteminorder.getIioItemId());
        parameters.addValue(IIO_ITEM_NAME, iteminorder.getIioItemName());
        parameters.addValue(IIO_ITEM_PRICE, iteminorder.getIioItemPrice());
        parameters.addValue(IIO_ITEM_COUNT, iteminorder.getIioItemCount());

        namedParameterJdbcTemplate.update(addSql, parameters);
        return iteminorder;
    }

    @Override
    public void update(ItemInOrder iteminorder) {
        if (namedParameterJdbcTemplate.update(updateSql, new BeanPropertySqlParameterSource(iteminorder)) < 1) {
            throw new RuntimeException("ItemInOrder DAO: Failed to update iteminorder in DB");
        }
    }

    @Override
    public void delete(Integer iioOrderId, Integer iioItemId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(IIO_ORDER_ID, iioOrderId);
        mapSqlParameterSource.addValue(IIO_ITEM_ID, iioItemId);
        if (namedParameterJdbcTemplate.update(deleteSql, mapSqlParameterSource) < 1) {
            throw new RuntimeException("ItemInOrder DAO: Failed to delete iteminorder from DB");
        }

    }

    @Override
    public List<ItemInOrder> findAll() {
        List<ItemInOrder> iteminorderlist =
                namedParameterJdbcTemplate.query(findAllSql, BeanPropertyRowMapper.newInstance(ItemInOrder.class));
        return iteminorderlist;

    }

    @Override
    public List<ItemInOrder> findIioByOrderId(Integer iioOrderId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(IIO_ORDER_ID, iioOrderId);
        List<ItemInOrder> results = namedParameterJdbcTemplate.query(findByOrderIdSql, namedParameters,
                BeanPropertyRowMapper.newInstance(ItemInOrder.class));
        return results;
    }

    @Override
    public Optional<ItemInOrder> findIioByOrderItemId(Integer iioOrderId, Integer iioItemId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(IIO_ORDER_ID, iioOrderId);
        parameters.addValue(IIO_ITEM_ID, iioItemId);
        List<ItemInOrder> results = namedParameterJdbcTemplate.query(findByOrderItemIdSql, parameters,
                BeanPropertyRowMapper.newInstance(ItemInOrder.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }
}
