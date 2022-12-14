package com.Davidcode.RESTfulMyWeb;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.Davidcode.RESTfulMyWeb.model.entity.RESTfulTodo;
import com.Davidcode.RESTfulMyWeb.service.RESTfulTodoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TestRESTfulTodoController {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	RESTfulTodoService todoService;

	@Test
	public void testGetTodos() throws Exception {
		// 設定資料
		List<RESTfulTodo> expectedList = new ArrayList<RESTfulTodo>();
		RESTfulTodo todo = new RESTfulTodo();
		todo.setTask("洗衣服");
		todo.setId(1);
		expectedList.add(todo);

		// 模擬todoService.getTodos() 回傳 expectedList
		Mockito.when(todoService.getTodos()).thenReturn(expectedList);

		// 模擬呼叫[GET] /api/todos
		String returnString = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/todos")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString();

		Iterable<RESTfulTodo> actualList = objectMapper.readValue(returnString,
				new TypeReference<Iterable<RESTfulTodo>>() {
				});

		// 判定回傳的body是否跟預期的一樣
		assertEquals(expectedList, actualList);
	}

	@Test
	public void testrCreateTodo() throws Exception {
		// 設定資料
		RESTfulTodo mockTodo = new RESTfulTodo();
		mockTodo.setId(1);
		mockTodo.setTask("洗衣服");
		mockTodo.setStatus(1);

		JSONObject todoObject = new JSONObject();
		todoObject.put("id", 1);
		todoObject.put("task", "洗衣服");

		// 模擬todoService.createTodo(todo) 回傳 id 1
		Mockito.when(todoService.createTodo(mockTodo)).thenReturn(1);

		// 模擬呼叫[POST] /api/todos
		String actual = ((ResultActions) ((MockHttpServletRequestBuilder) mockMvc
						.perform(MockMvcRequestBuilders.post("/api/todos")))			
						.accept(MediaType.APPLICATION_JSON) // response 設定型別
						.contentType(MediaType.APPLICATION_JSON) // request 設定型別
						.content(String.valueOf(todoObject))) // body 內容
				.andExpect(status().isCreated()) // 預期回應的status code 為
													// 201(Created)
				.andReturn().getResponse().getContentAsString();
	}

	@Test
	public void testDeleteTodoSuccess() throws Exception {
		// 模擬todoService.deleteTodo(1) 成功回傳true
		Mockito.when(todoService.deleteTodo(1)).thenReturn(true);

		// 模擬呼叫[DELETE] /api/todos/{id}
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/todos/1")
				.accept(MediaType.APPLICATION_JSON) // response 設定型別
				.contentType(MediaType.APPLICATION_JSON)) // request 設定型別
				.andExpect(status().isNoContent()); // 預期回應的status code 應為
													// 204(No Content)
	}
	@Test
	public void testDeleteTodoIdNotExist() throws Exception {
		// 模擬delete id 不存在，所以todoService.deleteTodo(100)回傳false
		Mockito.when(todoService.deleteTodo(100)).thenReturn(false);

		// 模擬呼叫[DELETE] /api/todos/{id}
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/todos/100")
				.accept(MediaType.APPLICATION_JSON) // response 設定型別
				.contentType(MediaType.APPLICATION_JSON)) // request 設定型別
				.andExpect(status().isBadRequest()); // 預期回應的status code 為
														// 400(Bad Request)
	}
}