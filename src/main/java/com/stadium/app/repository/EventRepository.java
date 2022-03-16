package com.stadium.app.repository;

import com.stadium.app.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

    Event findEventByName(String name);

    void deleteByName(String name);

}
