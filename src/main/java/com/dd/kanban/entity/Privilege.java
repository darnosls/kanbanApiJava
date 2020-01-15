package com.dd.kanban.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Component
@Table(name = "privilege")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Privilege implements Serializable{

	private static final long serialVersionUID = -1l;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
	@Getter
	@Setter
	@NonNull
    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
    
//    public Privilege() {
//    	
//    }
}
