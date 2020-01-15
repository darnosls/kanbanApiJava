package com.dd.kanban.controller;

import java.security.Principal;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AuthenticationController {
	@RequestMapping("/api/user")
	public Principal user(Principal user) {
		
		return user;
	}
}
