package com.oracle.lnsd.entity.one2one;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class DiaoSi implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5824778984210834766L;
	//共享主键生成策略，自己的主键是通过nvShen的主键来确定了
	@Id
	@GeneratedValue(generator="fk")
	@GenericGenerator(name="fk", strategy="foreign", parameters={@Parameter(value="nvShen", name="property")})
	private Integer id;

	private String name;
	@OneToOne
	@PrimaryKeyJoinColumn
	private NvShen nvShen;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public NvShen getNvShen() {
		return nvShen;
	}

	public void setNvShen(NvShen nvShen) {
		this.nvShen = nvShen;
	}
	
	
}
