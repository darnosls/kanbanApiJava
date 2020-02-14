package com.dd.kanban.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.modelmapper.ModelMapper;

import com.dd.kanban.dto.TaskDto;
import com.dd.kanban.entity.Task;

public class TaskUT {
	private static final ModelMapper modelMapper = new ModelMapper();
	
	@Test
	public void checkTaskMapping() {
		TaskDto taskCreation = new TaskDto();
		
		taskCreation.setTitle("Teste titulo");
		taskCreation.setDescription("teste descrição");
		
		Task task = modelMapper.map(taskCreation, Task.class);
		
		assertEquals(taskCreation.getTitle(), task.getTitle());
		assertEquals(taskCreation.getDescription(), task.getDescription());
		assertEquals(taskCreation.getCreatedAt(), task.getCreatedAt());
		assertEquals(taskCreation.getUpdatedAt(), task.getUpdatedAt());
	}
}
