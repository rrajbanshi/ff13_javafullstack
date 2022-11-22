package com.labs.spring.core;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMain {

	public static void main(String[] args) {
//		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("bean-config.xml");
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		System.out.println("no of beans " + ctx.getBeanDefinitionCount());
		String[] BeandefNames = ctx.getBeanDefinitionNames();
		for(String beanName :BeandefNames ) {
			System.out.println(beanName);
		}
		
		Order order = ctx.getBean("order",Order.class);
		System.out.println(order);
	}

}
