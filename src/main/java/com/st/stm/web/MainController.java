package com.st.stm.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.st.stm.entity.user.Menu;
import com.st.stm.service.ShiroDbRealm.ShiroUser;
import com.st.stm.service.user.AuthService;
import com.st.stm.util.ConUtils;

/**
 * 主页controller
 * 
 * @author tbicool
 * 
 */
@Controller
@RequestMapping(value = "/main")
public class MainController {

	@Autowired
	private AuthService authService;

	@RequestMapping(value = "")
	public String tomain(Model model) {
		ShiroUser user = ConUtils.getUser();
		model.addAttribute("username", user.getLoginName());
		List<Menu> menu = authService.getAuth(user.getLoginName());
		model.addAttribute("menu", menu);
		return "main";
	}
}
