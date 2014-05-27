package com.st.stm.web.user;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springside.modules.utils.Collections3;

import com.st.stm.entity.user.Dept;
import com.st.stm.service.user.DeptService;

/**
 * 用于转换用户表单中复杂对象DEPT的checkbox的关联。
 * 
 * @author tbicool
 * 
 */
@Component
public class DeptListEditor extends PropertyEditorSupport {

	@Autowired
	private DeptService deptService;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		String[] ids = StringUtils.split(text, ",");
		List<Dept> deptList = new ArrayList<Dept>();
		for (String id : ids) {
			Dept dept = deptService.getDept(Long.valueOf(id));
			deptList.add(dept);
		}
		setValue(deptList);
	}

	@Override
	public String getAsText() {
		return Collections3.extractToString((List) getValue(), "deptId", ",");
	}
}
