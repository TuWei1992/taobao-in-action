package com.dream.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dream.demo.service.DemoService;


@Controller
@RequestMapping(value = "/demo")
public class DemoController {
	
	@Autowired
	private DemoService demoService;
	
	public DemoService getDemoService() {
		return demoService;
	}

	public void setDemoService(DemoService demoService) {
		this.demoService = demoService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String transaction() throws Exception {
		String result = "t";
		
		demoService.require();
		
		return result;
	}
}
