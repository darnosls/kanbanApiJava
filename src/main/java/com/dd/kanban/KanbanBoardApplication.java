package com.dd.kanban;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration  
public class KanbanBoardApplication {
	public static void main(String[] args) {
		SpringApplication.run(KanbanBoardApplication.class, args);
	}
}
