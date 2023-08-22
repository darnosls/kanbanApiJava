package com.dd.kanban.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

import jakarta.validation.Valid;

import com.dd.kanban.entity.UserBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dd.kanban.entity.Role;
import com.dd.kanban.entity.RoleName;
import com.dd.kanban.repository.RoleRepository;
import com.dd.kanban.repository.UserRepository;
import com.dd.kanban.request.LoginRequest;
import com.dd.kanban.request.SignUpRequest;
import com.dd.kanban.response.ApiResponse;
import com.dd.kanban.response.JwtAuthenticationResponse;
import com.dd.kanban.security.JwtTokenProvider;

@SpringBootApplication
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {


	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider tokenProvider;

	@Autowired
	AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository,
							 RoleRepository roleRepository, PasswordEncoder passwordEncode, JwtTokenProvider tokenProvider) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncode;
		this.tokenProvider = tokenProvider;
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getEmail(),
						loginRequest.getPassword()
						)
				);

		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwt = tokenProvider.generateToken(auth);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

		if(userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity( new ApiResponse(false, "Email is already taken!"), HttpStatus.BAD_REQUEST );
		}

		Role userRole = roleRepository.findByName(RoleName.ROLE_USER);
		//.orElseThrow(() -> new ApiException("User Role not set."));
		UserBoard user = new UserBoard();


		user.setName(signUpRequest.getName());
		user.setUsername(signUpRequest.getUsername());
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		user.setEmail(signUpRequest.getEmail());
		user.setEnabled(true);
		user.setRoles(new ArrayList<Role>(Arrays.asList(userRole)));

		UserBoard result = userRepository.save(user);

		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/api/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
	}
}
