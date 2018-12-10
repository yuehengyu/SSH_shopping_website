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
	function showDetail(oid) {
		//get button object
		var btn=document.getElementById("btn"+oid);
		var div1=document.getElementById("div"+oid);
		if(btn.value=="order detail"){
			//use ajax 1.create object
			var xhr = createXmlHttp();
			//2. set listener
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						div1.innerHTML = xhr.responseText;
					}
				}
			}
			//3.open url
			xhr.open("GET",
					"${pageContext.request.contextPath}/adminOrder_findOrderItem.action?time="
							+ new Date().getTime() + "&oid=" + oid, true);

			//4.send request
			xhr.send(null);
			btn.value="Close";
		}else{
			btn.value="order detail";
			div1.innerHTML="";
		}
		
	}

	function createXmlHttp() {
		var xmlHTttp;
		try {
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try {
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
				}
			}
		}
		return xmlHttp;
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
							of Orders</strong></td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

								<td align="center" width="5%">Number</td>
								<td align="center" width="5%">Order Number</td>
								<td align="center" width="5%">Total Price</td>
								<td align="center" width="5%">Receiver</td>
								<td align="center" width="5%">State</td>
								<td width="*" align="center">Order detail</td>
							</tr>
							<s:iterator var="order" value="pageBean.list" status="status">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="5%"><s:property value="#status.count" /></td>

									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><s:property value="#order.oid" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><s:property value="#order.total" /></td>

									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><s:property value="#order.name" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><s:if test="#order.state==1">Not paying</s:if>
										<s:if test="#order.state==2">
											<a href="${pageContext.request.contextPath}/adminOrder_updateState.action?oid=<s:property value="#order.oid" />"><font color="blue">Ready to deliver</font></a>
										</s:if> <s:if test="#order.state==3">Shipped</s:if> <s:if
											test="#order.state==4">Completed</s:if></td>
									<td align="center" style="HEIGHT: 22px"><input
										id="btn<s:property value="#order.oid" />" type="button"
										value="order detail" onclick="showDetail(<s:property value="#order.oid" />)" />
										<div id="div<s:property value="#order.oid" />"></div></td>
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
								href="${pageContext.request.contextPath}/adminOrder_findAll.action?page=1">first
								page</a>|
								<a
								href="${pageContext.request.contextPath}/adminOrder_findAll.action?page=<s:property value="pageBean.page-1"/>">previous
								page</a>|
							</s:if> <s:if test="pageBean.page!=pageBean.totalPage">
							<a
								href="${pageContext.request.contextPath}/adminOrder_findAll.action?page=<s:property value="pageBean.page+1"/>">next
								page</a>|
								<a
								href="${pageContext.request.contextPath}/adminOrder_findAll.action?page=<s:property value="pageBean.totalPage"/>">last
								page</a>
						</s:if>
					</td>
				</tr>

			</TBODY>
		</table>
	</form>
</body>
</HTML>

