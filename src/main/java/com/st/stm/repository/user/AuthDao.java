package com.st.stm.repository.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.st.stm.entity.user.Auth;

/**
 * 权限查询基本dao
 * 
 * @author tbicool
 * 
 */
public interface AuthDao extends JpaRepository<Auth, Long> {
	/**
	 * 根据父节点菜单信息查询所属的子节点信息
	 * 
	 * @param authCode 权限编码
	 * @return
	 */
	@Query("select a from Auth a where a.authCode = :authCode and a.authType = :authType")
	List findListByParentCode(@Param("authCode") String authCode, @Param("authType") String authType);

	@Query("select a from Auth a where a.authName like:authName")
	Page<Auth> findListByAuthName(@Param("authName") String authName, Pageable pager);

	@Query("select a from Auth a where a.authCode =:authCode")
	List findAuthByAuthCode(@Param("authCode") String authCode);

	@Query("select a from Auth a where a.authParentCode =:authParentCode")
	List findAuthByParnentCode(@Param("authParentCode") String authParentCode);

	@Query("select a from Auth a where a.authType='A' and a.authParentCode=''")
	Page<Auth> findAllMan(Pageable pageable);

	@Query("select a from Auth a where  a.authType='A' and a.authParentCode='' and a.authName like:authName")
	Page<Auth> findListManByAuthName(@Param("authName") String authName, Pageable pageable);

	@Query("select a from Auth a where a.authParentCode=:authCode ")
	Page<Auth> findListMenuByAuthCode(@Param("authCode") String authCode, Pageable pageable);

	@Query("select a from Auth a where  a.authParentCode=:authCode and a.authName like:authName ")
	Page<Auth> findListMenuByAuthCodeAndName(@Param("authCode") String authCode, @Param("authName") String authName,
			Pageable pageable);

	@Query("select a from Auth a where  a.authParentCode='' and a.sortId>:sortId order by a.sortId asc ")
	List findMoveDownAuthForMan(@Param("sortId") Long sortId);

	@Query("select a from Auth a where  a.authParentCode =:authParentCode and a.authType='A' and a.sortId>:sortId order by a.sortId asc ")
	List findMoveDownAuthForMenu(@Param("authParentCode") String authParentCode, @Param("sortId") Long sortId);

	@Query("select a from Auth a where  a.authParentCode='' and a.sortId<:sortId order by a.sortId desc ")
	List findMoveUpAuthForMan(@Param("sortId") Long sortId);

	@Query("select a from Auth a where  a.authParentCode =:authParentCode and a.authType='A' and a.sortId<:sortId order by a.sortId desc ")
	List findMoveUpAuthForMenu(@Param("authParentCode") String authParentCode, @Param("sortId") Long sortId);

	@Query("select a from Auth a order by a.sortId desc ")
	List findAllAuth();
}
