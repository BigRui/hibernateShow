package com.oracle.lnsd.entity.one2Many;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.oracle.lnsd.entity.EntityId;



@Entity
public class Classes extends EntityId {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3194158201936091223L;
	private String name;
	
	/**
	 * OneToMany默认是LAZY加载，在调用students的时候会加载students
	 */
	@OneToMany(mappedBy="classes",fetch=FetchType.EAGER, cascade={CascadeType.PERSIST,CascadeType.REMOVE})  //关系的维护应该交给student
//	@Cascade(value={CascadeType.DELETE}) //hibernate
//	@OneToMany
//	@JoinColumn(name="classes_id") //关系的约束交给了一端。极不好：insert 每个student之后还要update外键
	private List<Student> students = new ArrayList<>(); //万万要记住，所有一对多关系，集合一定要初始化。
	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
