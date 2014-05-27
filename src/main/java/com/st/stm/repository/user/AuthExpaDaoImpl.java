package com.st.stm.repository.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

/**
 * 菜单查询实现类
 * 
 * @author tbicool
 * 
 */
@Component
public class AuthExpaDaoImpl implements AuthExpaDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List findListByLoginName(String loginName) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct a.* from auth_t a,user_t u,user_dept ud,dept_t d,dept_auth da ");
		sql.append("where u.user_id = ud.user_id and ud.dept_id= d.dept_id and d.dept_id = da.dept_id ");
		sql.append("and da.auth_code = a.auth_code and a.auth_Type = 'A'  and u.login_name = '");
		sql.append(loginName);
		sql.append("'");

		List result = em.createNativeQuery(sql.toString()).getResultList();

		return result;
	}

	@Override
	public List findMenuByLoginName(String loginName, String parentCode) {
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct a.* from auth_t a,user_t u,user_dept ud,dept_t d,dept_auth da ");
		sql.append("where u.user_id = ud.user_id and ud.dept_id= d.dept_id and d.dept_id = da.dept_id ");
		sql.append("and da.auth_code = a.auth_code and a.auth_Type = 'A'  and u.login_name = '");
		sql.append(loginName);
		sql.append("' and a.auth_parent_code = '");
		sql.append(parentCode);
		sql.append("'");
		List result = em.createNativeQuery(sql.toString()).getResultList();

		return result;
	}
}
