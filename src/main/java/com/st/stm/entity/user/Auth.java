package com.st.stm.entity.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 权限表
 * 
 * @author xushunxin
 * 
 */

@Entity
@Table(name = "AUTH_T")
public class Auth {
	/**
	 * 权限主键
	 */
	private Long authId;
	/**
	 * 权限编码
	 */
	private String authCode;
	/**
	 * 权限名称
	 */
	private String authName;
	/**
	 * 权限类型
	 */
	private String authType;
	/**
	 * 权限父编码
	 */
	private String authParentCode;
	/**
	 * 菜单地址
	 */
	private String menuAddr;
	/**
	 * 类型名称
	 */
	private String authTypeName;

	private Long sortId;

	public Auth() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getAuthId() {
		return authId;
	}

	public void setAuthId(Long authId) {
		this.authId = authId;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
		if ("A".equals(authType)) {
			setAuthTypeName("菜单权限");
		} else if ("B".equals(authType)) {
			setAuthTypeName("功能权限");
		}
	}

	public String getAuthParentCode() {
		return authParentCode;
	}

	public void setAuthParentCode(String authParentCode) {
		this.authParentCode = authParentCode;
	}

	public String getMenuAddr() {
		return menuAddr;
	}

	public void setMenuAddr(String menuAddr) {
		this.menuAddr = menuAddr;
	}

	public Long getSortId() {
		return sortId;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

	@Transient
	public String getAuthTypeName() {
		return authTypeName;
	}

	public void setAuthTypeName(String authTypeName) {
		this.authTypeName = authTypeName;
	}

}
