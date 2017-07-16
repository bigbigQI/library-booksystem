<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>

	<table class="table table-striped table-bordered table-hover datatable">
		<tr>
			<td width="100" align="center" bgcolor="#cd853f">用户编号</td>
			<td width="100" align="center" bgcolor="#cd853f">用户姓名</td>
			<td width="100" align="center" bgcolor="#cd853f">邮箱</td>
			<td width="132" align="center" bgcolor="#cd853f">性别</td>
			<td width="132" align="center" bgcolor="#cd853f">可借书数量</td>
			<td width="152" align="center" bgcolor="#cd853f">操作</td>
		</tr>
		<c:forEach items="${users }" var="users">
			<tr>
				<td height="30" align="center" class="l_table_1">${users.id }</td>
				<td height="30" align="center" class="l_table_1">${users.name }</td>
				<td height="30" align="center" class="l_table_1">${users.email }</td>
				<td height="30" align="center" class="l_table_1"><c:if
						test="${users.sex == 1}">男</c:if> <c:if test="${users.sex == 2}">女</c:if></td>
				<td height="30" align="center" class="l_table_1">${users.booksum }</td>
				<td height="30" align="center" class="l_table_1"><a style="color:#f08080"
					href="javascript:deleteUser('${users.id }')"><u><strong>删除</strong></u></a></td>
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
	function deleteUser(id) {
		$.ajax({
			type : "POST",
			dataType : 'json',
			data : {
				'id' : id
			},
			url : "${pageContext.request.contextPath}/user/deleteUserById.do",
			success : function(data) {
				if (data.code == '100') {
					//window.location.href="${path}/user/getUserManage.do";
					alert("删除用户成功！");
					$("#deleteUser").click();
				}
			}
		});
	}
	function changePage() {
		alert($("#pageNum").val());
		var goalPage = $("#pageNum").val();
		$.post("${pageContext.request.contextPath}/user/getUserManage.do?page=" + goalPage, function(data) {
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
		alert(previousPage);
		$.post("${pageContext.request.contextPath}/user/getUserManage.do?page=" + previousPage, function(data) {
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
		$.post("${pageContext.request.contextPath}/user/getUserManage.do?page=" + nextPage, function(data) {
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
