package com.stadium.app.model.dto;

import com.stadium.app.model.entity.Event;
import com.stadium.app.model.enums.Status;
import lombok.Getter;

public class EventDto {

    @Getter
    private final Long id;

    @Getter
    private final String name;

    @Getter
    private final String date;

    @Getter
    private Status status;

    public EventDto(Long id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.status = Status.AVAILABLE;
    }

    public EventDto(Event event) {
        this.id = event.getId();
        this.name = event.getName();
        this.date = event.getDate();
        this.status = event.getStatus();
    }
}
