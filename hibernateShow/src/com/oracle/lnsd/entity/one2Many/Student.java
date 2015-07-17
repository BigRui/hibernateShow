package com.oracle.lnsd.entity.one2Many;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.oracle.lnsd.entity.EntityId;

@Entity
public class Student extends EntityId {

	/**
	 * 
	 */
	private static final long serialVersionUID = 901556467211666910L;
	private String name;
	
	@ManyToOne
	@JoinColumn(name="classes_id")
	private Classes classes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

}
