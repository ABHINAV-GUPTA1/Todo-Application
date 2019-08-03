package com.learn.todo.application.todo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.learn.todo.application.todo.model.Todo;
import com.learn.todo.application.todo.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {

	private static List<Todo> todos = new ArrayList<>();
	private static long idCounter = 0;

	static {
		todos.add(new Todo(++idCounter, "abhinav", "Learn Java", new Date(), Boolean.TRUE));
		todos.add(new Todo(++idCounter, "abhinav", "Learn Spring", new Date(), Boolean.TRUE));
		todos.add(new Todo(++idCounter, "abhinav", "Learn Hibernate", new Date(), Boolean.TRUE));
		todos.add(new Todo(++idCounter, "abhinav", "Learn Angular", new Date(), Boolean.TRUE));
		todos.add(new Todo(++idCounter, "def", "Learn Angular", new Date(), Boolean.TRUE));
	}

	@Override
	public List<Todo> fetchAllTodos(String name) {

		return todos.stream().filter(a -> a.getUserName().equals(name)).collect(Collectors.toList());
	}

	@Override
	public Todo deleteById(String username, long id) {
		Todo todo = findById(username, id);

		if (Objects.isNull(todo)) {
			return null;
		}

		if (todos.remove(todo)) {
			return todo;
		}
		return null;
	}

	private Todo findById(String username, long id) {
		return todos.stream().filter(a -> a.getId() == id && a.getUserName().equals(username)).findFirst().orElse(null);
	}

}
