package com.stadium.app.repository;

import com.stadium.app.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Role findByRole(String role);
	
}
