package com.st.stm.web.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.st.stm.entity.user.Dept;
import com.st.stm.entity.user.Tree;
import com.st.stm.service.user.AuthService;
import com.st.stm.service.user.DeptService;
import com.st.stm.util.DataTablesParamUtility;
import com.st.stm.util.DataTablesReturnObject;

/**
 * 部门信息控制器
 * 
 * @author tbicool
 * 
 */
@Controller
@RequestMapping(value = "/user/dept")
public class DeptController {

	@Autowired
	private DeptService deptService;
	@Autowired
	private AuthService authService;

	@RequiresPermissions("dept:view")
	@RequestMapping(value = "")
	public String list(Model model) {

		return "user/deptList";
	}

	@RequiresPermissions("dept:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public DataTablesReturnObject<Dept> query(Model model, Dept dept, HttpServletRequest req) {
		Sort sort = new Sort(Direction.DESC, "deptId");
		Pageable pageable = DataTablesParamUtility.buildPage(req, sort);
		String sEcho = req.getParameter("sEcho");
		String deptName = dept.getDeptName();
		DataTablesReturnObject<Dept> dataTablesReturnObject = new DataTablesReturnObject();
		if ((deptName == null) || "".equals(deptName)) {
			Page<Dept> page = deptService.getDeptAllByPage(pageable);
			dataTablesReturnObject = DataTablesParamUtility.toDataTablesReturnObject(sEcho, page);
		} else {
			Page<Dept> page = deptService.getUserAllByDeptName(deptName, pageable);
			dataTablesReturnObject = DataTablesParamUtility.toDataTablesReturnObject(sEcho, page);
		}
		return dataTablesReturnObject;
	}

	@RequiresPermissions("dept:edit")
	@RequestMapping(value = "create")
	public String createForm(Model model) {
		model.addAttribute("dept", new Dept());
		model.addAttribute("actionId", "1");
		return "user/deptForm";
	}

	@RequiresPermissions("dept:edit")
	@RequestMapping("update/{id}")
	public String updateForm(@PathVariable("id") Long id, Model model) {
		Dept dept = deptService.getDept(id);
		model.addAttribute("dept", dept);
		model.addAttribute("actionId", "2");
		return "user/deptForm";
	}

	@RequiresPermissions("dept:edit")
	@RequestMapping(value = "update")
	public String update(Dept dept, RedirectAttributes redirectAttributes) {
		deptService.saveDept(dept);
		redirectAttributes.addFlashAttribute("message", "修改角色成功");
		return "redirect:/user/dept";
	}

	@RequiresPermissions("dept:edit")
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if (!deptService.checkDelete(id)) {
			redirectAttributes.addFlashAttribute("message", "角色正在使用，不能删除");
		} else {
			deptService.deleteDept(id);
			redirectAttributes.addFlashAttribute("message", "删除角色成功");
		}

		return "redirect:/user/dept";
	}

	@RequestMapping(value = "tree/{id}")
	@ResponseBody
	public List<Tree> getZtree(@PathVariable("id") Long id) {
		List<Tree> list = authService.getAllAuth(id);
		return list;
	}

	@RequestMapping(value = "tree")
	@ResponseBody
	public List<Tree> getZtree() {
		List<Tree> list = authService.getAllAuth(null);
		return list;
	}

	@RequiresPermissions("dept:edit")
	@RequestMapping(value = "save")
	public String save(Dept dept, RedirectAttributes redirectAttributes) {
		deptService.saveDept(dept);
		redirectAttributes.addFlashAttribute("message", "添加角色成功");
		return "redirect:/user/dept";
	}
}
