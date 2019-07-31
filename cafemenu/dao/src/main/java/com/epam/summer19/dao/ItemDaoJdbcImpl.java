package com.epam.summer19.dao;

import com.epam.summer19.model.Item;
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
public class ItemDaoJdbcImpl implements ItemDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final static String SELECT_ALL =
            "select o.item_id, o.item_name from item d order by 2";

    private final static String ADD_ITEM = "insert into item (item_name) values (:itemName)";

    public ItemDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Item add(Item item) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("itemName", item.getItemName());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_DEPARTMENT, parameters, generatedKeyHolder);
        item.setItemId(generatedKeyHolder.getKey().intValue());
        return item;
    }

    @Override
    public void update(Item item) {

    }

    @Override
    public void delete(Integer itemId) {

    }

    @Override
    public List<Item> findAll() {
        List<Item> items =
                namedParameterJdbcTemplate.query(SELECT_ALL, new ItemRowMapper());
        return items;
    }

    private class ItemRowMapper implements RowMapper<Item> {
        @Override
        public Item mapRow(ResultSet resultSet, int i) throws SQLException {
            Item item = new Item();
            item.setItemId(resultSet.getInt("item_id"));
            item.setItemName(resultSet.getString("item_name"));
            return item;
        }
    }

}