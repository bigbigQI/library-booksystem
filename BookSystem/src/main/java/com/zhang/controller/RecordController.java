package com.zhang.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhang.common.JsonResult;
import com.zhang.entity.Book;
import com.zhang.entity.Record;
import com.zhang.entity.User;
import com.zhang.service.BookService;
import com.zhang.service.RecordService;
import com.zhang.service.UserService;
import com.zhang.util.Constants;
import com.zhang.util.SpringUtils;

@Controller
@RequestMapping("/record")
public class RecordController extends BaseController {
	@Resource
	private BookService bookService;
	@Resource
	private RecordService recordService;
	@Resource
	private UserService userService;
	// 借阅图书

	@RequestMapping("/getAllRecord")
	public String getAllRecord(@RequestParam(value = "page", required = false) String page, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		int pageSize = Constants.PAGE_SIZE;
		if (page == null || page == "") {
			page = "1";
		}
		User user = (User) request.getSession().getAttribute(Constants.FRONT_USER_SESSION);
		int userid = user.getId();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", (Integer.parseInt(page) - 1) * pageSize); // 起始记录
		map.put("size", pageSize);
		map.put("status", 0);
		map.put("userid", userid);

		List<Record> records = recordService.getAllRecord(map);
		int total = recordService.countNum(map);
		int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
		if (!records.isEmpty()) {
			model.addAttribute("records", records);
			model.addAttribute("total", total);
			model.addAttribute("page", page);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("totalPage", totalPage);
			return "recordBook/recordManage";// 借阅图书
		}
		return null;
	}

/*	// 图书位置
	@RequestMapping("/getBookLocation.do")
	public String getAllRecord1(@RequestParam(value = "page", required = false) String page, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		int pageSize = Constants.PAGE_SIZE;
		if (page == null || page == "") {
			page = "1";
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", (Integer.parseInt(page) - 1) * pageSize); // 起始记录
		map.put("size", pageSize);
		map.put("status", 0);

		List<Record> records = recordService.getAllRecord(map);
		int total = recordService.countNum(map);
		int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;

		if (records != null) {
			model.addAttribute("records", records);
			model.addAttribute("total", total);
			model.addAttribute("page", page);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("totalPage", totalPage);
			return "recordBook/recordPlaceManage";// 图书位置
		}
		return null;
	}
*/
	@RequestMapping("/toReturnBook.do")
	public String toReturnBook(@RequestParam(value = "page", required = false) String page, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		int pageSize = Constants.PAGE_SIZE;
		if (page == null || page == "") {
			page = "1";
		}
		User user = (User) request.getSession().getAttribute(Constants.FRONT_USER_SESSION);
		int userid = user.getId();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", (Integer.parseInt(page) - 1) * pageSize); // 起始记录
		map.put("size", pageSize);
		map.put("userid", userid);
		map.put("status", '0'); // 0未还

		List<Record> records = recordService.getAllRecord(map);
		int total = recordService.countNum(map);
		int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
		if (!records.isEmpty()) {
			model.addAttribute("records", records);
			model.addAttribute("total", total);
			model.addAttribute("page", page);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("totalPage", totalPage);
			return "recordBook/returnBook";
		}
		return "recordBook/returnBook";
	}

	// 确定还书
	@RequestMapping("/returnBook.do")
	public void returnBook(HttpServletRequest request, HttpServletResponse response, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));
		Record record = recordService.serchRecordByID(id);
		int bookID = record.getBookid();
		Book book = bookService.searchBookByID(bookID);
		JsonResult jsonResult = new JsonResult();
		try {
			book.setAmount(book.getAmount() + 1);
			bookService.updateBook(book);
			record.setStatus(1); // 已还
			recordService.updateRecord(record);
			User loginUser = (User) request.getSession().getAttribute(Constants.FRONT_USER_SESSION);
			int booksum = userService.searchUserByID(loginUser.getId()).getBooksum();
			int sum = booksum + 1;
			loginUser.setBooksum(sum);
			userService.updateUser(loginUser);
			jsonResult.setCode("100");
			jsonResult.setMsg("还书成功");
			SpringUtils.renderJson(response, jsonResult);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode("200");
			jsonResult.setMsg("还书失败！");
			SpringUtils.renderJson(response, jsonResult);
		}
	}

}
