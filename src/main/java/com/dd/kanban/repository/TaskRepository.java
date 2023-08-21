package com.dd.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dd.kanban.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
	Task findByTitle(String title);
}
