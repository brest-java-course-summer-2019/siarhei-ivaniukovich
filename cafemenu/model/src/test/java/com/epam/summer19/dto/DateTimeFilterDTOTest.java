package com.epam.summer19.dto;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * model test for Cafe Menu.
 */
public class DateTimeFilterDTOTest {

    private DateTimeFilterDTO dateTimeFilterDTO = new DateTimeFilterDTO();

    @Test
    public void getStartDateTime() {
        LocalDateTime datetime = LocalDateTime.now();
        dateTimeFilterDTO.setStartDateTime(datetime);
        Assert.assertTrue(dateTimeFilterDTO.getStartDateTime().equals(datetime));
    }

    @Test
    public void getEndDateTime() {
        LocalDateTime datetime = LocalDateTime.now();
        dateTimeFilterDTO.setEndDateTime(datetime);
        Assert.assertTrue(dateTimeFilterDTO.getEndDateTime().equals(datetime));
    }

}
