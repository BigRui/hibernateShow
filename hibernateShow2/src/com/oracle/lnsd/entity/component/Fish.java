package com.oracle.lnsd.entity.component;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import com.oracle.lnsd.entity.EntityId;
@Entity
public class Fish extends EntityId {
	
	private String name;
	private String color;
	@Embedded
//	@AttributeOverrides({
//		@AttributeOverride(name="name", column=@Column(name="tail_name"))
//	})
	private Tail tail;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Tail getTail() {
		return tail;
	}
	public void setTail(Tail tail) {
		this.tail = tail;
	}
	
	
	private static final long serialVersionUID = 4245407903076411489L;
}
