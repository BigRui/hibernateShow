package com.oracle.lnsd.entity.hierarchy.singleTable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Rabbit")
public class Rabbit extends Animal {
	private Float ranSpeed;
	private Integer legCount;
	
	public Float getRanSpeed() {
		return ranSpeed;
	}

	public void setRanSpeed(Float ranSpeed) {
		this.ranSpeed = ranSpeed;
	}

	public Integer getLegCount() {
		return legCount;
	}

	public void setLegCount(Integer legCount) {
		this.legCount = legCount;
	}

	private static final long serialVersionUID = -168717447775772354L;
}
