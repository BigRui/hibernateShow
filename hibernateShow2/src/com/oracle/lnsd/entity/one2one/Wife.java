package com.oracle.lnsd.entity.one2one;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.oracle.lnsd.entity.EntityId;
@Entity
public class Wife extends EntityId {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8814599181575605829L;

	private String name;
	
	@ManyToOne
	@JoinColumn(name="husband_id", unique=true)
	private Husband husband;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Husband getHusband() {
		return husband;
	}

	public void setHusband(Husband husband) {
		this.husband = husband;
	}
	
}
