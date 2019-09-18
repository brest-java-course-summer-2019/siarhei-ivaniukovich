package com.epam.summer19.web_app;

import com.epam.summer19.model.Item;
import com.epam.summer19.dto.DateTimeFilterDTO;
import com.epam.summer19.model.ItemInOrder;
import com.epam.summer19.model.Order;
import com.epam.summer19.service.ItemInOrderService;
import com.epam.summer19.service.ItemService;
import com.epam.summer19.service.OrderService;
import com.epam.summer19.web_app.validators.OrderValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Order controller
 */
@Controller
public class OrderController {
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemInOrderService itemInOrderService;

    @Autowired
    private ItemService itemService;

    @Autowired
    OrderValidator orderValidator;

    /**
     * List all orders page
     * @param model
     * @return
     *
    @GetMapping(value = "/orders_")
    public final String listAllOrders(Model model) {
        LOGGER.debug("ListAllOrders findAll({})", model);
        model.addAttribute("orders_", orderService.findAll());
        return "orders_";
    }*/

    /**
     * List all ordersDTO page with price SUM & items count
     * @param model
     * @return
     */
    @GetMapping(value = "/ordersdto")
    public final String listAllOrdersDTO(Model model) {
        LOGGER.debug("ListAllOrdersDTO findAllDTO({})", model);
        model.addAttribute("ordersdto", orderService.findAllDTO());
        DateTimeFilterDTO dateTimeFilterDTO = new DateTimeFilterDTO();
        model.addAttribute("dateTimeFilterDTO", dateTimeFilterDTO);
        return "ordersdto";
    }


    /**
     * List all ordersDTO page filtered between START and END date&time
     * @param model
     * @return
     *
    @GetMapping(value = "/ordersdto/{startDateTime}/{endDateTime}")
    public final String listAllOrdersDTOByDateTime(
            @PathVariable("startDateTime") String startDateTime,
            @PathVariable("endDateTime") String endDateTime, Model model) {
        LOGGER.debug("findOrdersByDateTime({}{})", startDateTime, endDateTime, model);
        model.addAttribute("isNew", false);
        model.addAttribute("startDateTimeA", startDateTime);
        model.addAttribute("endDateTimeA", endDateTime);
        model.addAttribute("ordersdto", orderService.findOrdersDTOByDateTime(
                LocalDateTime.parse(startDateTime,DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                LocalDateTime.parse(endDateTime,DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        ));
        return "ordersdto";
    }*/

    /** TODO: Complete Date&Time FILTER ! **/
    @GetMapping(value = "/ordersdtofilterbydatetime")
    public final String listAllOrdersDTOByDateTime(DateTimeFilterDTO dateTimeFilterDTO, Model model) {
        LOGGER.debug("findOrdersByDateTime({})", model);
        model.addAttribute("ordersdto", orderService.findOrdersDTOByDateTime(
                dateTimeFilterDTO.getStartDateTime(), dateTimeFilterDTO.getEndDateTime()));
        model.addAttribute("dateTimeFilterDTO", dateTimeFilterDTO);
        return "ordersdtofilterbydatetime";
    }

    @PostMapping(value = "/ordersdtofilterbydatetime")
    public final String postAllOrdersDTOByDateTime(DateTimeFilterDTO dateTimeFilterDTO, BindingResult result,
                                                   Model model) {
        LOGGER.debug("Post filter byDateTime params({}, {})", dateTimeFilterDTO, result);
        model.addAttribute("ordersdto",orderService.findOrdersDTOByDateTime(
                dateTimeFilterDTO.getStartDateTime(), dateTimeFilterDTO.getEndDateTime()));
        return "ordersdtofilterbydatetime";
    }





    /**
     * GOTO Order add page
     * @param model
     * @return
     */
    @GetMapping(value = "/order")
    public final String gotoAddOrderPage(Model model) {
        LOGGER.debug("gotoAddOrderPage({})", model);
        Order order = new Order();
        model.addAttribute("isNew", true);
        model.addAttribute("order", order);
        return "order";
    }

    /**
     * Add order
     * @param order
     * @param result
     * @return
     */
    @PostMapping(value = "/order")
    public final String addOrder(@Valid Order order, BindingResult result) {
        LOGGER.debug("addOrder({}, {})", order, result);
        orderValidator.validate(order, result);
        if (result.hasErrors()) {
            return "order";
        } else {
            order = orderService.add(order);
            return "redirect:/order/"+order.getOrderId();
        }

    }

    /**
     * GOTO Edit order page
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/order/{id}")
    public final String gotoEditOrderPage(@PathVariable Integer id, Model model) {
        LOGGER.debug("gotoEditOrderPage({},{})", id, model);
        Order order = orderService.findOrderById(id);
        List<ItemInOrder> iteminorders = itemInOrderService.findIioByOrderId(id);
        List<Item> items =  itemService.findAll();
        ItemInOrder iteminorderin = new ItemInOrder();
        model.addAttribute("isNew", false);
        model.addAttribute("iteminorders", iteminorders);
        model.addAttribute("iteminorderin", iteminorderin);
        model.addAttribute("items", items);
        model.addAttribute("order", order);
        return "order";
    }

    /**
     * Update edited order
     * @param order
     * @param result
     * @return
     */
    @PostMapping(value = "/order/{id}")
    public final String updateOrder(@Valid Order order, BindingResult result) {
        LOGGER.debug("updateOrder({})", order);
        orderValidator.validate(order, result);
        if (result.hasErrors()) {
            return "order";
        } else {
            this.orderService.update(order);
        }
        return "redirect:/ordersdto";
    }

    /**
     * Delete order
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/orders/{id}/delete")
    public final String deleteOrder(@PathVariable Integer id, Model model) {
        LOGGER.debug("delete({},{})", id, model);
        orderService.delete(id);
        return "redirect:/ordersdto";
    }


}
