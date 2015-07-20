package com.oracle.lnsd.entity.hierarchy.joined;

import javax.persistence.Entity;

@Entity
public class Dog extends Pet {

	private boolean canWatchDoor;
	private Float height;

	public boolean isCanWatchDoor() {
		return canWatchDoor;
	}

	public void setCanWatchDoor(boolean canWatchDoor) {
		this.canWatchDoor = canWatchDoor;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	private static final long serialVersionUID = -3414732154732536797L;

}
