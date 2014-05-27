package com.st.stm.util;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 权限类型枚举
 * 
 */
public enum AuthType {
	AUTH_TYPE_O("A", "菜单权限"), AUTH_TYPE_T("B", "功能权限");

	public String typeValue;

	public String typeText;

	private static Map<String, AuthType> valueMap = Maps.newHashMap();

	static {
		for (AuthType type : AuthType.values()) {
			valueMap.put(type.typeValue, type);
		}
	}

	AuthType(String typeValue, String typeText) {
		this.typeValue = typeValue;
		this.typeText = typeText;
	}

	public static AuthType get(String typeValue) {

		return valueMap.get(typeValue);
	}

	public String getTypeValue() {

		return typeValue;
	}

	public String getTypeText() {
		return typeText;
	}

}
