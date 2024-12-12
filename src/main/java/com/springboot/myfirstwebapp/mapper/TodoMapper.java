package com.springboot.myfirstwebapp.mapper;

import org.springframework.stereotype.Component;

import com.springboot.myfirstwebapp.dto.TodoDto;
import com.springboot.myfirstwebapp.entity.Todo;

@Component
public class TodoMapper {
	public TodoDto mapToDto(Todo todo) {
		return TodoDto.builder().id(todo.getId()).username(todo.getUsername()).description(todo.getDescription())
				.targetDate(todo.getTargetDate()).done(todo.isDone()).build();
	}

	public Todo mapToTodo(TodoDto todoDto) {
		return Todo.builder().id(todoDto.getId()).username(todoDto.getUsername()).description(todoDto.getDescription())
				.targetDate(todoDto.getTargetDate()).done(todoDto.isDone()).build();
	}
}
