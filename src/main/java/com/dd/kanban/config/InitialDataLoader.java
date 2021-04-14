package com.dd.kanban.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dd.kanban.entity.Privilege;
import com.dd.kanban.entity.PrivilegeName;
import com.dd.kanban.entity.Role;
import com.dd.kanban.entity.RoleName;
import com.dd.kanban.entity.UserBoard;
import com.dd.kanban.repository.PrivilegeRepository;
import com.dd.kanban.repository.RoleRepository;
import com.dd.kanban.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {
	boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;


	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("======================================== InitialDataLoader: onApplicationEvent init ========================================");

		if (alreadySetup) {
			log.info("InitialDataLoader: alreadySetup exist - (exit)");
			return;
		}

		Privilege readPrivilege = createPrivilege(PrivilegeName.READ);
		Privilege writePrivilege = createPrivilege(PrivilegeName.WRITE);
		Privilege passwordPrivilege = createPrivilege(PrivilegeName.CHANGE_PASSWORD);

		List<Privilege> adminPrivileges = new ArrayList<Privilege>(Arrays.asList(readPrivilege, writePrivilege, passwordPrivilege));
		List<Privilege> userPrivileges = new ArrayList<Privilege>(Arrays.asList(readPrivilege, passwordPrivilege));
		Role adminRole = createRole(RoleName.ROLE_ADMIN, adminPrivileges);
		createRole(RoleName.ROLE_USER, userPrivileges);
		createUser("test@test.com", "test", "teste", "123456", new ArrayList<Role>(Arrays.asList(adminRole)));

		alreadySetup = true;
		log.info("======================================== InitialDataLoader: onApplicationEvent end ========================================");
	}

	@Transactional
	private Privilege createPrivilege(PrivilegeName name) {
		log.info("InitialDataLoader: createPrivilege init");
		Privilege privilege = privilegeRepository.findByName(name);
		if (privilege == null) {
			privilege = new Privilege(name);
			privilege = privilegeRepository.save(privilege);
		}
		log.info("InitialDataLoader: createPrivilege end");
		return privilege;
	}

	@Transactional
	private Role createRole(RoleName name, Collection<Privilege> privileges) {
		log.info("InitialDataLoader: createRole init");

		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role(name);
		}
		role.setPrivileges(privileges);
		role = roleRepository.save(role);
		log.info("InitialDataLoader: createPrivilege end");
		return role;
	}

	@Transactional
	private UserBoard createUser(String email, String name, String username, String password, Collection<Role> roles) {
		log.info("InitialDataLoader: createUser init");

		UserBoard user = userRepository.findByEmail(email);
		if (user == null) {
			user = new UserBoard();
			user.setName(name);
			user.setUsername(username);
			user.setPassword(passwordEncoder.encode(password));
			user.setEmail(email);
			user.setEnabled(true);
		}
		user.setRoles(roles);
		user = userRepository.save(user);
		log.info("InitialDataLoader: createUser end");
		return user;
	}
}
