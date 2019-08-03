package com.learn.todo.application.todo.service;

import java.util.List;

import com.learn.todo.application.todo.model.Todo;

public interface TodoService {

	public List<Todo> fetchAllTodos(String name);
	
	public Todo deleteById(String username, long id);
}
