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
	 * OneToManyĬ����LAZY���أ��ڵ���students��ʱ������students
	 */
	@OneToMany(mappedBy="classes",fetch=FetchType.EAGER, cascade={CascadeType.PERSIST,CascadeType.REMOVE})  //��ϵ��ά��Ӧ�ý���student
//	@Cascade(value={CascadeType.DELETE}) //hibernate
//	@OneToMany
//	@JoinColumn(name="classes_id") //��ϵ��Լ��������һ�ˡ������ã�insert ÿ��student֮��Ҫupdate���
	private List<Student> students = new ArrayList<>(); //����Ҫ��ס������һ�Զ��ϵ������һ��Ҫ��ʼ����
	
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
