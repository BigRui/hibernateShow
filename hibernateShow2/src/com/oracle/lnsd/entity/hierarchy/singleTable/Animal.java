package com.oracle.lnsd.entity.hierarchy.singleTable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.oracle.lnsd.entity.EntityId;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)// 单表策略是默认的，可以不写 
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING)// 自定义区分子类类型的字段名，以及字段类型
@DiscriminatorValue("Animal")
public class Animal extends EntityId {
	private String name;
	private Float weight;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	private static final long serialVersionUID = -7492444046131783591L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	
}
