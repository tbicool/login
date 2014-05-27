package com.st.stm.service.user;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.st.stm.entity.user.Dept;
import com.st.stm.entity.user.User;
import com.st.stm.repository.user.DeptDao;
import com.st.stm.repository.user.UserDao;
import com.st.stm.service.ShiroDbRealm;

/**
 * 部门管理类
 * 
 * @author tbicool
 * 
 */
// Spring Bean的标识.
@Component
// 默认将类中的所有public函数纳入事务管理.
@Transactional(readOnly = true)
public class DeptService {
	private static Logger logger = LoggerFactory.getLogger(DeptService.class);
	@Autowired
	private DeptDao deptDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ShiroDbRealm shiroRealm;

	/**
	 * 查询部门详细信息
	 * 
	 * @param id 部门主键
	 * @return 部门详细信息
	 */
	public Dept getDept(Long id) {
		return deptDao.findOne(id);
	}

	/**
	 * 保存部门信息
	 * 
	 * @param dept 部门信息
	 */
	@Transactional(readOnly = false)
	public void saveDept(Dept dept) {
		String authCodeString = dept.getAuthCodeString();
		String[] authArray = authCodeString.split(",");
		List<String> authList = new ArrayList();
		for (String element : authArray) {
			authList.add(element);
		}

		dept.setAuthList(authList);
		deptDao.save(dept);
		shiroRealm.clearAllCachedAuthorizationInfo();
	}

	/**
	 * 修改部门信息
	 * 
	 * @param dept 部门信息
	 */
	@Transactional(readOnly = false)
	public void updateDept(Dept dept) {
		String authCodeString = dept.getAuthCodeString();
		String[] authArray = authCodeString.split(",");
		List<String> authList = new ArrayList();
		for (String element : authArray) {
			authList.add(element);
		}
	}

	/**
	 * 删除部门信息
	 * 
	 * @param id 部门ID
	 */
	@Transactional(readOnly = false)
	public void deleteDept(Long id) {
		deptDao.delete(id);
		shiroRealm.clearAllCachedAuthorizationInfo();
	}

	/**
	 * 校验部门是否在使用中
	 * 
	 * @param id
	 */
	public boolean checkDelete(Long id) {
		List<User> list = userDao.findByDeptId(id);
		if ((list != null) && (list.size() > 0)) {
			return false;
		}
		return true;
	}

	/**
	 * 查询部门所有信息
	 * 
	 * @return
	 */
	public Page<Dept> getDeptAllByPage(Pageable pager) {
		return deptDao.findAll(pager);
	}

	public List<Dept> getDeptAll() {
		return (List<Dept>) deptDao.findAll();
	}

	/**
	 * 根据部门名称查询部门所有信息
	 * 
	 * @param deptName
	 * @return
	 */
	public Page<Dept> getUserAllByDeptName(String deptName, Pageable pager) {

		return deptDao.findDeptAllByDeptName("%" + deptName + "%", pager);
	}
}
