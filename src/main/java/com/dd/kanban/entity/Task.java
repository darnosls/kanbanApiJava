package com.dd.kanban.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

@Entity
@Component
@Table(name = "task")
public class Task implements Serializable {
	private static final long serialVersionUID = -1l;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@JsonSerialize(using = DateSerializer.class)
	@Column(name = "init_date", nullable = false)
	private Date initDate;
	
	@JsonSerialize(using = DateSerializer.class)
	@Column(name = "end_date", nullable = false)
	private Date endDate;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "sponsor", nullable = false)
	private User sponsor;
	
	public Task() {
		
	}

	public Task(Long id, String name, Date initDate, Date endDate, String description, User sponsor) {
		super();
		this.id = id;
		this.name = name;
		this.initDate = initDate;
		this.endDate = endDate;
		this.description = description;
		this.sponsor = sponsor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getInitDate() {
		return initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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
		return "Task [id=" + id + ", name=" + name + ", initDate=" + initDate + ", endDate=" + endDate
				+ ", description=" + description + ", sponsor=" + sponsor + "]";
	}
	
}
