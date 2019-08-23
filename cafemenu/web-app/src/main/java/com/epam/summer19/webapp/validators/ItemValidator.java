package com.epam.courses.paycom.webapp.validators;

import com.epam.summer19.model.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

@Component
public class ItemValidator implements Validator {

    public static final int ITEM_NAME_MAX_SIZE = 255;
    public static final int ITEM_MAX_PRICE = 24;

    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "itemName", "itemName.empty");
        ValidationUtils.rejectIfEmpty(errors, "itemPrice", "itemPrice.empty");
        Item item = (Item) target;

        if (StringUtils.hasLength(item.getItemName())
                && item.getItemName().length() > ITEM_NAME_MAX_SIZE) {
            errors.rejectValue("itemName", "itemName.maxSize255");
        }

        if (StringUtils.hasLength(item.getItemPrice())
                && company.getCompanyUNP().length() != COMPANY_UNP_SIZE)   {
            errors.rejectValue("companyUNP", "companyUNP.Size9");
        }

        if (StringUtils.hasLength(company.getCompanyUNP())
                && !company.getCompanyUNP().matches(NUMBER)) {
            errors.rejectValue("companyUNP", "companyUNP.Number");
        }
    }
}