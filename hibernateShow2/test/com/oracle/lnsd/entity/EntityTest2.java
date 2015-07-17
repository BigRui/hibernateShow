package com.oracle.lnsd.entity;

import static org.junit.Assert.*;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.classic.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oracle.lnsd.entity.Users;

public class EntityTest2 {
	private static SessionFactory sessionFactroy;
	private Transaction transaction;
	private Session session;

	@BeforeClass //总共被执行一次，最先执行
	public static void setUpBeforeClass() throws Exception {
		Configuration config = new Configuration()
		.addAnnotatedClass(Users.class)
		.setNamingStrategy(new ImprovedNamingStrategy()) //指定映射策略，节省注解
		.configure();
		sessionFactroy = config.buildSessionFactory();
	}

	@Before
	//每个方法之前执行一次
	public void setUp() throws Exception {
		session = sessionFactroy.getCurrentSession();
		transaction = session.beginTransaction();
	}

	@After
	//每个方法执行完之后执行一次
	public void tearDown() throws Exception {
		transaction.commit();
//		sessionFactroy.close();
	}
	@AfterClass
	public static void after() {
		System.out.println("最后执行一次");
	}

	@Test
	public void test() {
		//切面
		Users user = new Users();
		user.setName("zhangsan");
		user.setAge(123);
		user.setRealName("lisi"); //还是瞬时状态
		session.save(user);
		System.out.println("===========" + user.getId()); //持久化状态
		//执行完之后
		user.setAge(200);
	}
	
	@Test
	public void test2() throws Exception {
		Users user = (Users) session.get(Users.class, 1); //持久化状态
		user.setAge(1);
//		session.update(user);
	}

}
