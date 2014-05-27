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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.st.stm.entity.user.User;
import com.st.stm.service.user.DeptService;
import com.st.stm.service.user.UserService;
import com.st.stm.util.DataTablesParamUtility;
import com.st.stm.util.DataTablesReturnObject;

/**
 * 用户信息控制器
 * 
 * @author tbicool
 * 
 */
@Controller
@RequestMapping(value = "/user/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private DeptListEditor deptListEditor;
	@Autowired
	private DeptService deptService;

	@InitBinder
	public void initBinder(WebDataBinder b) {
		b.registerCustomEditor(List.class, "deptList", deptListEditor);
	}

	@RequiresPermissions("user:view")
	@RequestMapping(value = "")
	public String list(Model model) {

		return "user/userList";
	}

	@RequiresPermissions("user:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public DataTablesReturnObject query(Model model, User user, HttpServletRequest req) {
		Sort sort = new Sort(Direction.DESC, "userId");
		Pageable pageable = DataTablesParamUtility.buildPage(req, sort);
		String sEcho = req.getParameter("sEcho");
		String userName = user.getUserName();
		DataTablesReturnObject<User> dataTablesReturnObject = new DataTablesReturnObject();
		if ((userName == null) || "".equals(userName)) {
			Page<User> page = userService.getUserAll(pageable);
			dataTablesReturnObject = DataTablesParamUtility.toDataTablesReturnObject(sEcho, page);
		} else {
			Page<User> page = userService.getUserAllByUserName(userName, pageable);
			dataTablesReturnObject = DataTablesParamUtility.toDataTablesReturnObject(sEcho, page);
		}
		return dataTablesReturnObject;
	}

	@RequiresPermissions("user:edit")
	@RequestMapping(value = "create")
	public String createForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("allDepts", deptService.getDeptAll());
		model.addAttribute("actionId", "1");
		return "user/userForm";
	}

	@RequiresPermissions("user:edit")
	@RequestMapping(value = "save")
	public String save(User user, RedirectAttributes redirectAttributes) {
		userService.saveUser(user);
		redirectAttributes.addFlashAttribute("message", "创建用户" + user.getLoginName() + "成功");
		return "redirect:/user/user";
	}

	@RequiresPermissions("user:edit")
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		userService.deleteUser(id);
		redirectAttributes.addFlashAttribute("message", "删除用户成功");
		return "redirect:/user/user";
	}

	@RequiresPermissions("user:edit")
	@RequestMapping(value = "checkLoginName")
	@ResponseBody
	public String checkLoginName(@RequestParam("oldLoginName") String oldLoginName,
			@RequestParam("loginName") String loginName) {
		if (loginName.equals(oldLoginName)) {
			return "true";
		} else if (userService.findUserByLoginName(loginName) == null) {
			return "true";
		}
		return "false";
	}

	@RequiresPermissions("user:edit")
	@RequestMapping(value = "update/{id}")
	public String updateForm(@PathVariable("id") Long id, Model model) {
		User user = userService.getUser(id);
		model.addAttribute("user", user);
		model.addAttribute("allDepts", deptService.getDeptAll());
		model.addAttribute("actionId", "2");
		return "user/userForm";
	}

	@RequiresPermissions("user:edit")
	@RequestMapping(value = "update")
	public String update(User user, RedirectAttributes redirectAttributes) {
		userService.saveUser(user);
		redirectAttributes.addFlashAttribute("message", "修改用户" + user.getLoginName() + "成功");
		return "redirect:/user/user";
	}

}
