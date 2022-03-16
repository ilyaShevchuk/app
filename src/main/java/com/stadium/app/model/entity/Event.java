package com.stadium.app.model.entity;

import com.stadium.app.model.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Proxy(lazy = false)
@Table(name = "events")
public class Event {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Column(name = "event_id")
    private long id;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Column(name = "date")
    private String date;

    @Getter
    @Setter
    @Column(name = "status")
    private Status status;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "places_id", referencedColumnName = "id")
    private AvailablePlace availablePlaces;

    public Event() {

    }

    public Event(String name, String date, AvailablePlace availablePlace) {
        this.name = name;
        this.date = date.toString();
        this.status = Status.AVAILABLE;
        this.availablePlaces = availablePlace;
    }
}