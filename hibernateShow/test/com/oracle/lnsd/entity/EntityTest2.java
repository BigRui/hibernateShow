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

	@BeforeClass //�ܹ���ִ��һ�Σ�����ִ��
	public static void setUpBeforeClass() throws Exception {
		Configuration config = new Configuration()
		.addAnnotatedClass(Users.class)
		.setNamingStrategy(new ImprovedNamingStrategy()) //ָ��ӳ����ԣ���ʡע��
		.configure();
		sessionFactroy = config.buildSessionFactory();
	}

	@Before
	//ÿ������֮ǰִ��һ��
	public void setUp() throws Exception {
		session = sessionFactroy.getCurrentSession();
		transaction = session.beginTransaction();
	}

	@After
	//ÿ������ִ����֮��ִ��һ��
	public void tearDown() throws Exception {
		transaction.commit();
//		sessionFactroy.close();
	}
	@AfterClass
	public static void after() {
		System.out.println("���ִ��һ��");
	}

	@Test
	public void test() {
		//����
		Users user = new Users();
		user.setName("zhangsan");
		user.setAge(123);
		user.setRealName("lisi"); //����˲ʱ״̬
		session.save(user);
		System.out.println("===========" + user.getId()); //�־û�״̬
		//ִ����֮��
		user.setAge(200);
	}
	
	@Test
	public void test2() throws Exception {
		Users user = (Users) session.get(Users.class, 1); //�־û�״̬
		user.setAge(1);
//		session.update(user);
	}

}
