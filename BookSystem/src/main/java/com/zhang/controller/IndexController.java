package com.zhang.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {
	@RequestMapping("index")
	public String index(HttpServletRequest req, HttpServletResponse resp, Model model) {
		return "login";
	}
}
