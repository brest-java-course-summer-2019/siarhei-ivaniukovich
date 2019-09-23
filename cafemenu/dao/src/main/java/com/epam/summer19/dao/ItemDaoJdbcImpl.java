package com.epam.summer19.dao;

import com.epam.summer19.model.Item;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Component
public class ItemDaoJdbcImpl implements ItemDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(ItemDaoJdbcImpl.class);

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${item.findAll}")
    private String findAllSql;

    @Value("${item.add}")
    private String addSql;

    @Value("${item.delete}")
    private String deleteSql;

    @Value("${item.update}")
    private String updateSql;

    @Value("${item.findById}")
    private String findByIdSql;

    @Value("${item.findByName}")
    private String findByNameSql;

    private final static String ITEM_ID = "itemId";
    private final static String ITEM_NAME = "itemName";
    private final static String ITEM_PRICE = "itemPrice";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate1;

    public ItemDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Item add(Item item) {
        LOGGER.debug("ItemDaoJdbcImpl: add{})", item);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(ITEM_NAME, item.getItemName());
        parameters.addValue(ITEM_PRICE, item.getItemPrice());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(addSql, parameters, generatedKeyHolder);
        item.setItemId(generatedKeyHolder.getKey().intValue());
        return item;
    }

    @Override
    public void update(Item item) {
        LOGGER.debug("ItemDaoJdbcImpl: update({})", item);
        if(namedParameterJdbcTemplate.update(updateSql, new BeanPropertySqlParameterSource(item)) < 1) {
            throw new RuntimeException("Item DAO: Failed to update item in DB");
        }
    }

    @Override
    public void delete(Integer itemId) {
        LOGGER.debug("ItemDaoJdbcImpl: delete({})", itemId);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(ITEM_ID, itemId);
        if(namedParameterJdbcTemplate.update(deleteSql, mapSqlParameterSource) < 1) {
            throw new RuntimeException("Item DAO: Failed to delete item from DB");
        }
    }

    @Override
    public List<Item> findAll() {
        LOGGER.debug("ItemDaoJdbcImpl: findAll()");
        List<Item> items =
                namedParameterJdbcTemplate.query(findAllSql, BeanPropertyRowMapper.newInstance(Item.class));
        return items;
    }

    @Override
    public Optional<Item> findItemById(Integer itemId) {
        LOGGER.debug("ItemDaoJdbcImpl: findItemById({})", itemId);
        SqlParameterSource namedParameters = new MapSqlParameterSource(ITEM_ID, itemId);
        List<Item> results = namedParameterJdbcTemplate.query(findByIdSql, namedParameters,
                BeanPropertyRowMapper.newInstance(Item.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public Optional<Item> findItemByName(String itemName) {
        LOGGER.debug("ItemDaoJdbcImpl: findItemByName({})", itemName);
        SqlParameterSource namedParameters = new MapSqlParameterSource(ITEM_NAME, itemName);
        namedParameterJdbcTemplate1 = namedParameterJdbcTemplate;
        List<Item> results = namedParameterJdbcTemplate1.query(findByNameSql, namedParameters,
                BeanPropertyRowMapper.newInstance(Item.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }
}