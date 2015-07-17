package com.oracle.lnsd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
/**
 * —› æ@Column∫Õ@Transient
 * @author Administrator
 *
 */
@Entity
public class Users extends EntityId {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3577216542949167332L;
	private String name;
	@Column(name="user_age")
	private Integer age;
	private String realName;
	
	@Transient
	private String someName;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public String getSomeName() {
		return "Œ“Ω–£∫" + this.name;
	}

}
