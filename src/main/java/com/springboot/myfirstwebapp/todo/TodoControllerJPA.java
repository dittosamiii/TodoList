package com.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerJPA {

    private TodoRepository todoRepository;

    public TodoControllerJPA(TodoRepository todoRepository) {
        super();
        this.todoRepository = todoRepository;
    }

    @GetMapping("/list-todos")
    public String getTodos(ModelMap model) {
        String name = getLoggedInUser(model);
        List<Todo> todos = todoRepository.findByUsername(name);
        model.addAttribute("todos", todos);
        return "todos";
    }

    @GetMapping("/add-todo")
    public String showTodoPage(ModelMap model) {
        String name = getLoggedInUser(model);
        Todo todo = new Todo(0, name, "", LocalDate.now(), false);
        model.put("todo", todo);
        return "addTodo";
    }

    @PostMapping("/add-todo")
    public String addedNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "addTodo";
        }
        String name = getLoggedInUser(model);
        todo.setUsername(name);
        todoRepository.save(todo);
        return "redirect:list-todos";
    }

    @GetMapping("/delete-todo")
    public String deleteTodo(@RequestParam int id) {
        todoRepository.deleteById(id);
        return "redirect:list-todos";
    }

    @GetMapping("/update-todo")
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = todoRepository.findById(id).get();
        model.addAttribute("todo", todo);
        return "addTodo";
    }

    @PostMapping("/update-todo")
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "addTodo";
        }
        String name = getLoggedInUser(model);
        todo.setUsername(name);
        todoRepository.save(todo);
        return "redirect:list-todos";
    }

    private String getLoggedInUser(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}