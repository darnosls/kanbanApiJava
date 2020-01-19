package com.dd.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dd.kanban.entity.Privilege;
import com.dd.kanban.entity.PrivilegeName;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
	Privilege findByName(PrivilegeName name);
}
