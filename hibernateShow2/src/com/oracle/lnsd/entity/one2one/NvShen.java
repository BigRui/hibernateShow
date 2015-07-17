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
	 * 添加双向关联，但是这种情况你需要仔细考虑真的需要吗？
	 * mappedBy极重要，也就是维护关系的是对方，而对方记录了自己，自己在对方里定义的名字
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
