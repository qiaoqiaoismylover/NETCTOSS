package com.tarena.dao;

import java.util.List;

import com.tarena.annotation.MyBatisRepository;
import com.tarena.entity.Account;
import com.tarena.entity.Cost;
import com.tarena.entity.page.Page;

@MyBatisRepository
public interface CostDao {

	List<Cost> findAll();

	void save(Cost cost);

	Cost findById(int id);

	void update(Cost cost);
	
	void updateStatus(Cost cost);
	
	void delete(int id);
	
	List<Cost> findByPage(Page page);
	
	int findRows();

}
