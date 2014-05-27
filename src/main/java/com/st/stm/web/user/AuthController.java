package com.st.stm.web.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.st.stm.entity.user.Auth;
import com.st.stm.service.user.AuthService;
import com.st.stm.util.AuthType;
import com.st.stm.util.DataTablesParamUtility;
import com.st.stm.util.DataTablesReturnObject;

/**
 * 权限信息控制器
 * 
 * @author tbicool
 * 
 */
@Controller
@RequestMapping(value = "/user/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@RequiresPermissions("auth:view")
	@RequestMapping(value = "")
	public String list(Model model) {
		// type 1 管理菜单界面 type 2 menu 菜单界面
		model.addAttribute("type", "1");
		model.addAttribute("authName", "");
		return "user/authList";
	}

	@RequiresPermissions("auth:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public DataTablesReturnObject<Auth> query(Model model, Auth auth, HttpServletRequest req) {
		Sort sort = new Sort(Direction.ASC, "sortId");
		Pageable pageable = DataTablesParamUtility.buildPage(req, sort);
		String sEcho = req.getParameter("sEcho");
		String authName = auth.getAuthName();
		DataTablesReturnObject<Auth> dataTablesReturnObject = new DataTablesReturnObject();
		if ((authName == null) || "".equals(authName)) {
			Page<Auth> page = authService.getAuthAllMan(pageable);
			dataTablesReturnObject = DataTablesParamUtility.toDataTablesReturnObject(sEcho, page);
		} else {
			Page<Auth> page = authService.getAuthAllManByName(authName, pageable);
			dataTablesReturnObject = DataTablesParamUtility.toDataTablesReturnObject(sEcho, page);
		}
		return dataTablesReturnObject;
	}

	@RequiresPermissions("auth:view")
	@RequestMapping(value = "list/{authCode}")
	public String list(Model model, @PathVariable("authCode") String authCode) {
		// type 1 管理菜单界面 type 2 menu 菜单界面 type 3 权限菜单界面
		model.addAttribute("authCode", authCode);
		Auth auth = authService.getAuthByCode(authCode);
		if (auth != null) {
			model.addAttribute("authName", auth.getAuthName());
		}
		model.addAttribute("type", "2");
		return "user/authList";
	}

	@RequiresPermissions("auth:view")
	@RequestMapping(value = "menuList")
	@ResponseBody
	public DataTablesReturnObject<Auth> queryMenuList(Model model, Auth auth, HttpServletRequest req) {
		Sort sort = new Sort(Direction.ASC, "sortId");
		Pageable pageable = DataTablesParamUtility.buildPage(req, sort);
		String sEcho = req.getParameter("sEcho");
		String authName = auth.getAuthName();
		String authCode = auth.getAuthCode();
		DataTablesReturnObject<Auth> dataTablesReturnObject = new DataTablesReturnObject();
		if ((authName == null) || "".equals(authName)) {
			Page<Auth> page = authService.getAuthMenuByAuthCode(authCode, pageable);
			dataTablesReturnObject = DataTablesParamUtility.toDataTablesReturnObject(sEcho, page);
		} else {
			Page<Auth> page = authService.getAuthMenuByAuthCodeAndName(authCode, authName, pageable);
			dataTablesReturnObject = DataTablesParamUtility.toDataTablesReturnObject(sEcho, page);
		}
		return dataTablesReturnObject;
	}

	@RequiresPermissions("auth:edit")
	@RequestMapping(value = "create")
	public String createForm(Model model) {
		model.addAttribute("auth", new Auth());
		model.addAttribute("allAuthType", AuthType.values());
		model.addAttribute("actionId", "1");
		return "user/authForm";
	}

	@RequiresPermissions("auth:edit")
	@RequestMapping(value = "create/{authCode}")
	public String createForm1(Model model, @PathVariable("authCode") String authCode) {
		Auth auth = new Auth();
		if (!StringUtils.isBlank(authCode)) {
			auth.setAuthParentCode(authCode);
		}
		model.addAttribute("auth", auth);
		model.addAttribute("allAuthType", AuthType.values());
		model.addAttribute("actionId", "1");
		return "user/authForm";
	}

	@RequiresPermissions("dept:edit")
	@RequestMapping("update/{id}")
	public String updateForm(@PathVariable("id") Long id, Model model) {
		Auth auth = authService.getAuthById(id);
		model.addAttribute("auth", auth);
		model.addAttribute("allAuthType", AuthType.values());
		model.addAttribute("actionId", "2");
		return "user/authForm";
	}

	@RequiresPermissions("auth:edit")
	@RequestMapping(value = "update")
	public String update(Auth auth, RedirectAttributes redirectAttributes) {
		authService.saveAuth(auth);
		redirectAttributes.addFlashAttribute("message", "修改权限成功");
		if (StringUtils.isBlank(auth.getAuthParentCode())) {
			return "redirect:/user/auth";
		} else {
			return "redirect:/user/auth/list/" + auth.getAuthParentCode();
		}

	}

	@RequiresPermissions("auth:edit")
	@RequestMapping(value = "save")
	public String save(Auth auth, RedirectAttributes redirectAttributes) {
		authService.saveAuth(auth);
		redirectAttributes.addFlashAttribute("message", "添加权限成功");
		if (StringUtils.isBlank(auth.getAuthParentCode())) {
			return "redirect:/user/auth";
		} else {
			return "redirect:/user/auth/list/" + auth.getAuthParentCode();
		}

	}

	@RequiresPermissions("auth:edit")
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		Auth auth = authService.getAuthById(id);
		if (!authService.chechDelete(id)) {
			redirectAttributes.addFlashAttribute("message", "权限正在使用，不能删除");
		} else {
			authService.deleteAuth(id);
			redirectAttributes.addFlashAttribute("message", "删除权限成功");
		}
		if (StringUtils.isBlank(auth.getAuthParentCode())) {
			return "redirect:/user/auth";
		} else {
			return "redirect:/user/auth/list/" + auth.getAuthParentCode();
		}

	}

	@RequiresPermissions("auth:edit")
	@RequestMapping(value = "checkCode")
	@ResponseBody
	public String checkCode(@RequestParam("authCode") String authCode) {
		List list = authService.checkAuth(authCode);
		if ((list == null) || (list.size() == 0)) {
			return "true";
		}
		return "false";
	}

	@RequiresPermissions("dept:edit")
	@RequestMapping("moveDown/{id}")
	public String moveDown(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
		Auth auth = authService.getAuthById(id);

		if (StringUtils.isBlank(auth.getAuthParentCode())) {
			// 管理菜单
			// 3,4,5,6,7
			// 为了求出比自己大的那一个sortID,和其交换位置
			Auth authTemp = authService.getMoveDownAuthForMan(auth.getSortId());
			if (authTemp == null) {
				redirectAttributes.addFlashAttribute("message", "已经是在最低端，不能向下移动");
			} else {
				Long sortId1 = auth.getSortId();
				Long sortId2 = authTemp.getSortId();
				auth.setSortId(sortId2);
				authTemp.setSortId(sortId1);
				authService.saveAuth(auth);
				authService.saveAuth(authTemp);
			}
		} else if (!StringUtils.isBlank(auth.getAuthParentCode()) && "A".equals(auth.getAuthType())) {
			// menu菜单
			Auth authTemp = authService.getMoveDownAuthForMenu(auth.getAuthParentCode(), auth.getSortId());
			if (authTemp == null) {
				redirectAttributes.addFlashAttribute("message", "已经是在最低端，不能向下移动");
			} else {
				Long sortId1 = auth.getSortId();
				Long sortId2 = authTemp.getSortId();
				auth.setSortId(sortId2);
				authTemp.setSortId(sortId1);
				authService.saveAuth(auth);
				authService.saveAuth(authTemp);
			}
		} else {
			// 功能菜单

		}
		// model.addAttribute("auth", auth);
		// model.addAttribute("allAuthType", AuthType.values());
		// model.addAttribute("actionId", "2");
		if (StringUtils.isBlank(auth.getAuthParentCode())) {
			return "redirect:/user/auth";
		} else {
			return "redirect:/user/auth/list/" + auth.getAuthParentCode();
		}
	}

	@RequiresPermissions("dept:edit")
	@RequestMapping("moveUp/{id}")
	public String moveUp(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
		Auth auth = authService.getAuthById(id);
		if (StringUtils.isBlank(auth.getAuthParentCode())) {
			// 管理菜单
			// 3,4,5,6,7
			// 为了求出比自己大的那一个sortID,和其交换位置
			Auth authTemp = authService.getMoveUpAuthForMan(auth.getSortId());
			if (authTemp == null) {
				redirectAttributes.addFlashAttribute("message", "已经是在最低端，不能向下移动");
			} else {
				Long sortId1 = auth.getSortId();
				Long sortId2 = authTemp.getSortId();
				auth.setSortId(sortId2);
				authTemp.setSortId(sortId1);
				authService.saveAuth(auth);
				authService.saveAuth(authTemp);
			}
		} else if (!StringUtils.isBlank(auth.getAuthParentCode()) && "A".equals(auth.getAuthType())) {
			// menu菜单
			Auth authTemp = authService.getMoveUpAuthForMenu(auth.getAuthParentCode(), auth.getSortId());
			if (authTemp == null) {
				redirectAttributes.addFlashAttribute("message", "已经是在最上端，不能向上移动");
			} else {
				Long sortId1 = auth.getSortId();
				Long sortId2 = authTemp.getSortId();
				auth.setSortId(sortId2);
				authTemp.setSortId(sortId1);
				authService.saveAuth(auth);
				authService.saveAuth(authTemp);
			}
		} else {
			// 功能菜单

		}
		// model.addAttribute("auth", auth);
		// model.addAttribute("allAuthType", AuthType.values());
		// model.addAttribute("actionId", "2");
		if (StringUtils.isBlank(auth.getAuthParentCode())) {
			return "redirect:/user/auth";
		} else {
			return "redirect:/user/auth/list/" + auth.getAuthParentCode();
		}
	}
}
