package com.stadium.app.model.dto;

import com.stadium.app.model.enums.Status;
import lombok.Getter;

public class TicketDto {

    @Getter
    private String eventName;

    @Getter
    private String date;

    @Getter
    private String price;

    @Getter
    private String eventStatus;

    public TicketDto(String eventName, String date, float price, Status eventStatus) {
        this.eventName = eventName;
        this.date = date;
        this.price = String.valueOf(price);
        this.eventStatus = eventStatus.name();
    }
}
