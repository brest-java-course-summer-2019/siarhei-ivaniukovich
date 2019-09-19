package com.epam.summer19.web_app;

import com.epam.summer19.model.Item;
import com.epam.summer19.dto.DateTimeFilterDTO;
import com.epam.summer19.model.ItemInOrder;
import com.epam.summer19.model.Order;
import com.epam.summer19.service.ItemInOrderService;
import com.epam.summer19.service.ItemService;
import com.epam.summer19.service.OrderService;
import com.epam.summer19.web_app.validators.DateTimeFilterDTOValidator;
import com.epam.summer19.web_app.validators.OrderValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Order controller
 */
@Controller
@SessionAttributes("dateTimeFilterDTO")
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

    @Autowired
    DateTimeFilterDTOValidator dateTimeFilterDTOValidator;

    @ModelAttribute("dateTimeFilterDTO")
    DateTimeFilterDTO defDateTime() {
        DateTimeFilterDTO dateTimeFilterDTO = new DateTimeFilterDTO();
        dateTimeFilterDTO.setStartDateTime(LocalDateTime.now()
                .minusYears(25).truncatedTo(ChronoUnit.MINUTES));
        dateTimeFilterDTO.setEndDateTime(LocalDateTime.now()
                .plusHours(24).truncatedTo(ChronoUnit.MINUTES));
        return dateTimeFilterDTO;
    }

    @GetMapping(value = "/ordersdto")
    public final String listAllOrdersDTOByDateTime(
            @ModelAttribute("dateTimeFilterDTO") DateTimeFilterDTO dateTimeFilterDTO,
            Model model)
    {
        LOGGER.debug("listAllOrdersDTOByDateTime({})", dateTimeFilterDTO);
        model.addAttribute("isFilterExpanded", false);
        model.addAttribute("dateTimeFilterDTO", dateTimeFilterDTO);
        model.addAttribute("ordersdto", orderService.findOrdersDTOByDateTime(
                dateTimeFilterDTO.getStartDateTime(), dateTimeFilterDTO.getEndDateTime()));
        return "ordersdto";
    }

    @PostMapping(value = "/ordersdtofilterbydatetime")
    public final String postAllOrdersDTOByDateTime(
            @ModelAttribute DateTimeFilterDTO dateTimeFilterDTO,
            BindingResult result, Model model)
    {
        LOGGER.debug("Post filter byDateTime params({}, {})", dateTimeFilterDTO, result);
        dateTimeFilterDTOValidator.validate(dateTimeFilterDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("isFilterExpanded", true);
            return "ordersdto";
        } else {
            model.addAttribute("ordersdto",orderService.findOrdersDTOByDateTime(
                    dateTimeFilterDTO.getStartDateTime(), dateTimeFilterDTO.getEndDateTime()));
            return "ordersdto";
        }
    }

    @GetMapping(value = "/ordersdtofilterreset")
    public final String ordersDtoFilterReset(Model model)
    {
        DateTimeFilterDTO dateTimeFilterDTO = defDateTime();
        LOGGER.debug("listAllOrdersDTOByDateTime({})", dateTimeFilterDTO);
        model.addAttribute("isFilterExpanded", true);
        model.addAttribute("dateTimeFilterDTO", dateTimeFilterDTO);
        model.addAttribute("ordersdto", orderService.findOrdersDTOByDateTime(
                dateTimeFilterDTO.getStartDateTime(), dateTimeFilterDTO.getEndDateTime()));
        return "ordersdto";
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
