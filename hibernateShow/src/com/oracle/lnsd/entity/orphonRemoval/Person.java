package com.oracle.lnsd.entity.orphonRemoval;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.oracle.lnsd.entity.EntityId;
@Entity
public class Person extends EntityId {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8806132694396113604L;
	
	private String name;
	@OneToOne(mappedBy="person", cascade=CascadeType.PERSIST, orphanRemoval=true)
	private IdCard idCard;
	
	@OneToMany(mappedBy="person",cascade=CascadeType.PERSIST, orphanRemoval=true)
	private List<Pen> pens = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IdCard getIdCard() {
		return idCard;
	}

	public void setIdCard(IdCard idCard) {
		this.idCard = idCard;
	}

	public List<Pen> getPens() {
		return pens;
	}

	public void setPens(List<Pen> pens) {
		this.pens = pens;
	}
}
