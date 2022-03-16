package com.stadium.app.service;

import com.stadium.app.model.entity.Role;
import com.stadium.app.model.entity.UserEntity;
import com.stadium.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByUsernameIgnoreCase(username.toLowerCase());

		if(userEntity == null)
			throw new UsernameNotFoundException("User Not Found");

		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		for (Role role : userEntity.getRoles()) {
			roles.add(new SimpleGrantedAuthority(role.getRole()));
		}

		return new org.springframework.security.core.userdetails.User(userEntity.getUsername(), userEntity.getPassword(), roles);
	}

}
