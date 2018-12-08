<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript">
			function addCategorySecond(){
				window.location.href = "${pageContext.request.contextPath}/adminCategorySecond_addPage.action";
			}
		</script>
	</HEAD>
	<body>
		<br>
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>List of Primary Classification</strong>
						</td>
					</tr>
					<tr>
						<td class="ta_01" align="right">
							<button type="button" id="add" name="add" value="AddCategory" class="button_add" onclick="addCategorySecond()">
Add
</button>

						</td>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="18%">
										Number
									</td>
									<td align="center" width="17%">
										Name of Second Classification
									</td>
									<td width="7%" align="center">
										Edit
									</td>
									<td width="7%" align="center">
										Delete
									</td>
								</tr>
								<s:iterator var="cs" value="pageBean.list" status="status">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="18%">
												<s:property value="#status.count"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												<s:property value="#cs.csname"/>
											</td>
											<td align="center" style="HEIGHT: 22px">
												<a href="${pageContext.request.contextPath}/adminCategorySecond_edit.action?csid=<s:property value="#cs.csid"/>">
													<img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">
												</a>
											</td>
									
											<td align="center" style="HEIGHT: 22px">
												<a href="${pageContext.request.contextPath}/adminCategorySecond_delete.action?csid=<s:property value="#cs.csid"/>">
													<img src="${pageContext.request.contextPath}/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
												</a>
											</td>
										</tr>
									</s:iterator>	
							</table>
						</td>
					</tr>
					
					<tr align="center">
						<td class="ta_01" align="center" bgColor="#afd1f3">
							Current Page:<s:property value="pageBean.page"/>/<s:property value="pageBean.totalPage"/>&nbsp;&nbsp;&nbsp;&nbsp;
							<s:if test="pageBean.page!=1">
								<a href="${pageContext.request.contextPath}/adminCategorySecond_findAll.action?page=1">first page</a>|
								<a href="${pageContext.request.contextPath}/adminCategorySecond_findAll.action?page=<s:property value="pageBean.page-1"/>">previous page</a>|
							</s:if>
							<s:if test="pageBean.page!=pageBean.totalPage">
								<a href="${pageContext.request.contextPath}/adminCategorySecond_findAll.action?page=<s:property value="pageBean.page+1"/>">next page</a>|
								<a href="${pageContext.request.contextPath}/adminCategorySecond_findAll.action?page=<s:property value="pageBean.totalPage"/>">last page</a>
							</s:if>
						</td>
					</tr>
					
				</TBODY>
			</table>
		</form>
	</body>
</HTML>

