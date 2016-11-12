package com.example.controller;

import org.springframework.stereotype.Service;

@Service("DemoService")
public class DemoService implements DemoInterface {
	
	@Override
	public String dummyMethod() {
		return "Hi";
	}
}
