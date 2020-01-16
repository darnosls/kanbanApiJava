package com.dd.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dd.kanban.entity.Role;
import com.dd.kanban.entity.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(RoleName name);
}
