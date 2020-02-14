package com.dd.kanban.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dd.kanban.dto.TaskDto;
import com.dd.kanban.entity.Task;
import com.dd.kanban.response.Response;
import com.dd.kanban.service.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping(path = "/list")
	@Secured(value = { "ROLE_ADMIN" })
	public ResponseEntity<List<Task>> list() {
		List<Task> tasks = taskService.list();
		return ResponseEntity.status(HttpStatus.OK).body(tasks);
	}

	@PostMapping(path = "/new")
	@Secured(value = { "ROLE_ADMIN" })
	public ResponseEntity<Response<Task>> create(@Valid @RequestBody TaskDto taskDto, BindingResult result) {
		Response<Task> response = new Response<Task>();

		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Task createTask = this.taskService.save(taskDto);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(taskDto.getId())
				.toUri();
		response.setData(createTask);
		return ResponseEntity.created(location).body(response);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Response<Task>> find(@PathVariable("id") Long id) {
		Task task = taskService.findTask(id);

		Response<Task> response = new Response<Task>();
		response.setData(task);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
