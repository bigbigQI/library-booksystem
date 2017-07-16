package com.zhang.util;

public class PageUtil {

	/**
	 * 获取分页代码
	 * 
	 * @param targetUrl
	 *            目标地址
	 * @param totalNum
	 *            总记录数
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            每页大小
	 * @return
	 */
	public static String getPagation(String targetUrl, int totalNum, int currentPage, int pageSize) {
		int totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
		if (totalPage == 0) {
			return "<font color=red>未查询到数据！</font>";
		}
		StringBuffer pageCode = new StringBuffer();
		pageCode.append("<a href='#' id=\"firstPage\">首页&nbsp;&nbsp;</a>");
		if (currentPage == 1) {
			pageCode.append("<a href='" + targetUrl + "?page=1'>上一页&nbsp;&nbsp;</a>");
		} else {
			pageCode.append(
					"<a href='" + targetUrl + "?page=" + (currentPage - 1) + "'>&nbsp;&nbsp;上一页&nbsp;&nbsp;</a>");
		}

		if (currentPage == totalPage) {
			pageCode.append("<a href='" + targetUrl + "?page=" + totalPage + "'>下一页&nbsp;&nbsp;</a>");
		} else {
			pageCode.append("<a href='" + targetUrl + "?page=" + (currentPage + 1) + "'>下一页&nbsp;&nbsp;</a>");
		}
		pageCode.append("<a href='" + targetUrl + "?page=" + totalPage + "'>&nbsp;&nbsp;尾页</a>");
		pageCode.append("<script type='text/javascript'>");
		pageCode.append("	$('#firstPage').click(function(){ $.post(" + targetUrl + "?page=1"
				+ ",function(data){$('#text').html(data);});});");
		return pageCode.toString();
	}

	public static String getPageJS(String targetUrl, int totalNum, int currentPage, int pageSize) {
		int totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;

		StringBuffer pageJS = new StringBuffer();
		pageJS.append("<script type='text/javascript'>");
		pageJS.append("	$('#firstPage').click(function(){ $.post(" + targetUrl + "?page=1"
				+ ",function(data){$('#text').html(data);});});");
		return pageJS.toString();
	}
}
