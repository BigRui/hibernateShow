package com.oracle.lnsd.entity.orphonRemoval;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.oracle.lnsd.entity.EntityId;
@Entity
public class IdCard extends EntityId {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6889373615595018719L;

	private String idNum;
	private String province;
	@ManyToOne
	@JoinColumn(name="person_id", unique=true)
	private Person person;
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
}
