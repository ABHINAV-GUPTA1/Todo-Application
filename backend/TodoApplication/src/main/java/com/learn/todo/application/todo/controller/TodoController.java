package com.learn.todo.application.todo.controller;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@GetMapping("/users/{username}/todos/{id}")
	public Todo fetchTodo(@PathVariable(name = "username") String name, @PathVariable(name="id")long id) {
		return todoService.fetchTodoById(name, id);
	}
	
	@PutMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo) {
		Todo todoUpdated = todoService.save(todo);
		return ResponseEntity.ok(todoUpdated);
	}
	
	@PostMapping("/users/{username}/todos")
	public ResponseEntity<Void> createTodo(@PathVariable String username, @RequestBody Todo todo) {
		todo.setUserName(username);
		Todo todoCreated = todoService.save(todo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(todoCreated.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {
		if (!Objects.isNull(todoService.deleteById(username, id))) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
