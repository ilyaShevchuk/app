package com.stadium.app.repository;

import com.stadium.app.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	Boolean existsByUsernameIgnoreCase(String username);
	
	UserEntity findByUsernameIgnoreCase(String username);
}
