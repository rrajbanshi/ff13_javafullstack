package com.labs.spring.core;

public class Item {
 private int id;
 private double price;
 private int quantity;
 private String cusine;
 private boolean veg;
 private String itemName;
 public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public String getCusine() {
	return cusine;
}
public void setCusine(String cusine) {
	this.cusine = cusine;
}
public boolean isVeg() {
	return veg;
}
public void setVeg(boolean veg) {
	this.veg = veg;
}
public String getItemName() {
	return itemName;
}
public void setItemName(String itemName) {
	this.itemName = itemName;
}
@Override
public String toString() {
	return "Item [id=" + id + ", price=" + price + ", quantity=" + quantity + ", cusine=" + cusine + ", veg=" + veg
			+ ", itemName=" + itemName + "]";
}

}

