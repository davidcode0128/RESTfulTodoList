package com.Davidcode.RESTfulMyWeb;

import org.junit.jupiter.api.Test;

import com.Davidcode.RESTfulMyWeb.model.entity.RESTfulTodo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRESTfulTodoEntity {
    @Test
    public void whenGetId_ThenSetId() {
    	RESTfulTodo todo = new RESTfulTodo();
        todo.setId(1);
        Integer expected = 1;
        Integer actual = todo.getId();

        assertEquals(expected, actual);
    }

    @Test
    public void whenGetTask_ThenSetTask() {
    	RESTfulTodo todo = new RESTfulTodo();
        todo.setTask("洗衣服");
        String expected = "洗衣服";
        String actual = todo.getTask();

        assertEquals(expected, actual);
    }

    @Test
    public void whenSetTask_ThenGetTask() {
    	RESTfulTodo todo = new RESTfulTodo();
        todo.setStatus(2);
        Integer expected = 2;
        Integer actual = todo.getStatus();

        assertEquals(expected, actual);
    }
}