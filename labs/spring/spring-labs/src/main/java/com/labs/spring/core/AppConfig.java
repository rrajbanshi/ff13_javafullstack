package com.labs.spring.core;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	
	@Bean
	public  Order order() {
		Order order = new Order();
		order.setId(100);
		order.setStatus("Confirmed");
		order.setOrderAmount(120);
		order.setOrderBy("pratap");
		order.setItems(Arrays.asList(samosa()));
		return order;
	}
	
	@Bean
	public Item samosa() {
		Item item = new Item();
		item.setId(1);
		item.setItemName("samosa");
		item.setQuantity(20);
		item.setPrice(200);
		item.setVeg(false);
		item.setCusine("northIndian");
		return item;
	}
}
