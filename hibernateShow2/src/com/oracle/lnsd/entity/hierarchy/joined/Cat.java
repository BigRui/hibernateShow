package com.oracle.lnsd.entity.hierarchy.joined;

import javax.persistence.Entity;

@Entity
public class Cat extends Pet {
	private boolean catchMouse;
	private String foodType;
	
	public boolean isCatchMouse() {
		return catchMouse;
	}

	public void setCatchMouse(boolean catchMouse) {
		this.catchMouse = catchMouse;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	private static final long serialVersionUID = -4679902757605509738L;
}
