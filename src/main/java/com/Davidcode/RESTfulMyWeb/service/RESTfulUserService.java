package com.Davidcode.RESTfulMyWeb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Davidcode.RESTfulMyWeb.model.dao.RESTfulUserDao;
import com.Davidcode.RESTfulMyWeb.model.entity.RESTfulUser;

@Service
public class RESTfulUserService {
	@Autowired
	RESTfulUserDao UserDao;  

	public Optional<RESTfulUser> getTodosByUserId(Integer id) {
		return UserDao.findById(id);
	}
}
