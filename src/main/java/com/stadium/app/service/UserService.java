package com.stadium.app.service;

import com.stadium.app.model.entity.*;
import com.stadium.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	AvailablePlacesRepository availablePlacesRepository;

	public UserEntity findUserByUsername(String username) {
		return userRepository.findByUsernameIgnoreCase(username.toLowerCase());
	}
	//change return type to UserDto
	public UserEntity getUserById(Long id){
		return userRepository.getById(id);
	}


	public UserEntity saveUser(UserEntity userEntity) {
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		Role userRole = roleRepository.findByRole("ROLE_USER");
		userEntity.setRoles(Arrays.asList(userRole));
		
        return userEntity = userRepository.save(userEntity);
	}
	
	public UserEntity saveAdmin(UserEntity userEntity) {
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		Role adminRole = roleRepository.findByRole("ROLE_ADMIN");
		userEntity.setRoles(Arrays.asList(adminRole));
		
        return userEntity = userRepository.save(userEntity);
	}
	
}
