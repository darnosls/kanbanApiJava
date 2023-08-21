package com.dd.kanban.util;

import javax.persistence.EntityManager;

import org.modelmapper.ModelMapper;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;

public class DTOModelMapper extends RequestResponseBodyMethodProcessor {
	private static final ModelMapper modelMaper = new ModelMapper();
	
	private EntityManager entityManager;

	public DTOModelMapper(ObjectMapper objectMapper, EntityManager entityManger) {
		super(Collections.singletonList(new MappingJackson2HttpMessageConverter(objectMapper)));
		this.entityManager = entityManager;
	}

}
