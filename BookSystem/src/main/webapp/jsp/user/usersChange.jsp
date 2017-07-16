<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<table class="table table-hover">
	<tbody>
		<tr class="active">
			<td>&nbsp;用&nbsp;户&nbsp;名&nbsp;：</td>
			<td><input type="text" class="form-control" id="t_changeName"
				placeholder="请输入用户名..." value=${user.name } disabled></td>
		</tr>
		<tr class="success">
			<td>&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;级&nbsp;&nbsp;：</td>
			<td><input type="text" class="form-control" id="t_changeGrade"
				placeholder="请输入年级..." value="${user.grade }"></td>
		</tr>
		<tr class="warning">
			<td>手机号码：</td>
			<td><input type="text" class="form-control" id="t_changePhone"
				placeholder="请输入手机号..." value=${user.phone }></td>
		</tr>
		<tr class="danger">
			<td>身份证号：</td>
			<td><input type="text" class="form-control" id="t_changeIdCard"
				placeholder="请输入身份证号..." value=${user.code }></td>
		</tr>
		<tr class="info">
			<td>&nbsp;&nbsp;邮&nbsp;&nbsp;&nbsp;&nbsp;箱&nbsp;&nbsp;：</td>
			<td><input type="text" class="form-control" id="t_changeMail"
				placeholder="请输入邮箱..." value="${user.email }"></td>
		</tr>

	</tbody>
</table>