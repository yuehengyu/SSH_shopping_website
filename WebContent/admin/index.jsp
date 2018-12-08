<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" />
<head>
<title>YHY shopping website Admin</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<style type="text/css">
body {
	color: white;
}
</style>


<script>
	function checkForm(){
		var username = document.getElementById("username").value;
		if (username == null || username == '') {
			alert("Username can not be empty");
			return false;
		}

		var password = document.getElementById("password").value;
		if (password == null || password == '') {
			alert("Password can not be empty");
			return false;
		}
	}
</script>
</head>
<body style="background: #278296">
	<center>
		<s:actionerror />
	</center>
	<form method="post"
		action="${pageContext.request.contextPath }/adminUser_login.action"
		target="_parent" name='theForm' onsubmit="return checkForm();">
		<table cellspacing="0" cellpadding="0" style="margin-top: 100px"
			align="center">
			<tr>
				<td><img
					src="${pageContext.request.contextPath }/image/login1.jpg"
					border="0" alt="SHOP" /></td>
				<td style="padding-left: 50px">
					<table>
						<tr>
							<td>管理员姓名：</td>
							<td><input type="text" name="username" id="username" /></td>
						</tr>
						<tr>
							<td>管理员密码：</td>
							<td><input type="password" name="password" id="password" /></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><input type="submit" value="Submit" class="button" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<input type="hidden" name="act" value="signin" />
	</form>

</body>