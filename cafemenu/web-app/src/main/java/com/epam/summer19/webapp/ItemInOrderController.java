package com.epam.summer19.webapp;

import com.epam.summer19.model.ItemInOrder;
import com.epam.summer19.service.ItemInOrderService;
import com.epam.summer19.webapp.validators.ItemInOrderValidator;
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
 * ItemInOrder controller
 */
@Controller
public class ItemInOrderController {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ItemInOrderService itemInOrderService;

    @Autowired
    ItemInOrderValidator iioValidator;

    /**
     * List all iios page
     * @param model
     * @return
     */
    @GetMapping(value = "/iios")
    public final String listAllItemInOrders(Model model) {
        LOGGER.debug("ListAllItemInOrders findAll({})", model);
        model.addAttribute("iios", itemInOrderService.findAll());
        return "iios";
    }

    /**
     * GOTO ItemInOrder add page
     * @param model
     * @return
     */
    @GetMapping(value = "/iio")
    public final String gotoAddItemInOrderPage(Model model) {
        LOGGER.debug("gotoAddItemInOrderPage({})", model);
        ItemInOrder iio = new ItemInOrder();
        model.addAttribute("isNew", true);
        model.addAttribute("iio", iio);
        return "iio";
    }

    /**
     * Add iio
     * @param iio
     * @param result
     * @return
     */
    @PostMapping(value = "/iio")
    public final String addItemInOrder(@Valid ItemInOrder iio, BindingResult result) {
        LOGGER.debug("addItemInOrder({}, {})", iio, result);
        iioValidator.validate(iio, result);
        if (result.hasErrors()) {
            return "iio";
        } else {
            this.itemInOrderService.add(iio);
            return "redirect:/iios";
        }
    }

    /**
     * GOTO Edit iio page
     * @param orderId, itemId
     * @param model
     * @return
     */
    @GetMapping(value = "/iio/{orderId}/{itemId}")
    public final String gotoEditItemInOrderPage(@PathVariable Integer orderId, Integer itemId, Model model) {
        LOGGER.debug("gotoEditItemInOrderPage({},{},{})", orderId, itemId, model);
        ItemInOrder iio = itemInOrderService.findIioByOrderItemId(orderId, itemId);
        model.addAttribute("iio", iio);
        return "iio";
    }

    /**
     * Update edited iio
     * @param iio
     * @param result
     * @return
     */
    @PostMapping(value = "/iio/{id}")
    public final String updateItemInOrder(@Valid ItemInOrder iio, BindingResult result) {
        LOGGER.debug("updateItemInOrder({})", iio);
        iioValidator.validate(iio, result);
        if (result.hasErrors()) {
            return "iio";
        } else {
            this.itemInOrderService.update(iio);
        }
        return "redirect:/iios";
    }

    /**
     * Delete iio
     * @param
     * @param model
     * @return
     */
    @GetMapping(value = "/iio/orderId/itemId/delete")
    public final String deleteItemInOrderById(@PathVariable Integer orderId, Integer itemId, Model model) {
        LOGGER.debug("delete({},{},{})", orderId, itemId, model);
        itemInOrderService.delete(orderId, itemId);
        return "redirect:/iios";
    }


}
