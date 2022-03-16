package com.stadium.app.repository;

import com.stadium.app.model.entity.AvailablePlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailablePlacesRepository extends JpaRepository<AvailablePlace, Long> {
}
