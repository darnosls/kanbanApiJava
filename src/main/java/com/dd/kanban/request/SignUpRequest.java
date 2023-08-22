package com.dd.kanban.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
	@NotBlank
	@Size(min = 3, max =30)
	private String name;

	@NotBlank
	@Size(min = 3, max = 15)
	private String username;

	@NotBlank
	@Size(max = 100)
	private String email;

	@NotBlank
	@Size(min = 6, max = 20)
	private String password;
}
