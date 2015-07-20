package com.oracle.lnsd.entity;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.lnsd.entity.component.Fish;
import com.oracle.lnsd.entity.component.Tail;
import com.oracle.lnsd.entity.hierarchy.singleTable.Animal;
import com.oracle.lnsd.entity.hierarchy.singleTable.Bird;
import com.oracle.lnsd.entity.hierarchy.singleTable.Rabbit;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/application-cfg.xml")
@Transactional	// 默认事务回滚
@TransactionConfiguration(defaultRollback=false)	//每个测试方法默认不回滚
public class EntityTest3 {
	
	@Resource
	private SessionFactory sessionFactroy;
	private Session session;

/*	@BeforeClass
	public static void beforeClass() {
		System.setProperty("log4jdbc.spylogdelegator.name", "net.sf.log4jdbc.log.slf4j.Slf4jSpyLogDelegator");
	}*/
	
	@Before
	//每个方法之前执行一次
	public void setUp() throws Exception {
		session = sessionFactroy.getCurrentSession();
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
	
	///演示orphonRemoval
	@Test
//	@Rollback(false)	// 设置不回滚
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
	
	/////////////////////////演示组件映射
	@Test
	public void test17() {
		this.session.createQuery("delete from Fish").executeUpdate();
		
		Fish fish = new Fish();
		fish.setName("鲤鱼");
		fish.setColor("red");

		Tail tail = new Tail();
		tail.setName("鲤鱼尾巴");
		tail.setLength(1);
		fish.setTail(tail);
		session.persist(fish);
	}

	@Test
	public void test18() {
		Fish fish = (Fish) session.createCriteria(Fish.class).setMaxResults(1)
				.uniqueResult();
		System.out.println(fish);
	}
	
	///////////////////////////演示继承映射
	//////////////////////////single-table策略
	@Test
	public void test19() {
		this.session.createQuery("delete from Animal").executeUpdate();
		
		Animal animal = new Animal();
		animal.setName("动物");
		animal.setWeight(10.5F);
		
		Bird bird = new Bird();
		bird.setName("鸟");
		bird.setWeight(1.5F);
		bird.setFlySpeed(80.5F);
		bird.setWingCount(2);
		
		Rabbit rabbit = new Rabbit();
		rabbit.setName("兔子");
		rabbit.setWeight(2.5F);
		rabbit.setRanSpeed(70.3F);
		rabbit.setLegCount(4);
		
		session.persist(animal);
		session.persist(bird);
		session.persist(rabbit);
	}
	@Test
	public void test20() throws Exception {
		List<Animal> animals = session.createCriteria(Animal.class).list();
		for (Animal animal : animals) {
			System.out.println(animal);
		}
	}
}
