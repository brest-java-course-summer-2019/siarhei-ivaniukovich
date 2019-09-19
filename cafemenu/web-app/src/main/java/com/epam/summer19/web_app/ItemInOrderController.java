package com.epam.summer19.web_app;

import com.epam.summer19.model.ItemInOrder;
import com.epam.summer19.service.ItemInOrderService;
import com.epam.summer19.web_app.validators.ItemInOrderValidator;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemInOrderController.class);

    @Autowired
    private ItemInOrderService itemInOrderService;

    @Autowired
    ItemInOrderValidator iteminorderValidator;

    /**
     * Add iteminorder
     * @param iteminorderin
     * @param result
     * @return
     */
    @PostMapping(value = "/iteminorder")
    public final String addItemInOrder(@Valid ItemInOrder iteminorderin, BindingResult result) {
        LOGGER.debug("addItemInOrder({}, {})", iteminorderin, result);
        iteminorderValidator.validate(iteminorderin, result);
        ItemInOrder iioAlreadyInDB = this.itemInOrderService.findIioByOrderItemId(
                iteminorderin.getIioOrderId(), iteminorderin.getIioItemId());
        if(iioAlreadyInDB != null) {
            iioAlreadyInDB.setIioItemCount(iioAlreadyInDB.getIioItemCount() + iteminorderin.getIioItemCount());
            this.itemInOrderService.update(iioAlreadyInDB);
            return "redirect:/order/" + iteminorderin.getIioOrderId();
        }
        else {
            if (result.hasErrors()) {
                return "iteminorder";
            } else {
                this.itemInOrderService.add(iteminorderin);
                return "redirect:/order/" + iteminorderin.getIioOrderId();
            }
        }
    }

    /** ###### DO NOT DELETE !!! - for iio edit (quantity) may be needed #####
     * GOTO Edit iteminorder page
     * @param orderId, itemId
     * @param model
     * @return
     *
    @GetMapping(value = "/iteminorder/{orderId}/{itemId}")
    public final String gotoEditItemInOrderPage(@PathVariable Integer orderId, Integer itemId, Model model) {
        LOGGER.debug("gotoEditItemInOrderPage({},{},{})", orderId, itemId, model);
        ItemInOrder iteminorder = itemInOrderService.findIioByOrderItemId(orderId, itemId);
        model.addAttribute("iteminorder", iteminorder);
        return "iteminorder";
    }*/

    /**
     * Update edited iteminorder
     * @param iteminorder
     * @param result
     * @return
     *
    @PostMapping(value = "/iteminorder/{id}")
    public final String updateItemInOrder(@Valid ItemInOrder iteminorder, BindingResult result) {
        LOGGER.debug("updateItemInOrder({})", iteminorder);
        iteminorderValidator.validate(iteminorder, result);
        if (result.hasErrors()) {
            return "iteminorder";
        } else {
            this.itemInOrderService.update(iteminorder);
        }
        return "redirect:/iteminorders";
    }
    */

    /**
     * Delete iteminorder
     * @param
     * @param model
     * @return
     */
    @GetMapping(value = "/iteminorders/{orderId}/{itemId}/delete")
    public final String deleteItemInOrderById(@PathVariable Integer orderId,
                                              @PathVariable Integer itemId, Model model) {
        LOGGER.debug("delete({},{},{})", orderId, itemId, model);
        itemInOrderService.delete(orderId, itemId);
        return "redirect:/order/"+orderId;
    }


}
