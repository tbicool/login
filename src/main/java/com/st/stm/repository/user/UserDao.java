package com.st.stm.repository.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.st.stm.entity.user.User;

/**
 * 用户DAO类
 * 
 * @author tbicool
 * 
 */
public interface UserDao extends PagingAndSortingRepository<User, Long> {

	User findByLoginName(String loginName);

	@Query("select u from User u where u.userName like :userName")
	Page<User> findByUserName(@Param("userName") String userName, Pageable pager);

	@Query("select u from User u left join u.deptList g where g.deptId=:deptId")
	List<User> findByDeptId(@Param("deptId") Long deptId);

}
