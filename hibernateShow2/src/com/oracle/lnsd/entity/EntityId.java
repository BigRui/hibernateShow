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
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pid") //ʹ�����еķ�ʽ
//	@SequenceGenerator(name="pid", sequenceName="pkid")
	
//	@GeneratedValue(strategy=GenerationType.IDENTITY) //�����Զ�����
	
//	@GeneratedValue(strategy=GenerationType.AUTO) 
	//���ʹ�õ���mysql֮��ģ����Զ�ʹ��IDENTITY�����������Oracle,
	//Ĭ��ʹ�����У����е�����hibernate_sequence
	
	@GeneratedValue(strategy=GenerationType.TABLE) 
	//ʹ��һ�ű���ά��idֵ�������ͨ��
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, false);// �����������������ԣ� ���л�Ҫ�ų�����
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
