package com.oracle.lnsd.entity;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oracle.lnsd.entity.many2many.KeCheng;
import com.oracle.lnsd.entity.many2many.XueSheng;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/application-cfg.xml")
public class FetchTest {
	
	@Resource
	private SessionFactory sessionFactroy;
	private Session session;

	@Before
	//每个方法之前执行一次
	public void setUp() throws Exception {
		session = sessionFactroy.openSession();
	}

	@Test
	/**
	 * 演示延迟加载问题
	 * @throws Exception
	 */
	public void test13() throws Exception {
		Transaction transaction = session.beginTransaction();
		KeCheng yuwen = (KeCheng) session.createCriteria(KeCheng.class).setMaxResults(1).uniqueResult();
//		Hibernate.initialize(yuwen.getXueShengList());
		transaction.commit();
		session.evict(yuwen); // session关闭，延迟加载异常。解决办法：Hibernate.initialize(yuwen.getXueShengList());
		for (XueSheng xs : yuwen.getXueShengList()) {
			System.out.println(xs.getName());
		}
		session.close();
	}
	
	/**
	 * 演示延迟加载问题
	 */
	@Test
	public void test14() {
		Transaction transaction = session.beginTransaction();
		KeCheng yuwen = (KeCheng) session.createCriteria(KeCheng.class).setMaxResults(1).uniqueResult();
		Hibernate.initialize(yuwen.getXueShengList());
		transaction.commit();
		session.close();
		for (XueSheng xs : yuwen.getXueShengList()) {
			System.out.println(xs.getName());
		}
	}
}
