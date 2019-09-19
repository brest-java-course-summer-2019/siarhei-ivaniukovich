package com.epam.summer19.web_app.validators;

import com.epam.summer19.model.ItemInOrder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ItemInOrderValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ItemInOrder.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "iioItemCount", "iioItemCount.empty");
        ItemInOrder itemInOrder = (ItemInOrder) target;

        if (itemInOrder.getIioItemCount().intValue() < 1) {
            errors.rejectValue("iioItemCount", "iioItemCount.lessthanone");
        }

    }
}