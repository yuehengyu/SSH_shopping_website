<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<table border="1" width="100%">
	<s:iterator value="list" var="orderItem">
		<tr>
			<td><img width="40" height="45"
				src="${pageContext.request.contextPath}/<s:property value="#orderItem.product.image" />" /></td>
			<td><s:property value="#orderItem.count" /></td>
			<td><s:property value="#orderItem.subtotal" /></td>
		</tr>
	</s:iterator>
</table>