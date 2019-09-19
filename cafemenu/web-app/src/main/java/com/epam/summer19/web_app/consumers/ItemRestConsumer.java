package com.epam.summer19.web_app.consumers;

import com.epam.summer19.model.Item;
import com.epam.summer19.service.ItemService;
import com.epam.summer19.web_app.ItemController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Item Consumer (for REST)
 */
public class ItemRestConsumer implements ItemService {

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
        LOGGER.debug("ItemRestConsumer findAll()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<Item>) responseEntity.getBody();
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
        restTemplate.delete(url + "/" + itemId);
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

    @Override
    public Item findItemByName(String itemName) {
        LOGGER.debug("findItemByName({})", itemName);
        ResponseEntity<Item> responseEntity = restTemplate.getForEntity(url + "/byname/" + itemName, Item.class);
        return responseEntity.getBody();
    }
}