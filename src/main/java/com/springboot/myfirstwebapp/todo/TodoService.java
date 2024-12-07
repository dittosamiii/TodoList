package com.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

//@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();
    private static int counter = 0;
    static {
        todos.add(new Todo(++counter, "admin", "learn python", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++counter, "admin", "learn c++", LocalDate.now().plusYears(2), false));
        todos.add(new Todo(++counter, "admin", "learn java", LocalDate.now().plusYears(3), false));
    }

    public List<Todo> findByUsername(String username) {
        Predicate<? super Todo> predicate = todos -> todos.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
        todos.add(new Todo(++counter, username, description, targetDate, done));
    }

    public void deleteById(int id) {
        Predicate<? super Todo> predicate = todos -> todos.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todos -> todos.getId() == id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void updateById(Todo todo) {
        // TODO Auto-generated method stub
        deleteById(todo.getId());
        todos.add(todo);
    }
}
