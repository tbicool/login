package com.st.stm.entity.user;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.common.collect.Lists;

/**
 * 部门表
 * 
 * @author xushunxin
 * 
 */

@Entity
@Table(name = "dept_t")
public class Dept {
	private Long deptId;
	/**
	 * 部门名称
	 */
	private String deptName;

	/**
	 * 权限编码列表
	 */
	private List<String> authList = Lists.newArrayList();
	/**
	 * 权限编码串
	 */
	private String authCodeString;

	public Dept() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	@Column(nullable = false, length = 50)
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@ElementCollection
	@CollectionTable(name = "dept_auth", joinColumns = { @JoinColumn(name = "dept_id") })
	@Column(name = "auth_code")
	public List<String> getAuthList() {
		return authList;
	}

	public void setAuthList(List<String> authList) {
		this.authList = authList;
	}

	@Transient
	public String getAuthCodeString() {
		return authCodeString;
	}

	public void setAuthCodeString(String authCodeString) {
		this.authCodeString = authCodeString;
	}

}
