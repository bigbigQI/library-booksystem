<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>

	<table class="table table-striped table-bordered table-hover datatable">
		<tr>
			<td align="center" width="18%" bgcolor="#cd853f">书名</td>
			<td align="center" width="18%" bgcolor="#cd853f">出版社名字</td>
			<td align="center" width="15%" bgcolor="#cd853f">作者</td>
			<td align="center" width="10%" bgcolor="#cd853f">现存数量</td>
			<td align="center" width="10%" bgcolor="#cd853f">楼层</td>
			<td align="center" width="13%" bgcolor="#cd853f">区域</td>
			<td align="center" width="10%" bgcolor="#cd853f">书架</td>
			<td align="center" width="6%" bgcolor="#cd853f">操作</td>
		</tr>
		<c:forEach items="${books }" var="book">
			<tr>
				<td align="center">${book.bookName }</td>
				<td align="center">${book.pressName }</td>
				<td align="center">${book.autor }</td>
				<td align="center">${book.amount }</td>
				<td align="center">${book.floor }</td>
				<td align="center">${book.area }</td>
				<td align="center">${book.bookrack }</td>
				<td align="center"><a style="color:#f08080"
					href="javascript:updateBook('${book.id }')"><u><strong>借书</strong></u>	</a></td>
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
	function updateBook(id) {
		$.ajax({
			type : "POST",
			dataType : 'json',
			data : {
				'id' : id
			},
			url : "${pageContext.request.contextPath}/book/borrowBook.do",
			success : function(data) {
				if (data.code == '100') {
					alert("借阅成功！");
					$("#getAllBook").click();
				} else if(data.code == '200') {
					alert("借阅失败，图书数量不足！");
					$("#getAllBook").click();
				} else if(data.code == '300') {
					alert("借阅失败，可借书数量已达上限！");
				} else if(data.code == '400') {
					alert("借阅失败，出现未知错误!");
				}
			}
		});
	}
</script>


</html>
