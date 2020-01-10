package com.dd.kanban.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dd.kanban.entity.Task;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	@GetMapping
	public ResponseEntity<List<String>> index() {
		
		List<String> list = new ArrayList<>();
		
		list.add("C");
		list.add("C++");
		list.add("task");
		
	
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
}
