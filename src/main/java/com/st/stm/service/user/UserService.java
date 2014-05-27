package com.st.stm.service.user;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.st.stm.entity.user.User;
import com.st.stm.repository.user.UserDao;
import com.st.stm.service.ServiceException;

/**
 * 用户管理类
 * 
 * @author tbicool
 * 
 */
// Spring Bean的标识.
@Component
// 默认将类中的所有public函数纳入事务管理.
@Transactional(readOnly = true)
public class UserService {
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserDao userDao;

	/**
	 * 查询用户详细信息
	 * 
	 * @param id
	 * @return
	 */
	public User getUser(Long id) {
		return userDao.findOne(id);
	}

	/**
	 * 保存用户信息
	 * 
	 * @param user
	 */
	@Transactional(readOnly = false)
	public void saveUser(User user) {
		userDao.save(user);
	}

	/**
	 * 删除用户信息
	 * 
	 * @param id 用户信息ID
	 */
	@Transactional(readOnly = false)
	public void deleteUser(Long id) {
		User user = getUser(id);
		if ("admin".equals(user.getLoginName())) {
			logger.warn("操作员{}尝试删除超级管理员用户", SecurityUtils.getSubject().getPrincipal());
			throw new ServiceException("不能删除超级管理员用户");
		}
		userDao.delete(id);
	}

	/**
	 * 查询用户信息列表
	 * 
	 * @param
	 * @return
	 */
	public Page<User> getUserAll(Pageable pager) {
		Page<User> page = userDao.findAll(pager);
		return page;
	}

	/**
	 * 根据用户名查询
	 * 
	 * @param userName
	 * @return
	 */
	public Page<User> getUserAllByUserName(String userName, Pageable pager) {
		Page<User> page = userDao.findByUserName("%" + userName + "%", pager);
		return page;
	}

	/**
	 * 根据登录名称查询用户信息
	 * 
	 * @param loginName 登录名称
	 * @return
	 */
	public User findUserByLoginName(String loginName) {
		return userDao.findByLoginName(loginName);
	}
}
