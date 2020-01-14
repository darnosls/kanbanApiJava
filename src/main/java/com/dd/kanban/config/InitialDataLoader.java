package com.dd.kanban.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.annotation.Transactional;

import com.dd.kanban.repository.UserRepository;

public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {
	boolean alreadySetup = false;
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		
	}
	

}
