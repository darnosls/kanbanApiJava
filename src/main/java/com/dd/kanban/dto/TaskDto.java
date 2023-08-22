package com.dd.kanban.dto;

import java.util.Date;

import jakarta.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data
public class TaskDto {

	@Getter
	private Long id;
	
	@Getter
	@Setter
	@NotNull(message = "title is a required field")
	@Length(min = 3,  max = 40, message = "title must be between 3 and 40 characters")
	private String title;
	
	@Getter
	@Setter
	private String description;
	
	private final Date date = new Date();
	
	@Getter
	private final Date createdAt = date;
	
	@Getter
	private final Date updatedAt = date;

}
