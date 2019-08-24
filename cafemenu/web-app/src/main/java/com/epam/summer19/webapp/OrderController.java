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
     * List all orders page
     * @param model
     * @return
     */
    @GetMapping(value = "/orders")
    public final String listAllOrders(Model model) {
        LOGGER.debug("ListAllOrders findAll({})", model);
        model.addAttribute("orders", orderService.findAll());
        return "orders";
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
            this.orderService.add(order);
            return "redirect:/orders";
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
}
