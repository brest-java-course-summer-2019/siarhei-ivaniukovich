package com.epam.summer19.web_app.validators;

import com.epam.summer19.model.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class OrderValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Order.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "orderEmployeeId", "orderEmployeeId.empty");
        Order order = (Order) target;

        if (order.getOrderEmployeeId() != null && order.getOrderEmployeeId().intValue() < 0) {
            errors.rejectValue("orderEmployeeId", "orderEmployeeId.negative");
        }
    }
}