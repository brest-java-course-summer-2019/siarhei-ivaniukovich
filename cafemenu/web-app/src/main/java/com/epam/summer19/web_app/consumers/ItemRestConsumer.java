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
        LOGGER.debug("ItemRestConsumer: findAll()");
        return (List<Item>) restTemplate.getForEntity(url, List.class).getBody();
    }


    /**
     * add() new item.
     * @param
     */
    @Override
    public void add(Item item) {
        LOGGER.debug("ItemRestConsumer: add({})", item);
        restTemplate.postForEntity(url, item, Item.class);
    }

    /**
     * update() item
     * @param
     */
    @Override
    public void update(Item item) {
        LOGGER.debug("ItemRestConsumer: update({})", item);
        restTemplate.put(url, item);

    }

    /**
     * delete() item
     * @param
     */
    @Override
    public void delete(Integer itemId) {
        LOGGER.debug("ItemRestConsumer: delete({})", itemId);
        restTemplate.delete(url + "/" + itemId);
    }

    /**
     * findItemById() item
     * @param itemId
     * @return
     */
    @Override
    public Item findItemById(Integer itemId) {
        LOGGER.debug("ItemRestConsumer: findItemById({})", itemId);
        return restTemplate.getForEntity(url + "/" + itemId, Item.class).getBody();
    }

    @Override
    public Item findItemByName(String itemName) {
        LOGGER.debug("ItemRestConsumer: findItemByName({})", itemName);
        // fixme: itemName to REST sending with extra quotes -> ????
        return restTemplate.postForEntity(url + "/byname", itemName, Item.class).getBody();
    }
}