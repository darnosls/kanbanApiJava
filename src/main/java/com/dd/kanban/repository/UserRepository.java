package com.dd.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dd.kanban.entity.UserBoard;

public interface UserRepository extends JpaRepository<UserBoard, Long> {
	UserBoard findByEmail(String email);
	Boolean existsByEmail(String email);
}
