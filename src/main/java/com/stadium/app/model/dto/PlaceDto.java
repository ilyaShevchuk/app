package com.stadium.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaceDto {
    private String sector;
    private Float price;
    private Integer places;
}
