package com.zhang.listener;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.zhang.util.Constants;

public class UsersListener implements HttpSessionListener, HttpSessionAttributeListener {

	private ServletContext sc;
	private int count = 0;

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		String name = arg0.getName();
		if (name.equalsIgnoreCase(Constants.FRONT_USER_SESSION)) {
			count++;
		}
		if (sc != null) {
			sc.setAttribute(Constants.SESSION_COUNT, count);
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		String name = arg0.getName();
		if (count > 0 && name.equalsIgnoreCase(Constants.FRONT_USER_SESSION)) {
			count--;
		}
		if (sc != null) {
			sc.setAttribute(Constants.SESSION_COUNT, count);
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		if (sc == null) {
			sc = event.getSession().getServletContext();
		}
		sc.setAttribute(Constants.SESSION_COUNT, count);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		Enumeration<String> names = event.getSession().getAttributeNames();
		int line = 0;
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			if (name.equalsIgnoreCase(Constants.FRONT_USER_SESSION)) {
				line++;
			}
		}
		if (line > 0) {
			count = line;
		}
		if (sc != null)
			sc.setAttribute(Constants.SESSION_COUNT, count);
	}

}
