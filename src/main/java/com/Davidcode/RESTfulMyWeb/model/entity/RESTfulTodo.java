package com.Davidcode.RESTfulMyWeb.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table
public class RESTfulTodo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	@Column
	String task = "";

	@Column(insertable = false, columnDefinition = "int default 1")
	Integer status = 1;

	@CreatedDate
	@Column(updatable = false, nullable = false)
	Date createTime = new Date();

	@LastModifiedDate
	@Column(nullable = false)
	Date updateTime = new Date();

}