<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Login</title>

<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/login.css"
	rel="stylesheet" type="text/css" />

<script>
	function checkForm() {
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
	
	function change(){
		var image=document.getElementById("checkImg");
		image.src="${pageContext.request.contextPath}/checkImg.action?"+new Date().getTime();
	}
</script>

</head>
<body>

	<%@include file="menu.jsp"%>
	<div class="container login">
		<div class="span12">
			<div class="ad">
				<img src="${pageContext.request.contextPath}/image/login1.jpg"
					width="500" height="330" alt="member login" title="member login" />
			</div>
		</div>
		<div class="span12 last">
			<div class="wrap">
				<div class="main">
					<div class="title">
						<strong>Member</strong>USER LOGIN
					</div>
					<div>
						<s:actionerror />
					</div>
					<form id="loginForm"
						action="${pageContext.request.contextPath}/user_login.action"
						method="post" novalidate="novalidate"
						onsubmit="return checkForm();">
						<table>
							<tbody>
								<tr>
									<th>Username:</th>
									<td><input id="username" type="text" id="username"
										name="username" class="text" maxlength="20" /></td>
								</tr>
								<tr>
									<th>Password:</th>
									<td><input id="password" type="password" id="password"
										name="password" class="text" maxlength="20" autocomplete="off" /></td>
								</tr>
								<tr>
									<th>Verification code:</th>
									<td><span class="fieldSet"> <input type="text"
											id="checkcode" name="checkcode" class="text captcha"
											maxlength="4" autocomplete="off" /><img id="checkImg"
											class="captchaImage" onclick="change()"
											src="${pageContext.request.contextPath}/checkImg.action"
											title="Click to change" /></span></td>
								</tr>
								<tr>
									<th>&nbsp;</th>
									<td><label> <input type="checkbox"
											id="isRememberUsername" name="isRememberUsername"
											value="true" />Remember me </label> <label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a>Forget Password</a>
									</label></td>
								</tr>
								<tr>
									<th>&nbsp;</th>
									<td><input type="submit" class="submit" value="Login"></td>
								</tr>
								<tr class="register">
									<th>&nbsp;</th>
									<td>
										<dl>
											<dt>Do not have a account&nbsp;？</dt>
											<dd>
												Sign up and shopping now！ <a href="${pageContext.request.contextPath}/user_registPage.action">Sign up</a>
											</dd>
										</dl>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"  %>
</body>
</html>