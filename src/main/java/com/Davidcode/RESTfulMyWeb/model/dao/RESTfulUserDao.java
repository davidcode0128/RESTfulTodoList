package com.Davidcode.RESTfulMyWeb.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.Davidcode.RESTfulMyWeb.model.entity.RESTfulUser;

//第一個參數為訪問的實體，第二參數是這個Entity ID的資料型態
public interface RESTfulUserDao extends CrudRepository<RESTfulUser,Integer>{

}