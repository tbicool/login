package com.st.stm.repository.user;

import java.util.List;

/**
 * 查询所属用户菜单扩展DAO类
 * 
 * @author tbicool
 * 
 */
public interface AuthExpaDao {
	/**
	 * 根据登录名称查询用户所属的父节点菜单
	 * 
	 * @param loginName 登录名称
	 * @return
	 */
	List findListByLoginName(String loginName);

	List findMenuByLoginName(String loginName, String parentCode);

}
