<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="./shopping/index.htm"> <img
				src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.gif"
				alt="YHY" />
			</a>
		</div>
	</div>
	<div class="span9">
		<div class="headerAd">
			<img src="${pageContext.request.contextPath}/image/header.jpg"
				width="320" height="50" alt="Genuine" title="Genuine" />
		</div>
	</div>
	<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
				<s:if test="#session.existUser==null">
					<li id="headerLogin" class="headerLogin"
						style="display: list-item;"><a
						href="${pageContext.request.contextPath}/user_loginPage.action">Login</a>|</li>
					<li id="headerRegister" class="headerRegister"
						style="display: list-item;"><a
						href="${pageContext.request.contextPath}/user_registPage.action">Register</a>|</li>
				</s:if>
				<s:else>
					<li id="headerLogin" class="headerLogin"
						style="display: list-item;"><s:property value="#session.existUser.username" />|</li>
					<li id="headerLogin" class="headerLogin"
						style="display: list-item;"><a
						href="#">Orders</a>|</li>
					<li id="headerLogout" class="headerRegister"
						style="display: list-item;"><a href="${pageContext.request.contextPath}/user_quit.action">[Log out]</a>|</li>
				</s:else>
				<li><a>Member Center</a> |</li>
				<li><a>Guide</a> |</li>
				<li><a>About</a></li>
			</ul>
		</div>
		<div class="cart">
			<a href="./cart.htm">Shopping cart</a>
		</div>
		<div class="phone">
			Customer service hot line: <strong>5196949329</strong>
		</div>
	</div>
	<div class="span24">
		<ul class="mainNav">
			<li><a href="${pageContext.request.contextPath}/index.action">Home</a> |</li>
			<s:iterator value="#session.clist" var="c">
				<li><a href="./蔬菜分类.htm"><s:property value="#c.cname" /></a> |</li>
			</s:iterator>
		</ul>
	</div>


</div>