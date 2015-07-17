package com.oracle.lnsd.entity.many2many;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.oracle.lnsd.entity.EntityId;

@Entity
public class KeCheng extends EntityId {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7032632983633140296L;
	private String name;
	
	@ManyToMany(mappedBy="kechengList")
	private List<XueSheng> xueShengList = new ArrayList<>();
	
	
	public List<XueSheng> getXueShengList() {
		return xueShengList;
	}
	public void setXueShengList(List<XueSheng> xueShengList) {
		this.xueShengList = xueShengList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
