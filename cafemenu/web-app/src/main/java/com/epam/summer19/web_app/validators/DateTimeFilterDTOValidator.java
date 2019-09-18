package com.epam.summer19.web_app.validators;

import com.epam.summer19.dto.DateTimeFilterDTO;
import com.epam.summer19.model.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class DateTimeFilterDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return dateTimeFilterDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "startDateTime", "dateTimeFilterDTO.invalidStartDateTime");
        ValidationUtils.rejectIfEmpty(errors, "endDateTime", "dateTimeFilterDTO.invalidStartDateTime");
        DateTimeFilterDTO dateTimeFilterDTO = (DateTimeFilterDTO) target;

        if (dateTimeFilterDTO.getStartDateTime() == null) {
            errors.rejectValue("startDateTime", "dateTimeFilterDTO.invalidStartDateTime");
        }

        if (dateTimeFilterDTO.getEndDateTime() == null) {
            errors.rejectValue("endDateTime", "dateTimeFilterDTO.invalidEndDateTime");
        }

    }
}