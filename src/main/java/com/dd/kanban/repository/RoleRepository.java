package com.dd.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dd.kanban.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
