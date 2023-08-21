package com.dd.kanban.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.dd.kanban.dto.TaskDto;
import com.dd.kanban.entity.Task;
import com.dd.kanban.repository.TaskRepository;


@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public List<Task> list() {
		return taskRepository.findAll();
	}

	public Task save(TaskDto taskDto) {
		
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Task task = new Task();

		task.setTitle(taskDto.getTitle());
		task.setCreatedAt(taskDto.getCreatedAt());
		task.setUpdatedAt(taskDto.getUpdatedAt());
		task.setStep("1");
		task.setDescription(taskDto.getDescription());
		task.setSponsor(currentUser.getName());

		return taskRepository.save(task);
	}

	public Task findTask(Long id) {
		Task task = taskRepository.getOne(id);

		return task;
	}
}
