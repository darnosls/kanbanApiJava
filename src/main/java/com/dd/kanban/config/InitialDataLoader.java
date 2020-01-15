package com.dd.kanban.config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.annotation.Transactional;

import com.dd.kanban.entity.Privilege;
import com.dd.kanban.entity.Role;
import com.dd.kanban.entity.User;
import com.dd.kanban.repository.PrivilegeRepository;
import com.dd.kanban.repository.RoleRepository;
import com.dd.kanban.repository.UserRepository;

public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {
	boolean alreadySetup = false;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PrivilegeRepository privilegeRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
//	private PasswordEncoder passwordEncoder

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	@Transactional
	private Privilege createPrivilege(String name) {
		Privilege privilege = privilegeRepository.findByName(name);
		if (privilege == null) {
			privilege = new Privilege(name);
			privilege = privilegeRepository.save(privilege);
		}
		return privilege;
	}
	
	@Transactional
	private Role createRole(String name, Collection<Privilege> privileges) {
		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role(name);
		}
		role.setPrivileges(privileges);
		role = roleRepository.save(role);
		
		return role;
	}
	
	@Transactional
	private User createUser(String email, String firstName, String lastName, String password, Collection<Role> roles) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			user = new User();
			user.setFirstName(firstName);
			user.setLastName(lastName);
//			user.setPassword(passwordEncoder.encode(password));
		}
		
		return user;
	}
}
