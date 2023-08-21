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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Component
@Table(name = "task")
@ToString
@NoArgsConstructor @AllArgsConstructor
public class Task implements Serializable {
	private static final long serialVersionUID = -1l;

	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Getter
	@Setter
	@Column(name = "title", nullable = false)
	private String title;

	@Getter
	@Setter
	@JsonSerialize(using = DateSerializer.class)
	@Column(name = "init_date", nullable = false)
	private Date createdAt;

	@Getter
	@Setter
	@JsonSerialize(using = DateSerializer.class)
	@Column(name = "end_date", nullable = true)
	private Date updatedAt;

	@Getter
	@Setter
	@Column(name = "description", nullable = true)
	private String description;

	@Getter
	@Setter
	@Column(name = "sponsor")
//	private User sponsor;
	private String sponsor;

	@Getter
	@Setter
	@Column(name = "step")
//	private User sponsor;
	private String step;
	
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//	public Date getInitDate() {
//		return initDate;
//	}
//
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//	public Date getEndDate() {
//		return endDate;
//	}

}
