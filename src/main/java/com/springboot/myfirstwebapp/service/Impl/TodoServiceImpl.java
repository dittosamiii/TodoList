package com.springboot.myfirstwebapp.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springboot.myfirstwebapp.dto.TodoDto;
import com.springboot.myfirstwebapp.entity.Todo;
import com.springboot.myfirstwebapp.mapper.TodoMapper;
import com.springboot.myfirstwebapp.repository.TodoRepository;
import com.springboot.myfirstwebapp.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {

	private final TodoRepository todoRepository;
	private final TodoMapper todoMapper;

	public TodoServiceImpl(TodoRepository todoRepository, TodoMapper todoMapper) {
		this.todoRepository = todoRepository;
		this.todoMapper = todoMapper;
	}

	@Override
	public List<TodoDto> getTodosByUsername(String username) {
		List<Todo> todos = todoRepository.findByUsername(username);
		return todos.stream().map(todoMapper::mapToDto).collect(Collectors.toList());
	}

	@Override
	public TodoDto addTodo(TodoDto todoDto) {
		Todo todo = todoMapper.mapToTodo(todoDto);
		todo = todoRepository.save(todo);
		return todoMapper.mapToDto(todo);
	}

	@Override
	public void deleteTodoById(int id) {
		todoRepository.deleteById(id);
	}

	@Override
	public TodoDto getTodoById(int id) {
		Todo todo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
		return todoMapper.mapToDto(todo);
	}

	@Override
	public TodoDto updateTodo(TodoDto todoDto) {
		Todo todo = todoMapper.mapToTodo(todoDto);
		todo = todoRepository.save(todo);
		return todoMapper.mapToDto(todo);
	}
}
