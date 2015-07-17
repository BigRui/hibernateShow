package com.oracle.lnsd.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.junit.Test;
import static org.junit.Assert.*;

public class EntityTest {

	@Test
	public void test() {
		Configuration config = new Configuration()
		.addAnnotatedClass(Users.class)
		.setNamingStrategy(new ImprovedNamingStrategy()) //指定映射策略，节省注解
		.configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Users user = new Users();
		user.setName("zhangsan");
		user.setAge(123);
		user.setRealName("lisi");
		session.save(user);
		transaction.commit();
		session.close(); //session通过openSession取得的时候一定要close
		
	}
	@Test
	public void test2() throws Exception {
		Configuration config = new Configuration()
		.addAnnotatedClass(Users.class)
		.setNamingStrategy(new ImprovedNamingStrategy()) //指定映射策略，节省注解
		.configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Session session2 = sessionFactory.openSession();
//		assertSame(session, session2);
		assertTrue(session == session2);
	}
	@Test
	public void test3() throws Exception {
		Configuration config = new Configuration()
		.addAnnotatedClass(Users.class)
		.setNamingStrategy(new ImprovedNamingStrategy()) //指定映射策略，节省注解
		.configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Session session2 = sessionFactory.getCurrentSession();
//		assertSame(session, session2);
		assertTrue(session == session2);
	}
	
}
