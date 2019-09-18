package com.epam.summer19.dto;

import java.time.LocalDateTime;

public class DateTimeFilterDTO {

    LocalDateTime startDateTime;

    LocalDateTime endDateTime;

    public DateTimeFilterDTO() {
        this.startDateTime = LocalDateTime.MIN;
        this.endDateTime = LocalDateTime.MAX;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
}
