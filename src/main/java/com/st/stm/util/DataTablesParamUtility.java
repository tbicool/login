package com.st.stm.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class DataTablesParamUtility {
	/**
	 * 基础分页
	 * 
	 * @param request
	 * @return
	 */
	public static Pageable buildPage(HttpServletRequest request, Sort sort) {
		int pagesize = Integer.parseInt(request.getParameter("iDisplayLength"));
		int pageno = Integer.parseInt(request.getParameter("iDisplayStart")) / pagesize;
		Pageable pager = new PageRequest(pageno, pagesize, sort);
		return pager;
	}

	/**
	 * 扩展分页
	 * 
	 * @param <T>
	 * @param sEcho
	 * @param page
	 * @return
	 */
	public static <T> PageUtils<T> buildExpPage(HttpServletRequest request) {
		int pagesize = Integer.parseInt(request.getParameter("iDisplayLength"));
		int pageno = Integer.parseInt(request.getParameter("iDisplayStart")) / pagesize;
		PageUtils pager = new PageUtils(new Long(pageno), pagesize);
		return pager;
	}

	/**
	 * 扩展分页内容封装
	 * 
	 * @param <T>
	 * @param sEcho
	 * @param page
	 * @return
	 */
	public static <T> DataTablesReturnObject<T> toExpDataTablesReturnObject(String sEcho, PageUtils<T> page) {
		long iTotalRecords = page.getRowTotal();
		long iTotalDisplayRecords = page.getRowTotal();
		DataTablesReturnObject<T> dataTableReturnObject = new DataTablesReturnObject<T>();
		dataTableReturnObject.setsEcho(sEcho);
		dataTableReturnObject.setiTotalDisplayRecords(iTotalDisplayRecords);
		dataTableReturnObject.setiTotalRecords(iTotalRecords);
		List<T> result = page.getInfoContent();
		dataTableReturnObject.setAaData(result);
		return dataTableReturnObject;
	}

	/**
	 * 基础分页内容封装
	 * 
	 * @param <T>
	 * @param sEcho
	 * @param page
	 * @return
	 */
	public static <T> DataTablesReturnObject<T> toDataTablesReturnObject(String sEcho, Page<T> page) {
		long iTotalRecords = page.getTotalElements(); // total number of records (unfiltered)
		long iTotalDisplayRecords = page.getTotalElements(); // value will be set when code filters companies by keyword
		DataTablesReturnObject<T> dataTableReturnObject = new DataTablesReturnObject<T>();
		dataTableReturnObject.setsEcho(sEcho);
		dataTableReturnObject.setiTotalDisplayRecords(iTotalDisplayRecords);
		dataTableReturnObject.setiTotalRecords(iTotalRecords);
		List<T> result = page.getContent();
		dataTableReturnObject.setAaData(result);
		return dataTableReturnObject;
	}
}
