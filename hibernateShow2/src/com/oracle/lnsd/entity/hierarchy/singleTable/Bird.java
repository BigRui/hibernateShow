package com.oracle.lnsd.entity.hierarchy.singleTable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Bird")
public class Bird extends Animal {
	private Integer wingCount;
	private Float flySpeed;

	public Integer getWingCount() {
		return wingCount;
	}

	public void setWingCount(Integer wingCount) {
		this.wingCount = wingCount;
	}

	public Float getFlySpeed() {
		return flySpeed;
	}

	public void setFlySpeed(Float flySpeed) {
		this.flySpeed = flySpeed;
	}

	private static final long serialVersionUID = -6139199081105506992L;

}
