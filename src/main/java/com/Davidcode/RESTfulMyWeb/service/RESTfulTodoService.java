package com.Davidcode.RESTfulMyWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Davidcode.RESTfulMyWeb.model.dao.RESTfulTodoDao;
import com.Davidcode.RESTfulMyWeb.model.entity.RESTfulTodo;

import java.util.Optional;

@Service
public class RESTfulTodoService {
	
	@Autowired
	RESTfulTodoDao todoDao;

	public Iterable<RESTfulTodo> getTodos() {
		return todoDao.findAll();
	}

	public Integer createTodo(RESTfulTodo todo) {
		RESTfulTodo rltTodo = todoDao.save(todo);
		return rltTodo.getId();
	}

	public Boolean updateTodo(Integer id, RESTfulTodo todo) {
		Optional<RESTfulTodo> isExistTodo = findById(id);
		if (!isExistTodo.isPresent()) {
			return false;
		}
		RESTfulTodo newTodo = isExistTodo.get();
		if (todo.getStatus() == null) {
			return false;
		}
		newTodo.setStatus(todo.getStatus());
		newTodo.setUpdateTime(todo.getUpdateTime());
		todoDao.save(newTodo);
		return true;
	}

	public Optional<RESTfulTodo> findById(Integer id) {
		return todoDao.findById(id);
	}

	public Boolean deleteTodo(Integer id) {
		Optional<RESTfulTodo> findTodo = findById(id);
		if (!findTodo.isPresent()) {
			return false;
		}
		todoDao.deleteById(id);
		return true;
	}
}