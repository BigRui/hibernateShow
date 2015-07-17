package com.oracle.lnsd.entity.one2one;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.oracle.lnsd.entity.EntityId;

@Entity
public class Husband extends EntityId {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3268612182216352039L;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@OneToOne(mappedBy="husband")
	private Wife wife;
	public Wife getWife() {
		return wife;
	}
	public void setWife(Wife wife) {
		this.wife = wife;
	}
	
}
