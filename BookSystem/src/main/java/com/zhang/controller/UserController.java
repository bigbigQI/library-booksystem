package com.zhang.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhang.common.JsonResult;
import com.zhang.entity.User;
import com.zhang.service.UserService;
import com.zhang.util.Constants;
import com.zhang.util.SpringUtils;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	@Resource
	private UserService userService = null;

	@RequestMapping("/login")
	public String login(HttpServletRequest req, HttpServletResponse resp, Model model) {

		String name = req.getParameter("name");
		String pass = req.getParameter("pass");
		if (name == "" || pass == "") {
			return "login";
		}
		User user = new User();
		user.setName(name);
		user.setPass(pass);

		User existUser = userService.login(user);
		if (existUser == null) {
			req.setAttribute("msg", "该用户不存在，请检查");
			return "login";
		} else {
			HttpSession session = req.getSession();
			session.setAttribute(Constants.FRONT_USER_SESSION, existUser);
		}
		Properties props = System.getProperties();
		req.getSession().setAttribute("props", props);

		return "main/frameset";
	}

	public boolean validateCode(HttpServletRequest req, HttpServletResponse resp) {
		String validateExcept = (String) req.getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String kaptchaString = req.getParameter("kaptchafield");
		if (validateExcept == null || kaptchaString == null || !kaptchaString.equals(validateExcept)) {
			return false;
		} else {
			return true;
		}
	}
	@RequestMapping("/mainPage")
	public String mainPage(HttpServletRequest request, HttpServletResponse response) {
		
		return "main/mainPage";
	}
	
	@RequestMapping("/getUserManage")
	public String getUserManage(@RequestParam(value = "page", required = false) String page, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		try {
			String name = request.getParameter("name");
			int pageSize = Constants.PAGE_SIZE;
			if (page == null || page == "") {
				page = "1";
			}
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", (Integer.parseInt(page) - 1) * pageSize); // 起始记录
			map.put("size", pageSize);
			map.put("name", name);

			List<User> users = userService.getAllUser(map);
			users = (List<User>) removeSuperUser(users);
			int total = userService.countNum(map);
			int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;

			if (users != null) {
				model.addAttribute("users", users);
				model.addAttribute("name", name);
				model.addAttribute("total", total);
				model.addAttribute("page", page);
				model.addAttribute("pageSize", pageSize);
				model.addAttribute("totalPage", totalPage);
				return "user/usersDelete";
			}
		} catch (Exception e) {
			logger.info("获取所有的用户信息失败！");
			e.printStackTrace();
		}
		return "user/usersDelete";
	}

	@RequestMapping("/getUserSelect")
	public String getUserSelect(@RequestParam(value = "page", required = false) String page, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		try {
			String name = request.getParameter("name");
			int pageSize = Constants.PAGE_SIZE;
			if (page == null || page == "") {
				page = "1";
			}
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", (Integer.parseInt(page) - 1) * pageSize); // 起始记录
			map.put("size", pageSize);
			map.put("name", name);

			List<User> users = userService.getAllUser(map);
			users = (List<User>) removeSuperUser(users);
			int total = userService.countNum(map);
			int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
			if (users != null) {
				model.addAttribute("users", users);
				model.addAttribute("total", total);
				model.addAttribute("page", page);
				model.addAttribute("pageSize", pageSize);
				model.addAttribute("totalPage", totalPage);
				model.addAttribute("name", name);
				return "user/usersSelect";
			}
		} catch (Exception e) {
			logger.info("获取所有的用户信息失败！");
			e.printStackTrace();
		}
		return "user/usersSelect";
	}

	/**
	 * @描述 : 剔除超级管理员信息
	 * 
	 */
	@SuppressWarnings("unchecked")
	private Collection<User> removeSuperUser(Collection<?> collection) {
		if (collection == null) {
			return null;
		}
		Iterator<?> iterator = collection.iterator();
		while (iterator.hasNext()) {
			User o = (User) iterator.next();
			if (o.getName().equals("admin")) {
				iterator.remove();
			}
		}
		return (Collection<User>) collection;
	}

	@RequestMapping("deleteUserById")
	public void deleteUserByid(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			userService.lastDeleteUser(id);

			JsonResult jResult = new JsonResult();
			jResult.setCode("100");
			jResult.setMsg("删除用户信息成功！");
			SpringUtils.renderJson(response, jResult);

		} catch (Exception e) {
			JsonResult jResult = new JsonResult();
			jResult.setCode("200");
			jResult.setMsg("删除用户信息失败！");
			SpringUtils.renderJson(response, jResult);

			logger.info("根据ID获取用户信息失败");
			e.printStackTrace();
		}
	}

	@RequestMapping("/userAdd")
	public void userAdd(HttpServletRequest request, HttpServletResponse response, String username, String phone,
			String password, String idCard, String mail, String sex) {
		User user = new User();
		user.setName(username);
		user.setPass(password);
		user.setPhone(phone);
		user.setEmail(mail);
		user.setCode(idCard);
		user.setGrade(2);
		user.setBooksum(5);
		user.setSex(Integer.parseInt(sex));

		userService.addUser(user);

		
	}

	/**
	 * 获取登录用户的信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getUserMessage")
	public String getUserMessage(HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = (User) request.getSession().getAttribute(Constants.FRONT_USER_SESSION);
		model.addAttribute(user);
		return "user/usersChange";
	}

	/**
	 * 修改个人信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/userUpdate")
	public void userUpdate(HttpServletRequest request, HttpServletResponse response, String name, String grade,
			String phone, String idCard, String email) {
		User user = new User();
		user.setName(name);
		user.setGrade(Integer.parseInt(grade));
		user.setPhone(phone);
		user.setCode(idCard);
		user.setEmail(email);
		userService.updateUser(user);
	}
	/**
	 * 注销方法的实现
	 * @param request
	 * @param response
	 */
	
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		User user = (User)request.getSession().getAttribute(Constants.FRONT_USER_SESSION);
		if(user != null){
			request.getSession().invalidate();
		}
	}
}
