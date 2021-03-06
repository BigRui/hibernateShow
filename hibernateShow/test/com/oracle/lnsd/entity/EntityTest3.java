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

	@BeforeClass //总共被执行一次，最先执行
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
		.setNamingStrategy(new ImprovedNamingStrategy()) //指定映射策略，节省注解
		.configure();
		sessionFactroy = config.buildSessionFactory();
	}

	@Before
	//每个方法之前执行一次
	public void setUp() throws Exception {
		session = sessionFactroy.getCurrentSession();
		session.beginTransaction();
	}

	@After
	//每个方法执行完之后执行一次
	public void tearDown() throws Exception {
		if(this.session.isOpen()) {
			this.session.getTransaction().commit(); //取得当前线程对应的Transaction对象
		}
	}

	@Test
	public void test() {
		NvShen ns = new NvShen();
		ns.setName("人家是女神");
		DiaoSi ds = new DiaoSi();
		ds.setNvShen(ns);
		ds.setName("俺是真心的");
		
		session.save(ns);
		session.save(ds);
	}

	@Test
	public void test2() throws Exception {
		Husband hb = new Husband();
		hb.setName("大丈夫");
		Wife wf = new Wife();
		wf.setName("妻子");
		wf.setHusband(hb);
		session.save(hb);
		session.save(wf);
	}
	
	@Test
	/**
	 * 试图把第二个妻子指向id为30这个丈夫，抛出异常。
	 * @throws Exception
	 */
	public void test3() throws Exception {
		Wife wf = new Wife();
		wf.setName("妻子3");
		//检索一个丈夫
//		Husband hb = (Husband) session.createCriteria(Husband.class).setFirstResult(1).setMaxResults(1).uniqueResult();跳过X行取Y行
		Husband hb = (Husband) session.createCriteria(Husband.class).setMaxResults(1).uniqueResult();
//		Husband hb = (Husband) session.get(Husband.class, 40);
		//嫁给他一个妻子
		wf.setHusband(hb);
		session.save(wf);
	}
	@Test
	public void test4() throws Exception {
//		Husband hb = (Husband) session.get(Husband.class, 30);
		Husband hb = (Husband) session.createCriteria(Husband.class).setMaxResults(1).uniqueResult();
		System.out.println(hb.getWife().getName());
	}
	
	///////////////////////开始多对一的关系
	@Test
	public void test5() throws Exception {
		Classes cls = new Classes();
		cls.setName("三年二班");
		
		Student stu = new Student();
		stu.setName("张三");
		stu.setClasses(cls);
		
		Student lisi = new Student();
		lisi.setName("李四");
		lisi.setClasses(cls);
		
		session.save(cls);
		session.save(stu);
		session.save(lisi);
	}
	@Test
	public void test6() throws Exception {
		Classes cls = new Classes();
		cls.setName("三年3班");
		
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
	
	///////////////////开始演示多对多
	@Test
	public void test10() throws Exception {
		KeCheng yuwen= new KeCheng();
		yuwen.setName("语文");
		KeCheng shuXue= new KeCheng();
		shuXue.setName("数学");
		
		XueSheng xs = new XueSheng();
		xs.setName("张三");
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
	 * 演示延迟加载问题
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
	 * 演示延迟加载问题
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
	///演示orphonRemoval
	@Test
	public void test15() {
		//清空三张表的数据
		this.session.createQuery("delete from IdCard").executeUpdate();
		this.session.createQuery("delete from Pen").executeUpdate();
		this.session.createQuery("delete from Person").executeUpdate();
		
		//插入数据
		Person person = new Person();
		person.setName("张三");
		
		IdCard card = new IdCard();
		card.setIdNum("asdfds5641321");
		card.setProvince("辽宁省");
		
		Pen pencil = new Pen();
		pencil.setName("铅笔");
		
		Pen pen = new Pen();
		pen.setName("钢笔");
		
		//如果是双向关联，为了减少错误出现的几率，两端都需要关联
		
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
		
		//解除person和idCard的关联
		idCard.setPerson(null);
		person.setIdCard(null);
		
		List<Pen> pens = person.getPens();
		
/*		for (Pen pen : pens) {
			pen.setPerson(null);
//			pens.remove(pen);   //在foreach循环当中不要删除元素。
		}
		pens.clear();*/
		
		//解除第一支笔和person的关系
		pens.remove(0).setPerson(null);
		session.persist(person);
	}
}
