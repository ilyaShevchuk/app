package com.stadium.app.model.requestBody;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class PayRequestBody {
    private String eventId;
    private String sector;
    private String price;
}
