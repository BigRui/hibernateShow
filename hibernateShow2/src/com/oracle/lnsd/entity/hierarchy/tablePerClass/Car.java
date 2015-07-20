package com.oracle.lnsd.entity.hierarchy.tablePerClass;

import javax.persistence.Entity;

@Entity
public class Car extends Vehicle {
	private Double price;
	private String color; 
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	private static final long serialVersionUID = 1621855145504433038L;
}
