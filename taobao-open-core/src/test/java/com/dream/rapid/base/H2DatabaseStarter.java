package com.dream.rapid.base;

import org.springframework.context.support.ClassPathXmlApplicationContext;



public class H2DatabaseStarter   {
	
	
	public static void main(String[] args){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/tbo/taobao-h2.xml");
		context.start();
	}
}
