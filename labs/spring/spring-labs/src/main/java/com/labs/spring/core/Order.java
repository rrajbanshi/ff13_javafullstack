package com.labs.spring.core;
import com.labs.spring.core.Item;
import java.time.LocalDate;
import java.util.List;

public class Order {
	private int id;
	private String orderBy;
	private String status;
	private LocalDate orderDate= LocalDate.now();
	private List<Item> items;
	private double orderAmount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderBy=" + orderBy + ", status=" + status + ", orderDate=" + orderDate
				+ ", items=" + items + ", orderAmount=" + orderAmount + "]";
	}
	
	
	
	

}
