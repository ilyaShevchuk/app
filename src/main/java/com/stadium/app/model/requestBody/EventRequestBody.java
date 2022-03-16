package com.stadium.app.model.requestBody;

import lombok.Data;

@Data
public class EventRequestBody {

    private Long id;

    private String name;

    private String date = "Default date";

    private String status;

}
