package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	private final String svcType = "DemoService";
	@Qualifier(svcType)
	@Autowired
	private DemoInterface svc;
	
	@RequestMapping(value="/demo", method={RequestMethod.POST,RequestMethod.GET})
	public String getDemoValue(){
		System.out.println("entered in");
		return svc.dummyMethod()+" Hello World!!";
	}

}
