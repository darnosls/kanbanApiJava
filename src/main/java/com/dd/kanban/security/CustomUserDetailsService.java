package com.dd.kanban.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dd.kanban.entity.UserBoard;
import com.dd.kanban.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserBoard user = userRepository.findByEmail(username);
		return UserPrincipal.create(user);
	}

	@Transactional
	public UserDetails loadUserById(Long id) {

		UserBoard user = userRepository.findById(id).orElseThrow(
				() -> new UsernameNotFoundException("User not found with id: " + id)
				);

		return UserPrincipal.create(user);

	}
}
