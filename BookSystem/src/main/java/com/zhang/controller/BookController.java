package com.zhang.controller;

import java.util.Date;
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
import com.zhang.util.DateUtils;
import com.zhang.util.PageUtil;
import com.zhang.util.SpringUtils;

@Controller
@RequestMapping("/book")
public class BookController extends BaseController {

	@Resource
	private BookService bookService;
	@Resource
	private RecordService RecordService;
	@Resource
	private UserService userService;

	/**
	 * 查看所有图书信息
	 * 
	 * @param page
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/getAllBook")
	public String getAllBook(@RequestParam(value = "page", required = false) String page, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String bookName = request.getParameter("bookName");
		String pressName = request.getParameter("pressName");
		String autor = request.getParameter("autor"); // 从页面获取数据
		int pageSize = Constants.PAGE_SIZE;
		if (page == null || page == "") {
			page = "1";
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", (Integer.parseInt(page) - 1) * pageSize); // 起始记录
		map.put("size", pageSize); // 把值存入map
		map.put("bookName", bookName);
		map.put("pressName", pressName);
		map.put("autor", autor);

		List<Book> books = bookService.getAllBook(map);// 获取所有图书信息 存入books
		int total = bookService.countNum(map); // 总记录数
		int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
		if (books != null) {
			model.addAttribute("books", books);
			model.addAttribute("total", total);
			model.addAttribute("page", page);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("totalPage", totalPage);
			return "book/authorManage";
		}
		return null;
	}

	/**
	 * 通过书名搜索图书
	 * 
	 * @param page
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/getSearchBook")
	public String getSearchBook(@RequestParam(value = "page", required = false) String page, String bookName,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		int pageSize = Constants.PAGE_SIZE;
		if (page == null || page == "") {
			page = "1";
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", (Integer.parseInt(page) - 1) * pageSize); // 起始记录
		map.put("size", pageSize); // 把值存入map
		map.put("bookName", bookName);

		List<Book> books = bookService.getAllBook(map);// 获取所有图书信息 存入books
		int total = bookService.countNum(map); // 总记录数
		// 分页
		String pageCode = PageUtil.getPagation(request.getContextPath() + "/book/getAllBook.do", total,
				Integer.parseInt(page), pageSize);
		if (books != null) {
			model.addAttribute("books", books);
			model.addAttribute("pageCode", pageCode);
			model.addAttribute("bookName", bookName);
			return "book/authorManage";
		}
		return null;
	}

	@RequestMapping("/borrowBook")
	public void borrowBook(HttpServletRequest request, HttpServletResponse response, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));
		Book book = bookService.searchBookByID(id);
		Record record = new Record(); // 创建对象
		JsonResult jsonResult = new JsonResult();
		User loginUser = (User) request.getSession().getAttribute(Constants.FRONT_USER_SESSION);
		// 可借书数量
		int booksum = userService.searchUserByID(loginUser.getId()).getBooksum();
		try {
			if (booksum > 0) {
				if (book.getAmount() > 0) {
					record.setArea(book.getArea());
					record.setAutor(book.getAutor());
					record.setBegigtime(new Date());
					record.setBookid(book.getId());
					record.setBookName(book.getBookName());
					record.setBookrack(book.getBookrack());
					record.setBookType(book.getBookType());
					record.setFloor(book.getFloor());
					record.setLasttime(DateUtils.addDay(new Date(), 60));
					record.setPressName(book.getPressName());
					record.setStatus(0);
					record.setUserid(this.loginFrontUser(request).getId()); // 获取前台用户user对象
					RecordService.addRecord(record); // 添加记录到库

					book.setAmount(book.getAmount() - 1);
					bookService.updateBook(book);

					jsonResult.setCode("100");
					jsonResult.setMsg("借书成功");
					SpringUtils.renderJson(response, jsonResult);
					int sum = booksum - 1;
					loginUser.setBooksum(sum);
					userService.updateUser(loginUser);
				} else {
					jsonResult.setCode("200");
					jsonResult.setMsg("图书数量不够");
					SpringUtils.renderJson(response, jsonResult);
				}
			}
			else {
				//可借书数量为0
				jsonResult.setCode("300");
				SpringUtils.renderJson(response, jsonResult);
			}
		} catch (Exception e) {
			jsonResult.setCode("400");
			SpringUtils.renderJson(response, jsonResult);
			e.printStackTrace();
		}
	}

	@RequestMapping("/bookAdd")
	public void bookAdd(HttpServletRequest request, HttpServletResponse response, String bookName, String pressName,
			String bookType, String author, String bookSum, String floor, String area, String bookrack, String row,
			String lattice, String remark) {
		Book book = new Book();
		book.setBookName(bookName);
		book.setPressName(pressName);
		book.setBookType(Integer.parseInt(bookType));
		book.setAutor(author);
		book.setAmount(Integer.parseInt(bookSum));
		book.setFloor(floor);
		book.setArea(area);
		book.setBookrack(bookrack);
		book.setRow(row);
		book.setLattice(lattice);
		book.setRemark(remark);
		bookService.addBook(book);
	}

	@RequestMapping("/bookLocation")
	public String bookLocation(@RequestParam(value = "page", required = false) String page, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		int pageSize = Constants.PAGE_SIZE;
		if (page == null || page == "") {
			page = "1";
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", (Integer.parseInt(page) - 1) * pageSize); // 起始记录
		map.put("size", pageSize); // 把值存入map

		List<Book> books = bookService.getAllBook(map);// 获取所有图书信息 存入books
		int total = bookService.countNum(map); // 总记录数
		int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
		if (books != null) {
			model.addAttribute("books", books);
			model.addAttribute("total", total);
			model.addAttribute("page", page);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("totalPage", totalPage);
			return "book/bookLocation";
		}
		return null;
	}
}
