package com.oracle.lnsd.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@MappedSuperclass
public abstract class EntityId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7740147963363117942L;
	@Id
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pid") //使用序列的方式
//	@SequenceGenerator(name="pid", sequenceName="pkid")
	
//	@GeneratedValue(strategy=GenerationType.IDENTITY) //主键自动增长
	
//	@GeneratedValue(strategy=GenerationType.AUTO) 
	//如果使用的是mysql之类的，则自动使用IDENTITY，如果发现是Oracle,
	//默认使用序列，序列的名：hibernate_sequence
	
	@GeneratedValue(strategy=GenerationType.TABLE) 
	//使用一张表来维护id值，这个更通用
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, false);// 反射各个对象里的属性， 序列化要排除在外
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, false);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
