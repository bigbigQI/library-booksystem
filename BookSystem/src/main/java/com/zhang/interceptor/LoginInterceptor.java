package com.zhang.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zhang.entity.User;
import com.zhang.util.Constants;


public class LoginInterceptor extends HandlerInterceptorAdapter{

	private static final String LOGIN_URL = "/index2.do";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String login = "";
		
		HttpSession session = request.getSession(true);
		// 从session 里面获取用户名的信息
		User user = (User)session.getAttribute(Constants.FRONT_USER_SESSION);
		// 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆
		if (user == null || "".equals(user.toString())) {
			login = "【提示信息】请登陆！！！！";
			request.setAttribute("log", login);
			request.getRequestDispatcher(LOGIN_URL).forward(request, response);
			//response.sendRedirect(request.getContextPath()+LOGIN_URL);
			return false;
		}
		return true;
	}
}
