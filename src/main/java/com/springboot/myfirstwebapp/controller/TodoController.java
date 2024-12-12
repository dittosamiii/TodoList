package com.springboot.myfirstwebapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.myfirstwebapp.dto.TodoDto;
import com.springboot.myfirstwebapp.service.TodoService;

import jakarta.validation.Valid;

@Controller
public class TodoController {

	private final TodoService todoService;

	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping("/list-todos")
	public String getTodos(ModelMap model) {
		String name = getLoggedInUser();
		List<TodoDto> todos = todoService.getTodosByUsername(name);
		model.addAttribute("todos", todos);
		return "todos";
	}

	@GetMapping("/add-todo")
	public String showTodoPage(ModelMap model) {
		model.put("todo", new TodoDto());
		return "addTodo";
	}

	@PostMapping("/add-todo")
	public String addedNewTodo(@Valid @ModelAttribute("todo") TodoDto todoDto, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("todo", todoDto);
			return "addTodo";
		}
		String name = getLoggedInUser();
		todoDto.setUsername(name);
		if (!checkDateValid(todoDto)) {
			result.rejectValue("targetDate", null, "Invalid Target Date.");
			return "addTodo";
		}
		todoService.addTodo(todoDto);
		return "redirect:list-todos";
	}

	@GetMapping("/delete-todo")
	public String deleteTodo(@RequestParam int id) {
		todoService.deleteTodoById(id);
		return "redirect:list-todos";
	}

	@GetMapping("/update-todo")
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		TodoDto todoDto = todoService.getTodoById(id);
		model.addAttribute("todo", todoDto);
		return "addTodo";
	}

	@PostMapping("/update-todo")
	public String updateTodo(@Valid TodoDto todoDto, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("todo", todoDto);
			return "addTodo";
		}
		String name = getLoggedInUser();
		todoDto.setUsername(name);
		if (!checkDateValid(todoDto)) {
			result.rejectValue("targetDate", null, "Invalid Target Date.");
			return "addTodo";
		}
		todoService.updateTodo(todoDto);
		return "redirect:list-todos";
	}

	private String getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

	private boolean checkDateValid(TodoDto todoDto) {
		if (todoDto.getTargetDate().isBefore(LocalDate.now())) {
			return false;
		}
		return true;
	}
}
