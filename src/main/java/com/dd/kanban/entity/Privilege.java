package com.dd.kanban.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "privilege")
public class Privilege implements Serializable{
	
	private static final long serialVersionUID = -1l;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
	@Column(name = "name", nullable = false)
    private String name;
 
	@Column(name = "roles", nullable = false)
    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}
