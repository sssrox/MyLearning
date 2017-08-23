package com.example.demo;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

import io.swagger.annotations.ApiModelProperty;

public class VO {
	@NotNull
	@ApiModelProperty(value="User's last name", required = true)
	private String name;
	@NumberFormat
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
