package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	@RequestMapping(value="/demo", method={RequestMethod.POST,RequestMethod.GET})
	public String getDemoValue(){
		System.out.println("entered in");
		return "Hello World!!";
	}

}
