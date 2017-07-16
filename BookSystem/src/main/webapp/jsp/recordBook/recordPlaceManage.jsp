<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="15" class="l_table_1">&nbsp;</td>
		</tr>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="100" height="30" align="center" bgcolor="#0489b6"
				class="l_table_1">书名</td>
			<td width="100" height="30" align="center" bgcolor="#0489b6"
				class="l_table_1">出版社名字</td>
			<td width="100" height="30" align="center" bgcolor="#0489b6"
				class="l_table_1">作者</td>
			<td width="100" height="30" align="center" bgcolor="#0489b6"
				class="l_table_1">楼层</td>
			<td width="132" height="30" align="center" bgcolor="#0489b6"
				class="l_table_1">区域</td>
			<td width="128" height="30" align="center" bgcolor="#0489b6"
				class="l_table_1">书架</td>
		</tr>

		<c:forEach items="${records }" var="record">
			<tr>
				<td height="30" align="center" class="l_table_1">${record.bookName }</td>
				<td height="30" align="center" class="l_table_1">${record.pressName }</td>
				<td height="30" align="center" class="l_table_1">${record.autor }</td>
				<td height="30" align="center" class="l_table_1">${record.floor }</td>
				<td height="30" align="center" class="l_table_1">${record.area }</td>
				<td height="30" align="center" class="l_table_1">${record.bookrack }</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="row-fluid">
						<div class="span4" style="flaot:left;">
							<label> 每页${pageSize }条记录 ,共${total }条记录</label>
						</div>
						<div class="span4">
							<ul class="list-inline" style="text-align:center"
								id="previousNext">
								<li class="prev disabled"><a id="previousPage" href="#">←
										上一页</a></li>
								<li><select id="pageNum" onchange="changePage()"
									style="width:50px">
										<c:forEach var="i" begin="1" end="${totalPage }">
											<option value="${i }">${i}</option>
										</c:forEach>
								</select></li>
								<li class="next"><a id="nextPage" href="#">下一页 → </a></li>
							</ul>
						</div>
						<div class="span4"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="currentPage" value="${page }">
	<input type="hidden" id="totalPage" value="${totalPage }">

</body>
<script type="text/javascript">
	function changePage() {
		alert($("#pageNum").val());
		var goalPage = $("#pageNum").val();
		$.post("${pageContext.request.contextPath}/book/getAllBook.do?page=" + goalPage, function(data) {
			$("#text").html(data);
			var list = document.getElementById("pageNum").options;
			//得到select中，上一页的option
			var goalOption = list[goalPage - 1];
			//修改select的选中项
			goalOption.selected = true;
		});
	}

	$("#previousPage").click(function() {
		var currentPage = $("#currentPage").val();
		if (currentPage > 1) {
			var previousPage = currentPage - 1;
		} else {
			var previousPage = currentPage;
		}
		$.post("${pageContext.request.contextPath}/book/getAllBook.do?page=" + previousPage, function(data) {
			$("#text").html(data);
			//得到select的option集合
			var list = document.getElementById("pageNum").options;
			//得到select中，上一页的option
			var previousOption = list[previousPage - 1];
			//修改select的选中项
			previousOption.selected = true;
		});
	});
	$("#nextPage").click(function() {
		var currentPage = $("#currentPage").val();
		var totalPage = $("#totalPage").val();
		if (currentPage < totalPage) {
			var nextPage = parseInt(currentPage) + 1;
		} else {
			var nextPage = currentPage;
		}
		$.post("${pageContext.request.contextPath}/book/getAllBook.do?page=" + nextPage, function(data) {
			$("#text").html(data);
			//得到select的option集合
			var list = document.getElementById("pageNum").options;
			//得到select中，上一页的option
			var nextOption = list[nextPage - 1];
			//修改select的选中项
			nextOption.selected = true;
		});

	});
</script>
</html>
