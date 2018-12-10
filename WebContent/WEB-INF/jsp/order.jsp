<%@page import="org.apache.struts2.components.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>Generate Orders</title>
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
					<li>Successful</li>
				</ul>
			</div>


			<table>
				<tbody>
					<tr>
						<th colspan="5">Order number： <s:property value="model.oid" /></th>
					</tr>
					<tr>
						<th>Image</th>
						<th>Product</th>
						<th>Price</th>
						<th>Quantity</th>
						<th>Subtotal</th>
					</tr>
					<s:iterator value="model.orderItems" var="orderItem">
						<tr>
							<td width="60"><input type="hidden" name="id" value="22" />
								<img
								src="${ pageContext.request.contextPath }/<s:property value="#orderItem.product.image" />" /></td>
							<td><a target="_blank"><s:property
										value="#orderItem.product.pname" /></a></td>
							<td><s:property value="#orderItem.product.shop_price" /></td>
							<td class="quantity" width="60"><s:property value="#orderItem.count" /></td>
							<td width="140"><span class="subtotal">CDN$:<s:property value="#orderItem.subtotal" /></span></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<dl id="giftItems" class="hidden" style="display: none;">
			</dl>
			<div class="total">
				<em id="promotion"></em> Total Money: <strong id="effectivePrice">CDN$:<s:property value="model.total" /></strong>
			</div>
			<form id="orderForm" action="${ pageContext.request.contextPath }/order_payOrder.action" method="post">
				<input type="hidden" name="oid" value="<s:property value="model.oid" />" />
				<div class="span24">
					<p>
						Address：<input name="user.addr" type="text" value="<s:property value="model.user.addr" />"
							style="width: 350px" /> <br /> Name&nbsp;&nbsp;&nbsp;：<input
							name="order.user.username" type="text" value="<s:property value="model.user.name" />"
							style="width: 150px" /> <br /> Phone：<input
							name="order.user.phone" type="text" value="<s:property value="model.user.phone" />" style="width: 150px" />

					</p>
					<hr />
					<p>
						Select a bank：<br /> <input type="radio" name="pd_FrpId"
							value="ICBC-NET-B2C" checked="checked" />CBC <img
							src="${ pageContext.request.contextPath }/bank_img/icbc.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="pd_FrpId" value="BOC-NET-B2C" />BOC <img
							src="${ pageContext.request.contextPath }/bank_img/bc.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="pd_FrpId" value="ABC-NET-B2C" />ABC <img
							src="${ pageContext.request.contextPath }/bank_img/abc.bmp" align="middle" /> <br /> <input
							type="radio" name="pd_FrpId" value="BOCO-NET-B2C" />COM <img
							src="${ pageContext.request.contextPath }/bank_img/bcc.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="pd_FrpId" value="PINGANBANK-NET" />PAB
						<img src="${ pageContext.request.contextPath }/bank_img/pingan.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="pd_FrpId" value="CCB-NET-B2C" />CCB <img
							src="${ pageContext.request.contextPath }/bank_img/ccb.bmp" align="middle" /> <br /> <input
							type="radio" name="pd_FrpId" value="CEB-NET-B2C" />CEB <img
							src="${ pageContext.request.contextPath }/bank_img/guangda.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="pd_FrpId" value="CMBCHINA-NET-B2C" />CMB
						<img src="${ pageContext.request.contextPath }/bank_img/cmb.bmp" align="middle" />
					</p>
					<hr />
					<p style="text-align: right">
						<a
							href="javascript:document.getElementById('orderForm').submit();">
							<img
							src="${ pageContext.request.contextPath }/images/confirm.png"
							width="204" height="51" border="0" />
						</a>
					</p>
				</div>
			</form>
		</div>

	</div>
	<%@include file="footer.jsp"  %>
</body>
</html>