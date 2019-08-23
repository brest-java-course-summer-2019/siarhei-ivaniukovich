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
 * Client Consuemr (for REST)
 */
public class ClientRestConsumer implements ItemService {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    private String url;

    private RestTemplate restTemplate;

    /**
     * ItemRestConsumer constructor.
     * @param url request url
     * @param restTemplate  rest Template
     */
    public ClientRestConsumer(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    /**
     * findAll() method gets list of clients through rest service.
     * @return body of response entity clients records
     */
    @Override
    public List<Item> findAll() {
        LOGGER.debug("findAll");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/all", List.class);
        return (List<Client>) responseEntity.getBody();
    }

    /**
     * findAllDTOs() method gets list of clients DTO through rest service.
     * @return body of response entity clients DTO.
     */
    @Override
    public List<ClientDTO> findAllDTOs() {
        LOGGER.debug("findAllDTOs()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/all-dto", List.class);
        return  (List<ClientDTO>) responseEntity.getBody();
    }

    /**
     * findById() method gets client by ID through rest service.
     * @param clientId client ID for getting.
     * @return body of response entity client by ID.
     */
    @Override
    public Client findById(Integer clientId) {
        LOGGER.debug("findById({})", clientId);
        ResponseEntity<Client> responseEntity = restTemplate.getForEntity(url + "/client/" + clientId,
                Client.class);
        return responseEntity.getBody();
    }

    /**
     * findDTOById() method gets client DTO by ID through rest service.
     * @param clientId DTO client ID for getting.
     * @return body of response entity client DTO by ID.
     */
    @Override
    public ClientDTO findDTOById(Integer clientId) {
        LOGGER.debug("findById({})", clientId);
        ResponseEntity<ClientDTO> responseEntity = restTemplate.getForEntity(url + "/client/" + clientId,
                ClientDTO.class);
        return responseEntity.getBody();
    }

    /**
     * findDTOsByDate() method gets clients DTO by date interval through rest service.
     * @param startDate interval start date.
     * @param endDate interval end date.
     * @return body of response entity clients DTO by date interval.
     */
    @Override
    public List<ClientDTO> findDTOsByDate(String startDate, String endDate) {
        LOGGER.debug("findDTOsByDate()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/dto/" + startDate
                + "/" + endDate, List.class);
        return  (List<ClientDTO>) responseEntity.getBody();
    }

    /**
     * add() method create new client through rest service.
     * @param client new client.
     */
    @Override
    public void add(Client client) {
        LOGGER.debug("add({})", client);
        restTemplate.postForEntity(url, client, Client.class);
    }

    /**
     * update() method update client through rest service.
     * @param client client for updating.
     */
    @Override
    public void update(Client client) {
        LOGGER.debug("update({})", client);
        restTemplate.put(url, client);

    }

    /**
     * delete() method delete client through rest service.
     * @param clientId client ID for delete.
     */
    @Override
    public void delete(int clientId) {
        LOGGER.debug("delete({})", clientId);
        restTemplate.delete(url + "/client/" + clientId);
    }
}