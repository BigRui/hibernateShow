package com.oracle.lnsd.entity.many2many;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.oracle.lnsd.entity.EntityId;
@Entity
public class XueSheng extends EntityId {

	/**
	 * 
	 */
	private static final long serialVersionUID = -663791347269805482L;
	private String name;
	
	@ManyToMany(cascade={CascadeType.PERSIST})
	@JoinTable(name="xue_sheng_ke_cheng", 
		joinColumns=@JoinColumn(name="xue_sheng_id"), 
		inverseJoinColumns=@JoinColumn(name="ke_cheng_id"))
	private List<KeCheng> kechengList = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<KeCheng> getKechengList() {
		return kechengList;
	}

	public void setKechengList(List<KeCheng> kechengList) {
		this.kechengList = kechengList;
	}
	
	
}
