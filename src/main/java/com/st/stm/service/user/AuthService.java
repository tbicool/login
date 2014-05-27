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

import com.st.stm.entity.user.Auth;
import com.st.stm.entity.user.Dept;
import com.st.stm.entity.user.Menu;
import com.st.stm.entity.user.Tree;
import com.st.stm.repository.user.AuthDao;
import com.st.stm.repository.user.AuthExpaDao;
import com.st.stm.repository.user.DeptDao;
import com.st.stm.service.ShiroDbRealm;

/**
 * 查询权限菜单
 * 
 * @author tbicool
 * 
 */
@Component
// 默认将类中的所有public函数纳入事务管理.
@Transactional(readOnly = true)
public class AuthService {
	private static Logger logger = LoggerFactory.getLogger(AuthService.class);

	@Autowired
	private AuthExpaDao authExpaDao;
	@Autowired
	private AuthDao authDao;
	@Autowired
	private DeptDao deptDao;
	@Autowired
	private ShiroDbRealm shiroRealm;

	/**
	 * 查询权限菜单信息
	 * 
	 * @param loginName
	 * @return
	 */
	public List<Menu> getAuth(String loginName) {
		List<Menu> menuList = new ArrayList<Menu>();
		List<Object[]> list = authExpaDao.findListByLoginName(loginName);
		if ((list != null) && (list.size() > 0)) {
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = list.get(i);
				if (obj != null) {
					String authCode = (String) obj[1];
					String authName = (String) obj[2];
					String authParentCode = (String) obj[4];
					if ((authParentCode == null) || "".equals(authParentCode)) {
						Menu menu = new Menu();
						menu.setModelName(authName);
						List<Auth> al = new ArrayList<Auth>();
						List<Object[]> authList = authExpaDao.findMenuByLoginName(loginName, authCode);
						if ((authList != null) && (authList.size() > 0)) {
							for (int j = 0; j < authList.size(); j++) {
								Object[] object = authList.get(j);
								if (object != null) {
									String aName = (String) object[2];
									String menuAddr = (String) object[5];
									Auth auth = new Auth();
									auth.setAuthName(aName);
									auth.setMenuAddr(menuAddr);
									al.add(auth);
								}
							}
						}
						menu.setList(al);
						menuList.add(menu);
					}

				}
			}
		}
		return menuList;
	}

	/**
	 * 查询权限表所有信息
	 * 
	 * @return
	 */
	public List<Tree> getAllAuth(Long deptId) {
		List<Auth> list = authDao.findAll();
		List<Tree> treeList = new ArrayList();
		Dept dept = new Dept();
		if (deptId != null) {
			dept = deptDao.findOne(deptId);
		}
		if ((list != null) && (list.size() > 0)) {
			for (Auth auth : list) {
				if (auth != null) {
					Tree tree = new Tree();
					tree.setId(auth.getAuthCode());
					tree.setName(auth.getAuthName());
					tree.setpId(auth.getAuthParentCode());
					if ("A".equals(auth.getAuthType())) {
						// 如果是菜单权限，则节点全部展开
						tree.setOpen(true);
					}
					if (dept != null) {
						List authList = dept.getAuthList();
						if ((authList != null) && (authList.size() > 0)) {
							for (int i = 0; i < authList.size(); i++) {
								String authCode = (String) authList.get(i);
								if (authCode.equals(auth.getAuthCode())) {
									tree.setChecked(true);
								}
							}
						}
					}
					treeList.add(tree);
				}
			}
		}
		return treeList;
	}

	/**
	 * 保存权限
	 * 
	 * @param auth
	 */
	@Transactional(readOnly = false)
	public void saveAuth(Auth auth) {
		if ((auth.getSortId() == null) || (auth.getSortId() == 0)) {
			auth.setSortId(getSortId());
		}
		authDao.save(auth);
		shiroRealm.clearAllCachedAuthorizationInfo();
	}

	private Long getSortId() {
		// TODO Auto-generated method stub
		List<Auth> list = authDao.findAllAuth();
		if ((list != null) && (list.size() > 0)) {
			return list.get(0).getSortId() + 1;
		}
		return 1L;
	}

	/**
	 * 校验编码是否已经存在
	 * 
	 * @param authCode
	 * @return
	 */
	public List checkAuth(String authCode) {
		List list = authDao.findAuthByAuthCode(authCode);
		return list;
	}

	/**
	 * 查询权限实例
	 * 
	 * @param authId
	 * @return
	 */
	public Auth getAuthById(Long authId) {

		return authDao.findOne(authId);
	}

	/**
	 * 无条件查询权限所有信息
	 * 
	 * @return
	 */
	public Page<Auth> getAuthAll(Pageable pager) {
		Page<Auth> page = authDao.findAll(pager);
		return page;
	}

	/**
	 * 根据权限名称查询
	 * 
	 * @param authName
	 * @return
	 */
	public Page<Auth> getAuthAllByName(String authName, Pageable pager) {

		return authDao.findListByAuthName("%" + authName + "%", pager);
	}

	/**
	 * 删除权限信息
	 * 
	 * @param authId
	 */
	@Transactional(readOnly = false)
	public void deleteAuth(Long authId) {

		authDao.delete(authId);
		shiroRealm.clearAllCachedAuthorizationInfo();
	}

	public Page<Auth> getAuthAllMan(Pageable pageable) {
		Page<Auth> page = authDao.findAllMan(pageable);
		return page;
	}

	public Page<Auth> getAuthAllManByName(String authName, Pageable pageable) {
		return authDao.findListManByAuthName("%" + authName + "%", pageable);
	}

	public Page<Auth> getAuthMenuByAuthCode(String authCode, Pageable pageable) {
		Page<Auth> page = authDao.findListMenuByAuthCode(authCode, pageable);
		return page;
	}

	public Page<Auth> getAuthMenuByAuthCodeAndName(String authCode, String authName, Pageable pageable) {
		return authDao.findListMenuByAuthCodeAndName(authCode, "%" + authName + "%", pageable);
	}

	public Auth getAuthByCode(String authCode) {
		// TODO Auto-generated method stub
		List list = authDao.findAuthByAuthCode(authCode);
		if ((list != null) && (list.size() > 0)) {
			return (Auth) list.get(0);
		}
		return null;
	}

	public Auth getMoveDownAuthForMan(Long sortId) {
		// TODO Auto-generated method stub
		List list = authDao.findMoveDownAuthForMan(sortId);
		if ((list != null) && (list.size() > 0)) {
			return (Auth) list.get(0);
		}
		return null;
	}

	public Auth getMoveDownAuthForMenu(String authParentCode, Long sortId) {
		List list = authDao.findMoveDownAuthForMenu(authParentCode, sortId);
		if ((list != null) && (list.size() > 0)) {
			return (Auth) list.get(0);
		}
		return null;
	}

	public Auth getMoveUpAuthForMan(Long sortId) {
		// TODO Auto-generated method stub
		List list = authDao.findMoveUpAuthForMan(sortId);
		if ((list != null) && (list.size() > 0)) {
			return (Auth) list.get(0);
		}
		return null;
	}

	public Auth getMoveUpAuthForMenu(String authParentCode, Long sortId) {
		// TODO Auto-generated method stub
		List list = authDao.findMoveUpAuthForMenu(authParentCode, sortId);
		if ((list != null) && (list.size() > 0)) {
			return (Auth) list.get(0);
		}
		return null;
	}

	/**
	 * 校验编码是否已经存在
	 * 
	 * @param authCode
	 * @return
	 */
	public List checnAuth(String authCode) {
		List list = authDao.findAuthByAuthCode(authCode);
		return list;
	}

	/**
	 * 检查权限编码是否在使用中
	 * 
	 * @param authId
	 * @return
	 */
	public boolean chechDelete(Long authId) {
		Auth auth = authDao.findOne(authId);
		List<Dept> list = deptDao.findDeptByAuthCode(auth.getAuthCode());
		if ((list != null) && (list.size() > 0)) {
			return false;
		}
		return true;
	}
}
