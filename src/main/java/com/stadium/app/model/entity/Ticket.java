package com.stadium.app.model.entity;

import com.stadium.app.model.enums.Sector;
import com.stadium.app.model.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Proxy(lazy = false)
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    @Getter
    private long id;

    @Getter
    @Setter
    @Column(name = "event")
    private String event;

    @Getter
    @Setter
    @Column(name = "price")
    private float price;

    @Getter
    @Setter
    @Column(name = "status")
    private Status status;

    @Getter
    @Setter
    @Column(name = "sector")
    private Sector sector;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "user")
    private UserEntity user;

    public Ticket(){

    }
    public Ticket(UserEntity userEntity){
        this.user = userEntity;
    }

    public Ticket(String event, Float price, Status status, Sector sector) {
        this.event = event;
        this.price = price;
        this.status = status;
        this.sector = sector;
    }
}
