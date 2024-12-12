package com.springboot.myfirstwebapp.service;

import java.util.List;

import com.springboot.myfirstwebapp.dto.TodoDto;

public interface TodoService {
	List<TodoDto> getTodosByUsername(String username);

	TodoDto addTodo(TodoDto todoDto);

	void deleteTodoById(int id);

	TodoDto getTodoById(int id);

	TodoDto updateTodo(TodoDto todoDto);
}
