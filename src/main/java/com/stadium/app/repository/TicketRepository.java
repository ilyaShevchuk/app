package com.stadium.app.repository;

import com.stadium.app.model.entity.Ticket;
import com.stadium.app.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByEvent(String eventName);

    List<Ticket> findAllByUser(UserEntity user);
}
