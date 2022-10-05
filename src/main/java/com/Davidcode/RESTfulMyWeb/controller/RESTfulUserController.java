package com.Davidcode.RESTfulMyWeb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Davidcode.RESTfulMyWeb.model.entity.RESTfulUser;
import com.Davidcode.RESTfulMyWeb.service.RESTfulUserService;


@RestController
@RequestMapping("/api")
public class RESTfulUserController {
	@Autowired
	RESTfulUserService userService;
	
	@GetMapping("/users/{id}/todos")
	public ResponseEntity<Object> getTodosByUserId(@PathVariable Integer id) {
		Optional<RESTfulUser> todos = userService.getTodosByUserId(id);
		return ResponseEntity.status(HttpStatus.OK).body(todos);
	}
}
