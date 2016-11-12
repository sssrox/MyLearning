package com.example.controller;

import org.springframework.stereotype.Service;

@Service("DemoService2")
public class DemoService2 implements DemoInterface {

	@Override
	public String dummyMethod() {
		return "Hi From Svc 2";
	}

}
