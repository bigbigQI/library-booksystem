<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="taglibs.jsp"%>
<title>用户登录</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/CSS/style.css" />

<style type="text/css">
#wrap {
	width: 900px;
	height: 100%;
	margin: auto;
	background-color: #fffacd;
	margin: auto;
}
}
</style>

</head>
<%-- <body class="linear">
	<div id="login_center">
		<div id="login_area" >
			<div id="login_form" style="padding: 100px 100px 10px;">
				<form action="${path }/user/login.do" method="post" id="myform"
					class="bs-example bs-example-form">
					<div class="input-group">
						<span class="input-group-addon">用户名</span> <input type="text"
							name="name" id="name" class="form-control" placeholder="请输入用户名"
							value="${name }">
					</div>
					<div class="input-group">
						<span class="input-group-addon">密&nbsp;&nbsp;&nbsp;码</span> <input
							type="password" name="pass" id="pass" value="${pass }"
							class="form-control">
					</div>
					<div id="btn_area">
						<input type="submit" name="submit" id="sub_btn" value="登录">
					</div>
				</form>
			</div>
		</div>
	</div>
</body> --%>
<body>
	<div id="wrap">

		<div class="header"
			style="background:url(${frontPath}/img/header1.jpg)">
			<div style="position:absolute;left:700px;top:40px">
				<span style="font-size:45px;color:#fff">图书在线管理系统</span>
			</div>
		</div>


		<div class="center_content">
			<div class="left_content">
				<div class="title">
					<span class="title_icon"><img
						src="${path }/images/bullet1.gif" alt="" title="" /></span>我的账号
				</div>

				<div class="feat_prod_box_details">
					<div class="message warning">
						<div class="inset">
							<img src="${frontPath }/img/loginHead.jpg" />
							<form action="${path }/user/login.do" method="post" id="myform">
								<p style="color:#005951;
									align="left">请输入账号</p>
								<li><input type="text" class="text" name="name" id="name"
									value="${name }"></a></li>
								<div class="clear"></div>
								<p style="color:#005951" align="left">请输入密码</p>
								<li><input type="password" name="pass" id="pass"
									value="${pass }"> <a href="#" class="icon lock"></a></li>
								<div class="clear"></div>
								<div class="submit">
									<input type="submit" value="登录">
									<h4>
										<a id="register" href="#">注册</a>
									</h4>
									<div class="clear"></div>
								</div>

							</form>
						</div>
					</div>

				</div>

				<div class="clear"></div>
			</div>
			<div class="clear"></div>
		</div>
		<!--end of center content-->


		<div class="footer"></div>


	</div>
	<!-- 添加用户模态框 -->
	<div class="modal fade" id="registerModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel" style="text-align:center">用户注册</h4>
				</div>
				<div class="modal-body">

					<table class="table table-hover">
						<tbody>
							<tr class="active">
								<td>&nbsp;用&nbsp;户&nbsp;名&nbsp;：</td>
								<td><input type="text" class="form-control" id="t_userName"
									placeholder="请输入用户名..."></td>
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

</body>
<script type="text/javascript">
	$("#t_register").click(function() {
		var t_userName = $("#t_userName").val();
		var t_userPassword = $("#t_userPassword").val();
		var t_phone = $("#t_phone").val();
		var t_idCard = $("#t_idCard").val();
		var t_mail = $("#t_mail").val();
		var t_sex = $("#t_sex:checked").val();

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
			}); 
		
	});
	$("#register").click(function() {
		$("#registerModal").modal("show");
	});
	function toSubmit() {
		if (checkNameAndPass() == true) {
			if ($("#name").val() != "" && $("#pass").val() != "") {
				$("#myform").submit();
			}
		}
	}
	function checkNameAndPass() {
		var name = $("#name").val();
		var pass = $("#pass").val();
		var img = $("kaptchafield").val();
		if (name == "") {
			alert("用户名不能为空");
			return false;
		} else if (pass == "") {
			alert("密码不能为空");
			return false;
		} else if (img == "") {
			alert("验证码不能为空");
			return false;
		} else {
			return true;
		}
	}

	var msg = "${msg}";
	if (msg != "") {
		alert(msg);
	}
</script>
</html>