package com.oracle.lnsd.entity.one2one;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.oracle.lnsd.entity.EntityId;

@Entity
public class NvShen extends EntityId {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5529913285036913571L;
	
	private String name;

	/**
	 * ���˫����������������������Ҫ��ϸ���������Ҫ��
	 * mappedBy����Ҫ��Ҳ����ά����ϵ���ǶԷ������Է���¼���Լ����Լ��ڶԷ��ﶨ�������
	 */
	@OneToOne(mappedBy="nvShen")
	private DiaoSi diaoSi;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DiaoSi getDiaoSi() {
		return diaoSi;
	}

	public void setDiaoSi(DiaoSi diaoSi) {
		this.diaoSi = diaoSi;
	}
	
}
