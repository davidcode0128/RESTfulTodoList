package com.Davidcode.RESTfulMyWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Davidcode.RESTfulMyWeb.model.entity.RESTfulTodo;
import com.Davidcode.RESTfulMyWeb.service.RESTfulTodoService;

@Controller
public class TodoController {
	
	@Autowired
	RESTfulTodoService todoService;// 取得Service物件
	
	@GetMapping("/todos")
	public String showTodos(Model model) {
			
		Iterable<RESTfulTodo> todoLists = todoService.getTodos();
		model.addAttribute("todolist", todoLists);
		RESTfulTodo todo = new RESTfulTodo();
		model.addAttribute("todoObject", todo);
		return "index";
	}

    @PostMapping("/todos")
    public String createTodo(@ModelAttribute RESTfulTodo todo, Model model) {
        Integer allTodoList = todoService.createTodo(todo);
        RESTfulTodo emptyTodo = new RESTfulTodo();
        model.addAttribute("todolist", allTodoList);
        model.addAttribute("todoObject", emptyTodo);
        return "redirect:/todos";
    }
	
	@ResponseBody
	@PutMapping("/todos/{id}")
	public void upadteTodo(@PathVariable Integer id, @RequestBody RESTfulTodo todo) {
		todoService.updateTodo(id, todo);
	}

	@ResponseBody
	@DeleteMapping("/todos/{id}")
	public void deleteTodo(@PathVariable Integer id) {
		todoService.deleteTodo(id);
	}
}
