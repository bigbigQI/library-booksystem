<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<%@include file="../taglibs.jsp"%>
<title>主页面</title>
<style type="text/css">
.select_3 {
	width: 220px;
	padding-left: 5px;
	margin-left: 0px;
	height: 30px;
}

#wrap {
	width: 900px;
	height: 140%;
	margin: auto;
	background-color: #fffacd;
	margin: auto;
	margin: auto;
}

.footer {
	height: 100px;
	border-top: 1px #b2b2b2 dashed;
	background: url(${path}/images/footer_bg.gif) no-repeat bottom;
}

body {
	background-color: #6F605A;
	/* background: url(${path}/images/bg.jgp); */
	font-family: Arial, Helvetica, sans-serif;
	padding: 0;
	font-size: 12px;
	margin: 0px auto auto auto;
	color: #000000;
}

.header {
	width: 900px;
	height: 181px;
	background: url(images/header.jpg) no-repeat center;
}
</style>
<script type="text/javascript">
	$(function() {
		$("#mainPage").click();
	})
</script>
</head>
<body>
	<div id="wrap">
		<div class="header"
			style="background:url(${frontPath}/img/header1.jpg)">
			<div style="position:absolute;left:52%;top:40px">
				<span style="font-size:45px;color:#fff">图书在线管理系统</span>
			</div>
		</div>

		<nav class="navbar navbar-inverse" role="navigation">
			<div class="container-fluid">

				<div>
					<ul class="nav navbar-nav">
						<li id="mainPage" class="dropdown"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown">主&nbsp;&nbsp;页</a></li>
						<c:if test="${sessionScope.FRONT_USER_SESSION.name == 'admin'}">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"> 用户管理 <b class="caret"></b>
							</a>
								<ul class="dropdown-menu">
									<li id="selectUser"><a href="#">查询用户</a></li>
									<li id="deleteUser"><a href="#">删除用户</a></li>
									<li id="addUser"><a href="#">添加用户</a></li>
								</ul></li>
						</c:if>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> 图书管理 <b class="caret"></b>
						</a>
							<ul class="dropdown-menu">
								<c:if test="${sessionScope.FRONT_USER_SESSION.name == 'admin'}">
									<li id="addBook"><a href="#">添加图书</a></li>
								</c:if>
								<li id="getAllBook"><a href="#">借阅图书</a></li>
								<li id="returnBook"><a href="#">归还图书</a></li>
								<li id="getBookLocation"><a href="#">图书位置</a></li>

							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">个人中心<b class="caret"></b>
						</a>
							<ul class="dropdown-menu">
								<li id="changUserMessage"><a href="#">修改信息</a></li>
								<li id="feesMessage"><a href="#">欠费查询</a></li>
								<li id="getAllRecord"><a href="#">借阅记录</a></li>
							</ul>
					</ul>
				</div>
				<!-- 搜索框 -->
				<div class="input-group col-md-3"
					style="margin-top:8px;margin-left:500px">
					<input type="text" class="form-control" id="t_search_content"
						placeholder="请输入图书名" /> <span class="input-group-btn">
						<button class="btn btn-info btn-search" id="t_search">
							<img style="width:20px;height:20px"
								src="${pageContext.request.contextPath }/common/img/search.png" />
						</button>
					</span>
				</div>
				<div class="col-md-3" style="margin-top:-35px;margin-left:730px">
					<span style="color:white;font-size:5px">欢迎登陆,${sessionScope.FRONT_USER_SESSION.name }!</span>
					<button id="logout" style="background-color:black;border:0px">
						<img style="width:20px;height:20px"
							src="${frontPath }/img/log-out.png" />
					</button>
				</div>
			</div>
		</nav>
		<div id="text">
			<h1></h1>
		</div>
		<!-- 添加用户模态框 -->
		<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel"
							style="text-align:center">用户注册</h4>
					</div>
					<div class="modal-body">

						<table class="table table-hover">
							<tbody>
								<tr class="active">
									<td>&nbsp;用&nbsp;户&nbsp;名&nbsp;：</td>
									<td><input type="text" class="form-control"
										id="t_userName" placeholder="请输入用户名..."></td>
								</tr>
								<tr class="success">
									<td>&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;&nbsp;：</td>
									<td><input type="password" class="form-control"
										id="t_userPassword" placeholder="请输入密码..."></td>
								</tr>
								<tr class="warning">
									<td>确认密码：</td>
									<td><input type="password" class="form-control"
										id="t_userPassword2" placeholder="再次输入密码..."></td>
								</tr>
								<tr class="danger">
									<td>手机号码：</td>
									<td><input type="text" class="form-control" id="t_phone"
										placeholder="请输入手机号..."></td>
								</tr>
								<tr class="active">
									<td>身份证号：</td>
									<td><input type="text" class="form-control" id="t_idCard"
										placeholder="请输入身份证号..."></td>
								</tr>
								<tr class="success">
									<td>&nbsp;&nbsp;邮&nbsp;&nbsp;&nbsp;&nbsp;箱&nbsp;&nbsp;：</td>
									<td><input type="text" class="form-control" id="t_mail"
										placeholder="请输入邮箱..."></td>
								</tr>
								<tr class="warning">
									<td>&nbsp;&nbsp;性&nbsp;&nbsp;&nbsp;&nbsp;别&nbsp;&nbsp;：</td>
									<td><label class="radio-inline">
											&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="t_sex"
											id="t_sex" value="1" checked>男&nbsp;&nbsp;
									</label> <label class="radio-inline"> <input type="radio"
											name="t_sex" id="t_sex" value="2">女
									</label></td>
								</tr>

							</tbody>
						</table>


					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" id="t_register"
							data-dismiss="modal">确定</button>
					</div>
				</div>
			</div>
		</div>
		<!-- 添加图书模态框 -->
		<div class="modal fade" id="addBookModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel"
							style="text-align:center">添加图书信息</h4>
					</div>
					<div class="modal-body">

						<table class="table table-bordered">
							<tbody>
								<tr class="active">
									<td>名称</td>
									<td><input type="text" class="form-control"
										id="t_bookName" placeholder="请输入图书名称..."></td>
									<td>出版社</td>
									<td><input type="text" class="form-control"
										id="t_pressName" placeholder="请输入出版社名称..."></td>
								</tr>
								<tr class="success">
									<td>类别</td>
									<td><select name="bookType" id="t_bookType"
										class="select_3">
											<option value="0">--请选择图书类别--</option>
											<option value="1">文学</option>
											<option value="2">杂志</option>
											<option value="3">科幻</option>
											<option value="4">经济</option>
											<option value="5">哲学</option>
											<option value="6">计算机</option>
									</select></td>
									<td>&nbsp;作&nbsp;&nbsp;者&nbsp;</td>
									<td><input type="text" class="form-control" id="t_author"
										placeholder="请输入作者..."></td>
								</tr>
								<tr class="warning">
									<td>数量</td>
									<td><input type="text" class="form-control" id="t_bookSum"
										placeholder="请输入图书数量..."></td>
									<td>&nbsp;楼&nbsp;&nbsp;层&nbsp;</td>
									<td><input type="text" class="form-control" id="t_floor"
										placeholder="请输入楼层..."></td>
								</tr>
								<tr class="danger">
									<td>区域</td>
									<td><input type="text" class="form-control" id="t_area"
										placeholder="请输入区域..."></td>
									<td>&nbsp;书&nbsp;&nbsp;架&nbsp;</td>
									<td><input type="text" class="form-control"
										id="t_bookrack" placeholder="请输入所在书架..."></td>
								</tr>
								<tr class="info">
									<td>层数</td>
									<td><input type="text" class="form-control" id="t_row"
										placeholder="请输入图书所在层数..."></td>
									<td>&nbsp;格&nbsp;&nbsp;数&nbsp;</td>
									<td><input type="text" class="form-control" id="t_lattice"
										placeholder="请输入图书所在格数..."></td>
								</tr>
							</tbody>
						</table>

						<table class="table table-bordered">
							<tbody>
								<tr class="active">
									<td style="width:50px">简介</td>
									<td><input type="text" class="form-control" id="t_remark"
										placeholder="请输入图书简介信息..."></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="t_bookSubmit">确定</button>
					</div>
				</div>
			</div>
		</div>

		<!-- 修改个人信息模态框 -->
		<div class="modal fade" id="changeUserModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel"
							style="text-align:center">用户信息修改</h4>
					</div>
					<div class="modal-body" id="changeUserBodu"></div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" id="t_save"
							data-dismiss="modal">保存</button>
					</div>
				</div>
			</div>
		</div>
		<!-- 欠费提示框 -->
		<div class="modal fade" id="feesModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel"
							style="text-align:center">用户注册</h4>
					</div>
					<div class="modal-body" style="background-color:#FDF5E6">
						<h1 style="text-align:center">您当前的欠费为0元.</h1>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
					</div>
				</div>
			</div>
		</div>

	</div>

</body>
<script type="text/javascript">
	$("#logout").click(function() {
		$.post("${pageContext.request.contextPath}/user/logout.do", function(data) {
		alert("注销成功！");
			window.location.href = "${pageContext.request.contextPath}/user/login.do";
		});
	});

	$("#mainPage").click(function() {
		$.post("${pageContext.request.contextPath}/user/mainPage.do", function(data) {
			$("#text").html(data);
		});
	});


	$("#t_search").click(function() {
		var searchContent = $("#t_search_content").val();
		$.post("${pageContext.request.contextPath}/book/getSearchBook.do", {
			'bookName' : searchContent
		}, function(data) {
			$("#text").html(data);
		});
	});

	$("#feesMessage").click(function() {
		$("#feesModal").modal("show");
	});

	$("#t_save").click(function() {
		var changeName = $("#t_changeName").val();
		var changeGrade = $("#t_changeGrade").val();
		var changePhone = $("#t_changePhone").val();
		var changeIdCard = $("#t_changeIdCard").val();
		var changeMail = $("#t_changeMail").val();

		$.post("${pageContext.request.contextPath}/user/userUpdate.do", {
			'name' : changeName,
			'grade' : changeGrade,
			'phone' : changePhone,
			'idCard' : changeIdCard,
			'email' : changeMail
		}, function(data) {
			alert("修改成功！")
		});
	});

	$("#changUserMessage").click(function() {
		$.post("${pageContext.request.contextPath}/user/getUserMessage.do", function(data) {
			$("#changeUserBodu").html(data);
			$("#changeUserModal").modal("show");
		});
	});



	$("#t_bookSubmit").click(function() {
		var t_bookName = $("#t_bookName").val();
		var t_pressName = $("#t_pressName").val();
		var t_bookType = $("#t_bookType").val();
		var t_author = $("#t_author").val();
		var t_bookSum = $("#t_bookSum").val();
		var t_floor = $("#t_floor").val();
		var t_area = $("#t_area").val();
		var t_bookrack = $("#t_bookrack").val();
		var t_row = $("#t_row").val();
		var t_lattice = $("#t_lattice").val();
		var t_remark = $("#t_remark").val();

		$.post("${pageContext.request.contextPath}/book/bookAdd.do", {
			'bookName' : t_bookName,
			'pressName' : t_pressName,
			'bookType' : t_bookType,
			'author' : t_author,
			'bookSum' : t_bookSum,
			'floor' : t_floor,
			'area' : t_area,
			'bookrack' : t_bookrack,
			'row' : t_row,
			'lattice' : t_lattice,
			'remark' : t_remark
		}, function(data) {
			alert("添加图书成功！");

		});
	});


	$("#t_register").click(function() {
		var t_userName = $("#t_userName").val();
		var t_userPassword = $("#t_userPassword").val();
		var t_userPassword2 = $("#t_userPassword2").val();
		var t_phone = $("#t_phone").val();
		var t_idCard = $("#t_idCard").val();
		var t_mail = $("#t_mail").val();
		var t_sex = $("#t_sex:checked").val();

		if (t_userName == "" || t_userName == null || t_userPassword == null ||
			t_userPassword == "" || t_phone == null || t_phone == "" || t_idCard == null || t_idCard == "" ||
			t_mail == null || t_mail == "" || t_sex == null || t_sex == "") {
			alert("请填写完善信息！");
			return;
		}
		if (t_userPassword != t_userPassword2) {
			alert("两次输入密码不相同！");
			return;
		}
		$.post("${pageContext.request.contextPath}/user/userAdd.do",
			{
				'username' : t_userName,
				'password' : t_userPassword,
				'phone' : t_phone,
				'idCard' : t_idCard,
				'mail' : t_mail,
				'sex' : t_sex
			}, function(data) {
				alert("注册成功！");
				$("#selectUser").click();
			});
	});
	$("#getAllRecord").click(function() {
		$.post("${pageContext.request.contextPath}/record/getAllRecord.do", function(data) {
			$("#text").html(data);
		});
	});
	$("#returnBook").click(function() {
		$.post("${pageContext.request.contextPath}/record/toReturnBook.do", function(data) {
			$("#text").html(data);
		});
	});
	$("#getBookLocation").click(function() {
		$.post("${pageContext.request.contextPath}/book/bookLocation.do", function(data) {
			$("#text").html(data);
		});
	});
	$("#getAllBook").click(function() {
		$.post("${pageContext.request.contextPath}/book/getAllBook.do", function(data) {
			$("#text").html(data);
		});
	});
	$("#deleteUser").click(function() {
		$.post("${pageContext.request.contextPath}/user/getUserManage.do", function(data) {
			$("#text").html(data);
		});
	});
	$("#selectUser").click(function() {
		$.post("${pageContext.request.contextPath}/user/getUserSelect.do", function(data) {
			$("#text").html(data);
		});
	});
	$("#addUser").click(function() {
		$("#addUserModal").modal("show");
	});
	$("#addBook").click(function() {
		$("#addBookModal").modal("show");
	});
</script>

</html>