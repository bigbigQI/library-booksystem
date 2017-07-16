package com.zhang.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zhang.entity.User;
import com.zhang.util.Constants;

public class BaseController {
	/**
	 * 得到session中的user对象
	 * 
	 * @param req
	 * @param name
	 * @return
	 */
	public User loginUser(HttpServletRequest req, String name) {
		if (req == null) {
			req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		}
		User user = (User) req.getSession().getAttribute(name);
		return user;
	}

	/**
	 * 获取用户user对象
	 * 
	 * @param req
	 * @return
	 */
	public User loginFrontUser(HttpServletRequest req) {
		return loginUser(req, Constants.FRONT_USER_SESSION);
	}

	/**
	 * 将页面传递的参数封装成map
	 * 
	 * @param req
	 * @return
	 */
	public Map<String, String> getParameters(HttpServletRequest req) {
		if (req == null) {
			req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		}
		Map<String, String[]> reqMap = req.getParameterMap();
		Map<String, String> map = new HashMap<String, String>();
		if ((reqMap != null) && (!reqMap.isEmpty())) {
			Collection keys = reqMap.keySet();
			for (Iterator i = keys.iterator(); i.hasNext();) {
				String key = (String) i.next();
				Object value = reqMap.get(key);
				Object v = null;
				if ((value.getClass().isArray()) && (((Object[]) value).length > 0)) {
					v = ((Object[]) value)[0];
				} else {
					v = value;
				}
				if ((v != null) && (v instanceof String)) {
					String s = (String) v;
					if (s.length() > 0) {
						map.put(key, s);
					}
				}
			}
			// 读取cookie
			map.putAll(ReadCookieMap(req));
			return map;
		}
		return map;
	}

	public static Map<String, String> ReadCookieMap(HttpServletRequest req) {
		Map<String, String> cookieMap = new HashMap<String, String>();
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie.getValue());
			}
		}
		return cookieMap;
	}
}
