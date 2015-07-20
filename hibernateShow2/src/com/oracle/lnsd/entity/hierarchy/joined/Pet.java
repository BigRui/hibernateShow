package com.oracle.lnsd.entity.hierarchy.joined;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.oracle.lnsd.entity.EntityId;
@Entity
@Inheritance(strategy=InheritanceType.JOINED)	// 需要顯示指定繼承策略
public class Pet extends EntityId {
	private String name;
	private Float price;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	private static final long serialVersionUID = -2321177230880205845L;

}
