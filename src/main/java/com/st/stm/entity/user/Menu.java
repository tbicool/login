package com.st.stm.entity.user;

import java.util.List;

/**
 * 菜单
 * 
 * @author tbicool
 * 
 */
public class Menu {

	private String modelName;

	private List<Auth> list;

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public List<Auth> getList() {
		return list;
	}

	public void setList(List<Auth> list) {
		this.list = list;
	}

}
