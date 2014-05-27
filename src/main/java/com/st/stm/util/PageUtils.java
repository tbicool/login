package com.st.stm.util;

import java.util.List;

/**
 * 分页
 * 
 * @author tbicool；
 * 
 */
public class PageUtils<T> {
	// 当前页
	private Long curPage = 1L;
	// 列表行数
	private Integer size = 10;
	// 排序列
	private String orderCol;
	// 排序方向
	private String order;
	// 结果列表
	protected List<T> infoContent;
	// 总行数
	protected Long rowTotal;

	public PageUtils(Long curPage, Integer size) {
		this.curPage = curPage;
		this.size = size;
	}

	public Long getCurPage() {
		return curPage;
	}

	public void setCurPage(Long curPage) {
		this.curPage = curPage;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getOrderCol() {
		return orderCol;
	}

	public void setOrderCol(String orderCol) {
		this.orderCol = orderCol;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Long getRowTotal() {
		return rowTotal;
	}

	public void setRowTotal(Long rowTotal) {
		this.rowTotal = rowTotal;
	}

	public List<T> getInfoContent() {
		return infoContent;
	}

	public void setInfoContent(List<T> infoContent) {
		this.infoContent = infoContent;
	}
}
