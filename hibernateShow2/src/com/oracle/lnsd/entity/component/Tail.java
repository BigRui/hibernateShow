package com.oracle.lnsd.entity.component;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class Tail implements Serializable {
	@Column(name="tail_name")
	private String name;
	private Integer length;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	
	private static final long serialVersionUID = 6096778282520059352L;
}
