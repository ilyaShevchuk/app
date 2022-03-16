package com.stadium.app.model.dto;

import java.util.Map;

public class PricesDto {

    private final Map<String, Float> prices;

    public PricesDto(Map<String, Float> prices) {
        this.prices = prices;
    }
}
