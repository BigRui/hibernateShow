package com.oracle.lnsd.entity.hierarchy.tablePerClass;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.oracle.lnsd.entity.EntityId;

/**
 * 交通工具
 * 
 * @author baishuang
 * 
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class  Vehicle extends EntityId {
	private Float speed;
	private Integer capacity; // 载客数

	public Float getSpeed() {
		return speed;
	}

	public void setSpeed(Float speed) {
		this.speed = speed;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	private static final long serialVersionUID = -729336257354155701L;
}
