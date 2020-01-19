package com.dd.kanban.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.dd.kanban.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

public class TaskDto implements Serializable{

	private static final long serialVersionUID = -1L;


	private Long id;

	private String name;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date initDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date endDate;

	@Column(name = "description", nullable = false)
	private String description;

	private User sponsor;

	public TaskDto() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull(message = "name is a required field")
	@Length(min = 3,  max = 40, message = "name must be between 3 and 40 characters")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull(message = "initial date is a required field")
	public Date getInitDate() {
		return initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getSponsor() {
		return sponsor;
	}

	public void setSponsor(User sponsor) {
		this.sponsor = sponsor;
	}

	@Override
	public String toString() {
		return "TaskDto [id=" + id + ", name=" + name + ", initDate=" + initDate + ", endDate=" + endDate
				+ ", description=" + description + ", sponsor=" + sponsor + "]";
	}


}
