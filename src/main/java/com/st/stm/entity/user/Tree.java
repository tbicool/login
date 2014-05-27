package com.st.stm.entity.user;

/**
 * 菜单树JSON对象类
 * 
 * @author tbiool
 * 
 */
public class Tree {
	/**
	 * 节点ID
	 */
	private String id;
	/**
	 * 节点父ID
	 */
	private String pId;
	/**
	 * 节点显示名称
	 */
	private String name;
	/**
	 * 节点是否打开
	 */
	private boolean open;
	/**
	 * 菜单地址
	 */
	private String menuAddr;
	/**
	 * 是否选中
	 */
	private boolean checked;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getMenuAddr() {
		return menuAddr;
	}

	public void setMenuAddr(String menuAddr) {
		this.menuAddr = menuAddr;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
