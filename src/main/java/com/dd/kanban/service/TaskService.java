package com.dd.kanban.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		Task task = new Task();

		task.setName(taskDto.getName());
		task.setInitDate(taskDto.getInitDate());
		task.setDescription(taskDto.getDescription());
		task.setSponsor(taskDto.getSponsor());

		return taskRepository.save(task);
	}

	public Task findTask(Long id) {
		Task task = taskRepository.getOne(id);

		return task;
	}
}
