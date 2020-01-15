package com.dd.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dd.kanban.entity.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
	Privilege findByName(String name);
}
