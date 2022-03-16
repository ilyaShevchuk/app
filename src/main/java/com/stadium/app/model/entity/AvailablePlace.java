package com.stadium.app.model.entity;

import com.stadium.app.model.enums.Sector;
import lombok.Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

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
    private HashMap<String, Float> prices = new HashMap<String, Float>();



    public AvailablePlace() {
        A1 = 1000;
        A2 = 1000;
        B1 = 1000;
        B2 = 1000;
        B3 = 1000;
        Lounge = 50;
        for (Sector sector : Sector.values()){
            prices.put(sector.name(), (float) Math.random() * 1000F);
        }
    }
    public void setPrices(Float A1Price, Float A2Price, Float B1Price, Float B2Price, Float B3Price, Float LoungePrice){
        prices.clear();
        prices.put(Sector.A1.name(), A1Price);
        prices.put(Sector.A2.name(), A2Price);
        prices.put(Sector.B1.name(), B1Price);
        prices.put(Sector.B2.name(), B2Price);
        prices.put(Sector.B3.name(), B3Price);
        prices.put(Sector.Lounge.name(), LoungePrice);
    }

    public Map<String, Float> getPrices(){
        return prices;
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
