package com.epam.summer19.webapp;

import com.epam.summer19.model.Order;
import com.epam.summer19.service.OrderService;
import com.epam.summer19.webapp.validators.OrderValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Order controller
 */
@Controller
public class OrderController {
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    OrderValidator orderValidator;

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
            this.orderService.add(order);
        }
        return "redirect:/orders";
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
        return "redirect:/orders";
    }

    /**
     * Delete order
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/order/{id}/delete")
    public final String deleteOrder(@PathVariable Integer id, Model model) {
        LOGGER.debug("delete({},{})", id, model);
        orderService.delete(id);
        return "redirect:/orders";
    }

    /**
     * List all orders page
     * @param model
     * @return
     */
    @GetMapping(value = "/orders_")
    public final String listAllOrders(Model model) {
        LOGGER.debug("ListAllOrders findAll({})", model);
        model.addAttribute("orders_", orderService.findAll());
        return "orders_";
    }

    /**
     * List all ordersDTO page with price SUM & items count
     * @param model
     * @return
     */
    @GetMapping(value = "/orders")
    public final String listAllOrdersDTO(Model model) {
        LOGGER.debug("ListAllOrdersDTO findAllDTO({})", model);
        model.addAttribute("orders", orderService.findAllDTO());
        return "orders";
    }

    /**
     * List all orders page filtered between START and END date&time
     * @param model
     * @return
     */
    @GetMapping(value = "/orders/{startDateTime}/{endDateTime}")
    public final String listAllOrdersByDateTime(
            @PathVariable("startDateTime") String startDateTime,
            @PathVariable("endDateTime") String endDateTime, Model model) {
        LOGGER.debug("findOrdersByDateTime({}{})", startDateTime, endDateTime, model);
        model.addAttribute("isNew", false);
        model.addAttribute("orders", orderService.findOrdersByDateTime(
                LocalDateTime.parse(startDateTime,DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss")),
                LocalDateTime.parse(endDateTime,DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss"))
        ));
        return "orders";
    }

}
