package com.dd.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dd.kanban.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
