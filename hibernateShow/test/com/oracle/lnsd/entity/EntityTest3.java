package com.oracle.lnsd.entity;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.classic.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oracle.lnsd.entity.many2many.KeCheng;
import com.oracle.lnsd.entity.many2many.XueSheng;
import com.oracle.lnsd.entity.one2Many.Classes;
import com.oracle.lnsd.entity.one2Many.Student;
import com.oracle.lnsd.entity.one2one.DiaoSi;
import com.oracle.lnsd.entity.one2one.Husband;
import com.oracle.lnsd.entity.one2one.NvShen;
import com.oracle.lnsd.entity.one2one.Wife;
import com.oracle.lnsd.entity.orphonRemoval.IdCard;
import com.oracle.lnsd.entity.orphonRemoval.Pen;
import com.oracle.lnsd.entity.orphonRemoval.Person;

public class EntityTest3 {
	private static SessionFactory sessionFactroy;
	private Session session;

	@BeforeClass //�ܹ���ִ��һ�Σ�����ִ��
	public static void setUpBeforeClass() throws Exception {
		Configuration config = new Configuration()
		.addAnnotatedClass(Users.class)
		.addAnnotatedClass(NvShen.class)
		.addAnnotatedClass(DiaoSi.class)
		.addAnnotatedClass(Husband.class)
		.addAnnotatedClass(Wife.class)
		.addAnnotatedClass(DiaoSi.class)
		.addAnnotatedClass(Classes.class)
		.addAnnotatedClass(Student.class)
		.addAnnotatedClass(XueSheng.class)
		.addAnnotatedClass(KeCheng.class)
		.addAnnotatedClass(Person.class)
		.addAnnotatedClass(IdCard.class)
		.addAnnotatedClass(Pen.class)
		.setNamingStrategy(new ImprovedNamingStrategy()) //ָ��ӳ����ԣ���ʡע��
		.configure();
		sessionFactroy = config.buildSessionFactory();
	}

	@Before
	//ÿ������֮ǰִ��һ��
	public void setUp() throws Exception {
		session = sessionFactroy.getCurrentSession();
		session.beginTransaction();
		
	}

	@After
	//ÿ������ִ����֮��ִ��һ��
	public void tearDown() throws Exception {
		if(this.session.isOpen()) {
			this.session.getTransaction().commit(); //ȡ�õ�ǰ�̶߳�Ӧ��Transaction����
		}
	}

	@Test
	public void test() {
		NvShen ns = new NvShen();
		ns.setName("�˼���Ů��");
		DiaoSi ds = new DiaoSi();
		ds.setNvShen(ns);
		ds.setName("�������ĵ�");
		
		session.save(ns);
		session.save(ds);
	}

	@Test
	public void test2() throws Exception {
		Husband hb = new Husband();
		hb.setName("���ɷ�");
		Wife wf = new Wife();
		wf.setName("����");
		wf.setHusband(hb);
		session.save(hb);
		session.save(wf);
	}
	
	@Test
	/**
	 * ��ͼ�ѵڶ�������ָ��idΪ30����ɷ��׳��쳣��
	 * @throws Exception
	 */
	public void test3() throws Exception {
		Wife wf = new Wife();
		wf.setName("����3");
		//����һ���ɷ�
//		Husband hb = (Husband) session.createCriteria(Husband.class).setFirstResult(1).setMaxResults(1).uniqueResult();����X��ȡY��
		Husband hb = (Husband) session.createCriteria(Husband.class).setMaxResults(1).uniqueResult();
//		Husband hb = (Husband) session.get(Husband.class, 40);
		//�޸���һ������
		wf.setHusband(hb);
		session.save(wf);
	}
	@Test
	public void test4() throws Exception {
//		Husband hb = (Husband) session.get(Husband.class, 30);
		Husband hb = (Husband) session.createCriteria(Husband.class).setMaxResults(1).uniqueResult();
		System.out.println(hb.getWife().getName());
	}
	
	///////////////////////��ʼ���һ�Ĺ�ϵ
	@Test
	public void test5() throws Exception {
		Classes cls = new Classes();
		cls.setName("�������");
		
		Student stu = new Student();
		stu.setName("����");
		stu.setClasses(cls);
		
		Student lisi = new Student();
		lisi.setName("����");
		lisi.setClasses(cls);
		
		session.save(cls);
		session.save(stu);
		session.save(lisi);
	}
	@Test
	public void test6() throws Exception {
		Classes cls = new Classes();
		cls.setName("����3��");
		
		Student stu = new Student();
		stu.setName("wangwu");
		stu.setClasses(cls);
		
		Student lisi = new Student();
		lisi.setName("zhaoliu");
		lisi.setClasses(cls);
		
		
		cls.getStudents().add(stu);
		cls.getStudents().add(lisi);
		
		session.persist(cls);
	}
	@Test
	public void test7() throws Exception {
//		Classes cls = (Classes) session.get(Classes.class, 64);
		Classes cls = (Classes) session.createCriteria(Classes.class).setMaxResults(1).uniqueResult();
		session.delete(cls);
	}
	@Test
	public void test8() throws Exception {
		Classes cls = (Classes) session.createCriteria(Classes.class).setMaxResults(1).uniqueResult();
		System.out.println(cls);
	}
	@Test
	public void test9() throws Exception {
		Student stu = (Student) session.createCriteria(Student.class).setMaxResults(1).uniqueResult();
	}
	
	///////////////////��ʼ��ʾ��Զ�
	@Test
	public void test10() throws Exception {
		KeCheng yuwen= new KeCheng();
		yuwen.setName("����");
		KeCheng shuXue= new KeCheng();
		shuXue.setName("��ѧ");
		
		XueSheng xs = new XueSheng();
		xs.setName("����");
		xs.getKechengList().add(yuwen);
		xs.getKechengList().add(shuXue);
		
		session.persist(xs);
	}
	@Test
	public void test11() throws Exception {
//		KeCheng yuwen = (KeCheng) session.get(KeCheng.class, 75);
		KeCheng yuwen = (KeCheng) session.createCriteria(KeCheng.class).setMaxResults(1).uniqueResult();
		for (XueSheng xs : yuwen.getXueShengList()) {
			System.out.println(xs.getName());
		}
	}
	@Test
	public void test12() throws Exception {
//		XueSheng xs = (XueSheng) session.get(XueSheng.class, 67);
		XueSheng xs = (XueSheng) session.createCriteria(XueSheng.class).setMaxResults(1).uniqueResult();
		session.delete(xs);
	}
	
	@Test
	/**
	 * ��ʾ�ӳټ�������
	 * @throws Exception
	 */
	public void test13() throws Exception {
//		KeCheng yuwen = (KeCheng) session.get(KeCheng.class, 75);
		KeCheng yuwen = (KeCheng) session.createCriteria(KeCheng.class).setMaxResults(1).uniqueResult();
		Hibernate.initialize(yuwen.getXueShengList());
		this.session.getTransaction().commit();
		for (XueSheng xs : yuwen.getXueShengList()) {
			System.out.println(xs.getName());
		}
	}
	
	/**
	 * ��ʾ�ӳټ�������
	 */
	@Test
	public void test14() {
//		KeCheng yuwen = (KeCheng) session.load(KeCheng.class, 75);
		KeCheng yuwen = (KeCheng) session.createCriteria(KeCheng.class).setMaxResults(1).uniqueResult();
		Hibernate.initialize(yuwen.getXueShengList());
		this.session.getTransaction().commit();
		for (XueSheng xs : yuwen.getXueShengList()) {
			System.out.println(xs.getName());
		}
	}
	///��ʾorphonRemoval
	@Test
	public void test15() {
		//������ű������
		this.session.createQuery("delete from IdCard").executeUpdate();
		this.session.createQuery("delete from Pen").executeUpdate();
		this.session.createQuery("delete from Person").executeUpdate();
		
		//��������
		Person person = new Person();
		person.setName("����");
		
		IdCard card = new IdCard();
		card.setIdNum("asdfds5641321");
		card.setProvince("����ʡ");
		
		Pen pencil = new Pen();
		pencil.setName("Ǧ��");
		
		Pen pen = new Pen();
		pen.setName("�ֱ�");
		
		//�����˫�������Ϊ�˼��ٴ�����ֵļ��ʣ����˶���Ҫ����
		
		person.setIdCard(card);
		card.setPerson(person);
		
		pencil.setPerson(person);
		pen.setPerson(person);
		
		person.getPens().add(pencil);
		person.getPens().add(pen);
		
		this.session.persist(person);
	}
	@Test
	public void test16() {
//		Person person = (Person)session.get(Person.class, 80);
		Person person = (Person)session.createCriteria(Person.class).setMaxResults(1).uniqueResult();
		IdCard idCard = person.getIdCard();
		
		//���person��idCard�Ĺ���
		idCard.setPerson(null);
		person.setIdCard(null);
		
		List<Pen> pens = person.getPens();
		
/*		for (Pen pen : pens) {
			pen.setPerson(null);
//			pens.remove(pen);   //��foreachѭ�����в�Ҫɾ��Ԫ�ء�
		}
		pens.clear();*/
		
		//�����һ֧�ʺ�person�Ĺ�ϵ
		pens.remove(0).setPerson(null);
		session.persist(person);
	}
}
