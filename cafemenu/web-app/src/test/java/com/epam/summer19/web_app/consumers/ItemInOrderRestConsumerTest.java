package com.epam.summer19.web_app.consumers;

import com.epam.summer19.model.ItemInOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ItemInOrderRestConsumerTest {

    @Mock
    private RestTemplate mockRestTemplate;

    private ItemInOrderRestConsumer itemInOrderRestConsumerUnderTest;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        itemInOrderRestConsumerUnderTest = new ItemInOrderRestConsumer("url", mockRestTemplate);
    }

    @Test
    public void testFindAll() throws Exception {
        final List<ItemInOrder> expectedResult = Arrays.asList();
        when(mockRestTemplate.getForEntity("url/all", List.class))
                .thenReturn(new ResponseEntity<>(new ArrayList(), HttpStatus.OK));

        final List<ItemInOrder> result = itemInOrderRestConsumerUnderTest.findAll();

        assertEquals(expectedResult, result);
    }

    @Test
    public void testAdd() throws Exception {
        final ItemInOrder itemInOrder = null;
        when(mockRestTemplate.postForEntity("url", createItemInOrder(2), ItemInOrder.class))
                .thenReturn(new ResponseEntity<>(createItemInOrder(1),HttpStatus.OK));

        itemInOrderRestConsumerUnderTest.add(itemInOrder);
    }


    @Test
    public void testUpdate() throws Exception {
        final ItemInOrder itemInOrder = createItemInOrder(3);
        itemInOrderRestConsumerUnderTest.update(itemInOrder);

        verify(mockRestTemplate).put("url", itemInOrder);
    }

    @Test
    public void testDelete() throws Exception {
        final Integer itemInOrderId = 1;

        itemInOrderRestConsumerUnderTest.delete(itemInOrderId, itemInOrderId);

        verify(mockRestTemplate).delete("url/1/1");
    }


    @Test
    public void testFindItemInOrderByOrderId() throws Exception {
        final Integer itemInOrderId = 1;
        final ArrayList<ItemInOrder> expectedResult = new ArrayList();
        when(mockRestTemplate.getForEntity("url/"+itemInOrderId, List.class))
                .thenReturn(new ResponseEntity<>(new ArrayList(), HttpStatus.OK));

        final List<ItemInOrder> result = itemInOrderRestConsumerUnderTest.findIioByOrderId(itemInOrderId);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindItemInOrderByOrderItemId() throws Exception {
        final Integer orderId = 1, itemId = 2;
        final ItemInOrder expectedResult = createItemInOrder(orderId);
        when(mockRestTemplate.getForEntity("url/"+orderId+"/"+itemId, ItemInOrder.class))
                .thenReturn(new ResponseEntity<>(createItemInOrder(orderId), HttpStatus.OK));
        
        final ItemInOrder result = itemInOrderRestConsumerUnderTest.findIioByOrderItemId(orderId,itemId);

        assertEquals(expectedResult.getIioItemName(), result.getIioItemName());
    }

    private ItemInOrder createItemInOrder(int itemInOrderId) {
        ItemInOrder itemInOrder = new ItemInOrder();
        itemInOrder.setIioOrderId(itemInOrderId);
        itemInOrder.setIioItemId(itemInOrderId+1);
        itemInOrder.setIioItemName("Name"+itemInOrderId);
        itemInOrder.setIioItemPrice(new BigDecimal(itemInOrderId+1));
        itemInOrder.setIioItemCount(1);
        return itemInOrder;
    }
}
