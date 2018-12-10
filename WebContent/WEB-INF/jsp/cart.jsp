<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Cart</title>

	<link href="${pageContext.request.contextPath}/css/common.css"
		rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/css/cart.css"
			rel="stylesheet" type="text/css">
</head>
<body>
	<%@include file="menu.jsp"%>
	<div class="container cart">
		<s:if test="#session.cart.cartItems.size()!=0">
			<div class="span24">
				<div class="step step1"></div>
				<table>
					<tbody>
						<tr>
							<th>Image</th>
							<th>Product</th>
							<th>Price</th>
							<th>Quantity</th>
							<th>Subtotal</th>
							<th>Operation</th>
						</tr>
						<s:iterator value="#session.cart.cartItems" var="cartItem">
							<tr>
								<td width="60"><input type="hidden" name="id" value="22" />
									<img
									src="${pageContext.request.contextPath}/<s:property value="#cartItem.product.image" />" /></td>
								<td><a target="_blank"><s:property
											value="#cartItem.product.pname" /></a></td>
								<td>CDN$:<s:property value="#cartItem.product.shop_price" /></td>
								<td class="quantity" width="60"><s:property
										value="#cartItem.count" /></td>
								<td width="140"><span class="subtotal">CDN$:<s:property
											value="#cartItem.subtotal" /></span></td>
								<td><a
									href="${pageContext.request.contextPath}/cart_removeCart.action?pid=<s:property value="#cartItem.product.pid" />"
									class="delete">Delete</a></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<dl id="giftItems" class="hidden" style="display: none;">
				</dl>
				<div class="total">
					<em id="promotion"></em> <em> Sign in to confirm if you have a discount </em> Credits: <em
						id="effectivePoint"><s:property value="#session.cart.total" /></em>
					Total Money: <strong id="effectivePrice">CDN$:<s:property
							value="#session.cart.total" /></strong>
				</div>
				<div class="bottom">
					<a href="${pageContext.request.contextPath}/cart_clearCart.action"
						id="clear" class="clear">Clear Shopping Cart</a> <a
						href="${pageContext.request.contextPath}/order_save.action" id="submit" class="submit">Proceed to
						checkout</a>
				</div>
			</div>
		</s:if>
		<s:else>
			<div class="span24">
				<div class="step step1">
					<span><h2>Your shopping cart is empty.Please add
							products to shopping cart first.</h2></span>
				</div>
			</div>
		</s:else>
	</div>

	<%@include file="footer.jsp"  %>
</body>
</html>