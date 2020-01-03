package com.tarena.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tarena.dao.CostDao;
import com.tarena.entity.Cost;
import com.tarena.entity.page.CostPage;

public class TestCostDao {

	private String conf = "applicationContext.xml";

	@Test
	public void testFindAll() {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext(conf);
		CostDao dao = ctx.getBean(CostDao.class);
		List<Cost> list = dao.findAll();
		for (Cost c : list) {
			System.out.println(
				c.getCost_id() + " " +
				c.getName() + " " +
				c.getDescr()
			);
		}
	}
	
	@Test
	public void testSave() {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext(conf);
		CostDao dao = ctx.getBean(CostDao.class);
		Cost c = new Cost();
		c.setName("tarena");
		c.setBase_duration(90);
		c.setBase_cost(9.0);
		c.setUnit_cost(0.9);
		c.setDescr("tarena");
		c.setStatus("0");
		c.setCost_type("2");
		dao.save(c);
	}
	
	@Test
	public void testFindById() {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext(conf);
		CostDao dao = ctx.getBean(CostDao.class);
		Cost c = dao.findById(1);
		System.out.println(
			c.getCost_id() + " " +
			c.getName() + " " +
			c.getDescr()
		);
	}
	
	@Test
	public void testUpdate() {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext(conf);
		CostDao dao = ctx.getBean(CostDao.class);
		Cost c = dao.findById(460);
		c.setName("TARENA");
		c.setDescr("TARENA");
		dao.update(c);
	}
	
	@Test
	public void testDelete() {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext(conf);
		CostDao dao = ctx.getBean(CostDao.class);
		dao.delete(460);
	}
	
	@Test
	public void testFindByPage() {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext(conf);
		CostDao dao = ctx.getBean(CostDao.class);
		CostPage page = new CostPage();
		page.setCurrentPage(2);
		List<Cost> list = dao.findByPage(page);
		for (Cost c : list) {
			System.out.println(
				c.getCost_id() + " " +
				c.getName() + " " +
				c.getDescr()
			);
		}
	}
	
	@Test
	public void testFindRows() {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext(conf);
		CostDao dao = ctx.getBean(CostDao.class);
		int rows = dao.findRows();
		System.out.println(rows);
	}
	
}
