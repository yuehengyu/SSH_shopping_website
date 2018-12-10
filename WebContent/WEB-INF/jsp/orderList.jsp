<%@page import="org.apache.struts2.components.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>My Orders Page</title>
<link href="${ pageContext.request.contextPath }/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${ pageContext.request.contextPath }/css/cart.css"
	rel="stylesheet" type="text/css" />

</head>
<body>

	<%@include file="menu.jsp"%>

	<div class="container cart">

		<div class="span24">

			<div class="step step1">
				<ul>

					<li class="current"></li>
					<li>My Orders</li>
				</ul>
			</div>


			<table>
				<tbody>
					<s:iterator value="pageBean.list" var="order">
						<tr>
							<th colspan="5">Order number： <s:property value="#order.oid" />&nbsp;&nbsp;&nbsp;&nbsp;
								Order Status: <s:if test="#order.state==1">
									Not paying!!<a
										href="${ pageContext.request.contextPath }/order_findByOid.action?oid=<s:property value="#order.oid" />"><font
										color="red">Paying Now</font></a>
								</s:if> <s:if test="#order.state==2">
							 	Have been paid
							 </s:if> <s:if test="#order.state==3">
									<a href="${ pageContext.request.contextPath }/order_updateState.action?oid=<s:property value="#order.oid" />"><font color="red">Confirm receipt</font></a>
								</s:if> <s:if test="#order.state==4">
							 	Completed
							 </s:if>
							</th>
						</tr>

						<tr>
							<th>Image</th>
							<th>Product</th>
							<th>Price</th>
							<th>Quantity</th>
							<th>Subtotal</th>
						</tr>
						<s:iterator value="#order.orderItems" var="orderItem">
							<tr>
								<td width="60"><input type="hidden" name="id" value="22" />
									<img
									src="${ pageContext.request.contextPath }/<s:property value="#orderItem.product.image" />" /></td>
								<td><a target="_blank"><s:property
											value="#orderItem.product.pname" /></a></td>
								<td><s:property value="#orderItem.product.shop_price" /></td>
								<td class="quantity" width="60"><s:property
										value="#orderItem.count" /></td>
								<td width="140"><span class="subtotal">CDN$:<s:property
											value="#orderItem.subtotal" /></span></td>
							</tr>
						</s:iterator>
					</s:iterator>
					<tr>
						<td colspan="5">
							<div class="pagination">
								<span>current page:<s:property value="pageBean.page" />/<s:property
										value="pageBean.totalPage" /></span>
								<s:if test="pageBean.page!=1">
									<a class="firstPage"
										href="${pageContext.request.contextPath}/order_findByUid.action?page=1">&nbsp;</a>
									<a class="previousPage"
										href="${pageContext.request.contextPath}/order_findByUid.action?page=<s:property value="pageBean.page-1"/>">&nbsp;</a>
								</s:if>
								<s:iterator begin="1" end="pageBean.totalPage" var="i">
									<s:if test="pageBean.page!=#i">

										<a
											href="${pageContext.request.contextPath}/order_findByUid.action?page=<s:property
								value="#i" />"><s:property
												value="#i" /></a>
									</s:if>
									<s:else>
										<span class="currentPage"><s:property value="#i" /></span>
									</s:else>
								</s:iterator>
								<s:if test="pageBean.page!=pageBean.totalPage">
									<a class="nextPage"
										href="${pageContext.request.contextPath}/order_findByUid.action?page=<s:property value="pageBean.page+1"/>">&nbsp;</a>
									<a class="lastPage"
										href="${pageContext.request.contextPath}/order_findByUid.action?page=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
								</s:if>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<%@include file="footer.jsp"  %>
</body>
</html>