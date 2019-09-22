package com.epam.summer19.web_app.consumers;

import com.epam.summer19.model.ItemInOrder;
import com.epam.summer19.service.ItemInOrderService;
import com.epam.summer19.web_app.ItemInOrderController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * ItemInOrder Consumer (for REST)
 */
public class ItemInOrderRestConsumer implements ItemInOrderService {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemInOrderController.class);

    private String url;

    private RestTemplate restTemplate;

    /**
     * ItemInOrderRestConsumer constructor.
     * @param url
     * @param restTemplate
     */
    public ItemInOrderRestConsumer(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    /**
     * findAll() - get all iios
     * @return
     */
    @Override
    public List<ItemInOrder> findAll() {
        LOGGER.debug("findAll()");
        return restTemplate.getForEntity(url + "/all", List.class).getBody();
    }

    /**
     * add() new iio.
     * @param
     */
    @Override
    public void add(ItemInOrder iio) {
        LOGGER.debug("add({})", iio);
        restTemplate.postForEntity(url, iio, ItemInOrder.class);
    }

    /**
     * update() iio
     * @param
     */
    @Override
    public void update(ItemInOrder iio) {
        LOGGER.debug("update({})", iio);
        restTemplate.put(url, iio);

    }

    /**
     * delete() iio
     * @param
     */
    @Override
    public void delete(Integer iioOrderId, Integer iioItemId) {
        LOGGER.debug("delete({}{})", iioOrderId, iioItemId);
        restTemplate.delete(url + "/" + iioOrderId + "/" + iioItemId);
    }

    /**
     * findItemInOrderByDateTime()
     * @param
     * @return
     */
    @Override
    public List<ItemInOrder> findIioByOrderId(Integer iioOrderId) {
        LOGGER.debug("findIioByOrderId({})", iioOrderId);
        return restTemplate.getForEntity(url + "/" + iioOrderId, List.class).getBody();
    }

    /**
     * findItemInOrderById() iio
     * @param iioOrderId
     * @param iioItemId
     * @return
     */
    @Override
    public ItemInOrder findIioByOrderItemId(Integer iioOrderId, Integer iioItemId) {
        LOGGER.debug("findIioByOrderId({}{})", iioOrderId, iioItemId);
        return restTemplate.getForEntity(
                url + "/" + iioOrderId + "/" + iioItemId, ItemInOrder.class).getBody();
    }


}