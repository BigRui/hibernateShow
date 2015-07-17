package com.oracle.lnsd.entity.orphonRemoval;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.oracle.lnsd.entity.EntityId;
@Entity
public class Pen extends EntityId {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6301102954965361422L;
	private String name;
	@ManyToOne
	@JoinColumn(name="person_id")
	private Person person;
	
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
