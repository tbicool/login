package com.st.stm.repository.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.st.stm.entity.user.Dept;

/**
 * 部门DAO类
 * 
 * @author tbicool
 * 
 */
public interface DeptDao extends PagingAndSortingRepository<Dept, Long> {

	@Query("select d from Dept d where d.deptName like :deptName")
	Page<Dept> findDeptAllByDeptName(@Param("deptName") String deptName, Pageable pager);

	@Query("select d from Dept d left join d.authList a where a = :authCode")
	List<Dept> findDeptByAuthCode(@Param("authCode") String authCode);
}
