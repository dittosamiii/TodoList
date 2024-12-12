package com.springboot.myfirstwebapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.myfirstwebapp.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
	List<Todo> findByUsername(String username);
}
