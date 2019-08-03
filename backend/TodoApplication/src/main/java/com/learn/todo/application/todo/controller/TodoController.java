package com.learn.todo.application.todo.controller;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learn.todo.application.todo.model.Todo;
import com.learn.todo.application.todo.service.TodoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TodoController {

	
	@Resource
	private TodoService todoService;
	
	@GetMapping("/users/{username}/todos")
	public List<Todo> fetchAllTodo(@PathVariable(name = "username") String name) {
		return todoService.fetchAllTodos(name);
	}
	
	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {
		if (!Objects.isNull(todoService.deleteById(username, id))) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
