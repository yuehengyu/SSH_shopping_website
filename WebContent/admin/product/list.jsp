<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>
<script type="text/javascript">
			function addProduct(){
				window.location.href = "${pageContext.request.contextPath}/adminProduct_addPage.action";
			}
		</script>
</HEAD>
<body>
	<br>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/user/list.jsp"
		method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>List
							of Products</strong></td>
				</tr>
				<tr>
					<td class="ta_01" align="right">
						<button type="button" id="add" name="add" value="AddCategory"
							class="button_add" onclick="addProduct()">Add</button>

					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

								<td align="center" width="10%">Number</td>
								<td align="center" width="17%">Image</td>
								<td align="center" width="20%">Name of Products</td>
								<td align="center" width="17%">Shop Price</td>
								<td align="center" width="17%">Popular/Hot</td>
								<td width="7%" align="center">Edit</td>
								<td width="7%" align="center">Delete</td>
							</tr>
							<s:iterator var="product" value="pageBean.list" status="status">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="5%"><s:property value="#status.count" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><img alt="product image" width="40"
										height="45"
										src="${pageContext.request.contextPath}/<s:property value="#product.image"/>" />
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><s:property value="#product.pname" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">$<s:property value="#product.shop_price" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><s:if test="#product.is_hot==1">Yes</s:if> <s:else>No</s:else>
									</td>
									<td align="center" style="HEIGHT: 22px"><a
										href="${pageContext.request.contextPath}/adminProduct_edit.action?pid=<s:property value="#product.pid"/>">
											<img
											src="${pageContext.request.contextPath}/images/i_edit.gif"
											border="0" style="CURSOR: hand">
									</a></td>

									<td align="center" style="HEIGHT: 22px"><a
										href="${pageContext.request.contextPath}/adminProduct_delete.action?pid=<s:property value="#product.pid"/>">
											<img
											src="${pageContext.request.contextPath}/images/i_del.gif"
											width="16" height="16" border="0" style="CURSOR: hand">
									</a></td>
								</tr>
							</s:iterator>
						</table>
					</td>
				</tr>

				<tr align="center">
					<td class="ta_01" align="center" bgColor="#afd1f3">Current
						Page:<s:property value="pageBean.page" />/<s:property
							value="pageBean.totalPage" />&nbsp;&nbsp;&nbsp;&nbsp; <s:if
							test="pageBean.page!=1">
							<a
								href="${pageContext.request.contextPath}/adminProduct_findAll.action?page=1">first
								page</a>|
								<a
								href="${pageContext.request.contextPath}/adminProduct_findAll.action?page=<s:property value="pageBean.page-1"/>">previous
								page</a>|
							</s:if> <s:if test="pageBean.page!=pageBean.totalPage">
							<a
								href="${pageContext.request.contextPath}/adminProduct_findAll.action?page=<s:property value="pageBean.page+1"/>">next
								page</a>|
								<a
								href="${pageContext.request.contextPath}/adminProduct_findAll.action?page=<s:property value="pageBean.totalPage"/>">last
								page</a>
						</s:if>
					</td>
				</tr>

			</TBODY>
		</table>
	</form>
</body>
</HTML>

