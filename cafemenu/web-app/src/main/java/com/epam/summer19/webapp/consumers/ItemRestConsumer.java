package com.epam.summer19.webapp.consumers;

import com.epam.summer19.model.Item;
import com.epam.summer19.service.ItemService;
import com.epam.summer19.webapp.ItemController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Item Consumer (for REST)
 */
public class ItemRestConsumer implements ItemService {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    private String url;

    private RestTemplate restTemplate;

    /**
     * ItemRestConsumer constructor.
     * @param url
     * @param restTemplate
     */
    public ItemRestConsumer(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    /**
     * findAll() - get all items
     * @return
     */
    @Override
    public List<Item> findAll() {
        LOGGER.debug("findAll()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/all", List.class);
        return (List<Item>) responseEntity.getBody();
    }

    /**
     * add() new multiple items.
     * @param
     */
    @Override
    public void add(Item... items) {
        LOGGER.debug("Multiple items add({})");
        for(Item item : items) {
            restTemplate.postForEntity(url, item, Item.class);
        }
    }

    /**
     * add() new item.
     * @param
     */
    @Override
    public void add(Item item) {
        LOGGER.debug("add({})", item);
        restTemplate.postForEntity(url, item, Item.class);
    }

    /**
     * update() item
     * @param
     */
    @Override
    public void update(Item item) {
        LOGGER.debug("update({})", item);
        restTemplate.put(url, item);

    }

    /**
     * delete() item
     * @param
     */
    @Override
    public void delete(Integer itemId) {
        LOGGER.debug("delete({})", itemId);
        restTemplate.delete(url + "/item/" + itemId);
    }

    /**
     * findItemById() item
     * @param itemId
     * @return
     */
    @Override
    public Item findItemById(Integer itemId) {
        LOGGER.debug("findItemById({})", itemId);
        ResponseEntity<Item> responseEntity = restTemplate.getForEntity(url + "/" + itemId, Item.class);
        return responseEntity.getBody();
    }
}