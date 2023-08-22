package com.dd.kanban.entity;

import java.io.Serializable;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import org.hibernate.annotations.NaturalId;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Component
@Table(name = "role")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Role implements Serializable{

	private static final long serialVersionUID = -1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Getter
	@Setter
	@NonNull
	@Enumerated(EnumType.STRING)
	@NaturalId
	@Column(length = 60)
	private RoleName name;

	@Getter
	@Setter
	@ManyToMany(mappedBy = "roles")
	private Collection<UserBoard> users;

	@Getter
	@Setter
	@Column(name = "privileges", nullable = false)
	@ManyToMany
	@JoinTable(
			name = "role_privileges", 
			joinColumns = @JoinColumn(
					name = "role_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
					name = "privilege_id", referencedColumnName = "id"))
	private Collection<Privilege> privileges;

}
