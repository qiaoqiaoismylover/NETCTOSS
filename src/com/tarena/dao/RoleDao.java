package com.tarena.dao;

import java.util.List;
import java.util.Map;

import com.tarena.annotation.MyBatisRepository;
import com.tarena.entity.Module;
import com.tarena.entity.Role;
import com.tarena.entity.page.Page;

@MyBatisRepository
public interface RoleDao {

	List<Role> findByPage(Page page);

	int findRows(Page page);
	
	List<Module> findAllModules();
	
	Role findById(int id);
	
	void saveRole(Role role);
	
	void saveRoleModules(Map<String,Object> roleModules);
	
	void updateRole(Role role);
	
	void deleteRoleModules(int roleId);
	
	void deleteRole(int roleId);
	
}
