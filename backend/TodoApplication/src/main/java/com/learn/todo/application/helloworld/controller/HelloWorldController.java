package com.learn.todo.application.helloworld.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learn.todo.application.helloworld.model.HelloWorldBean;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HelloWorldController {

	@GetMapping("/helloworld")
	public HelloWorldBean helloWorldMessage() {
		return new HelloWorldBean("HelloWorld!");
	}

	@GetMapping("/helloworld/{message}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String message) {
		return new HelloWorldBean(String.format("Hello, %s", message));
	}
}
