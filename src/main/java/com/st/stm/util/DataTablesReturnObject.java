package com.st.stm.util;

import java.util.List;

import com.google.common.collect.Lists;

public class DataTablesReturnObject<T> {
	private long iTotalRecords;
	private long iTotalDisplayRecords;
	private String sEcho;
	private List<T> aaData = Lists.newArrayList();

	public DataTablesReturnObject() {
	}

	public void setiTotalRecords(long iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public long getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalDisplayRecords(long iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public long getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public String getsEcho() {
		return sEcho;
	}

	public List<T> getAaData() {
		return aaData;
	}

	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}
}
