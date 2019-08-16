package com.epam.summer19.dao;

import com.epam.summer19.model.Item;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ItemDaoJdbcImpl implements ItemDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final static String SELECT_ALL =
            "select item_id, item_name, item_price from item_table order by 1";
    private final static String ADD_ITEM =
            "insert into item_table (item_name, item_price) values"
          + " (:itemName, :itemPrice)";
    private final static String DELETE_ITEM =
            "delete from item_table where item_id = :itemId";
    private final static String UPDATE_ITEM =
            "update item_table set item_name = :itemName,"
          + " item_price = :itemPrice where item_id = :itemId";
    private final static String FIND_BY_ITEM_ID =
            "select item_id, item_name, item_price from item_table where item_id = :itemId";
    private final static String ITEM_ID = "itemId";
    private final static String ITEM_NAME = "itemName";
    private final static String ITEM_PRICE = "itemPrice";

    public ItemDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }


    @Override
    public Item add(Item item) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(ITEM_NAME, item.getItemName());
        parameters.addValue(ITEM_PRICE, item.getItemPrice());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_ITEM, parameters, generatedKeyHolder);
        item.setItemId(generatedKeyHolder.getKey().intValue());
        return item;
    }

    @Override
    public void update(Item item) {
        Optional.of(namedParameterJdbcTemplate.update(UPDATE_ITEM, new BeanPropertySqlParameterSource(item)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update item in DB"));
    }

    @Override
    public void delete(Integer itemId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(ITEM_ID, itemId);
        Optional.of(namedParameterJdbcTemplate.update(DELETE_ITEM, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete item from DB"));
    }

    @Override
    public List<Item> findAll() {
        List<Item> items =
                namedParameterJdbcTemplate.query(SELECT_ALL, BeanPropertyRowMapper.newInstance(Item.class));
        return items;
    }

    @Override
    public Optional<Item> findItemById(Integer itemId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(ITEM_ID, itemId);
        List<Item> results = namedParameterJdbcTemplate.query(FIND_BY_ITEM_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(Item.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

}