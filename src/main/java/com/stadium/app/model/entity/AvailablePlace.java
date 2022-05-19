package com.stadium.app.model.entity;

import com.stadium.app.model.dto.PlaceDto;
import com.stadium.app.model.enums.Sector;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
public class AvailablePlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Integer A1;

    private Integer A2;

    private Integer B1;

    private Integer B2;

    private Integer B3;

    private Integer Lounge;

    @OneToOne(mappedBy = "availablePlaces")
    private Event event;

    @Transient
    private Map<String, Float> prices = new LinkedHashMap<>();

    public AvailablePlace() {
        A1 = 2000;
        A2 = 2000;
        B1 = 1000;
        B2 = 500;
        B3 = 500;
        Lounge = 50;
        for (Sector sector : Sector.values()){
            prices.put(sector.name(), (float) Math.random() * 1000F);
        }
    }
    public List<Integer> getPlaces(){
        List<Integer> places = new ArrayList<>();
        List<PlaceDto> dtos = new ArrayList<>();
        places.add(A1);
        places.add(A2);
        places.add(B1);
        places.add(B2);
        places.add(B3);
        places.add(Lounge);
        return places;
    }

    public Map<String, Float> getPrices(){
        return prices;
    }

    public List<PlaceDto> getDto(){
            List<PlaceDto> dtos = new ArrayList<>();
        int index = 0;
        for(var sector :Sector.values()){
            dtos.add(new PlaceDto(sector.name(), prices.get(sector.name()), getPlaces().get(index)));
            index++;
        }
        return dtos;
    }

    public Integer getPlacesBySectorName(Sector sector){
        Integer answer;
        switch (sector) {
            case A1 -> answer = A1;
            case A2 -> answer = A2;
            case B1 -> answer = B1;
            case B2 -> answer = B2;
            case B3 -> answer = B3;
            case Lounge -> answer = Lounge;
            default -> answer = 0;
        }
        return answer;
    }

    public Float buyTicket(Sector sector) {
        switch (sector) {
            case A1 -> this.A1--;
            case A2 -> this.A2--;
            case B1 -> this.B1--;
            case B2 -> this.B2--;
            case B3 -> this.B3--;
            case Lounge -> this.Lounge--;
        }
        return getPrices().get(sector.name());
    }
}
