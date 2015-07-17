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
	//ÿ������֮ǰִ��һ��
	public void setUp() throws Exception {
		session = sessionFactroy.openSession();
	}

	@Test
	/**
	 * ��ʾ�ӳټ�������
	 * @throws Exception
	 */
	public void test13() throws Exception {
		Transaction transaction = session.beginTransaction();
		KeCheng yuwen = (KeCheng) session.createCriteria(KeCheng.class).setMaxResults(1).uniqueResult();
//		Hibernate.initialize(yuwen.getXueShengList());
		transaction.commit();
		session.evict(yuwen); // session�رգ��ӳټ����쳣������취��Hibernate.initialize(yuwen.getXueShengList());
		for (XueSheng xs : yuwen.getXueShengList()) {
			System.out.println(xs.getName());
		}
		session.close();
	}
	
	/**
	 * ��ʾ�ӳټ�������
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
