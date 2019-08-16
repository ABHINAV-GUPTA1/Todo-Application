package com.learn.todo.application.todo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.learn.todo.application.todo.model.Todo;
import com.learn.todo.application.todo.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {

	private static final String LEARN_ANGULAR = "Learn Angular";
	private static final String DEF = "def";
	private static final String ABHINAV = "abhinav";
	private static List<Todo> todos = new ArrayList<>();
	private static long idCounter = 0;

	static {
		todos.add(new Todo(++idCounter, ABHINAV, "Learn Java", new Date(), Boolean.TRUE));
		todos.add(new Todo(++idCounter, ABHINAV, "Learn Spring", new Date(), Boolean.TRUE));
		todos.add(new Todo(++idCounter, ABHINAV, "Learn Hibernate", new Date(), Boolean.TRUE));
		todos.add(new Todo(++idCounter, ABHINAV, LEARN_ANGULAR, new Date(), Boolean.TRUE));
		todos.add(new Todo(++idCounter, DEF, LEARN_ANGULAR, new Date(), Boolean.TRUE));
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

	@Override
	public Todo fetchTodoById(String name, long id) {
		return findById(name, id);
	}

	
	/**
	 *Save the todo to db if id is 0 or -1 else update todo.
	 *
	 */
	@Override
	public Todo save(Todo todo) {
		if (todo.getId() == -1 || todo.getId() == 0) {
			todo.setId(++idCounter);
			todos.add(todo);
		} else {
			deleteById(todo.getUserName(), todo.getId());
			todos.add(todo);
		}
		return todo;
	}

}
