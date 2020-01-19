package com.dd.kanban.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Table(name = "user")
public class User implements Serializable{
	private static final long serialVersionUID = -1l;

	@Id
	@Getter
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	@Column(name = "user_name")
	private String username;

	@Getter
	@Setter
	private String email;

	@Getter
	@Setter
	private String password;

	@Getter
	@Setter
	private boolean enabled;

	@Getter
	@Setter
	@Column(name = "token_expired")
	private boolean tokenExpired;

	@Setter
	@Getter
	@ManyToMany
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(
					name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
					name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;

}
