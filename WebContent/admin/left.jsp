<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>菜单</title>
<link href="${pageContext.request.contextPath}/css/left.css" rel="stylesheet" type="text/css"/>
<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" />
</head>
<body>
<table width="100" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="12"></td>
  </tr>
</table>
<table width="100%" border="0">
  <tr>
    <td>
<div class="dtree">

	<a href="javascript: d.openAll();">Open All</a> | <a href="javascript: d.closeAll();">Close All</a>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
	<script type="text/javascript">
		<!--
		d = new dTree('d');
		d.add('01',-1,'Menu');
		d.add('0101','01','User');
		d.add('010101','0101','User manage','','','mainFrame');
		d.add('0102','01','Primary Classification');
		d.add('010201','0102','Primary manage','${pageContext.request.contextPath}/adminCategory_findAll.action','','mainFrame');
		d.add('0103','01','second Classification');
		d.add('010301','0103','Second manage','${pageContext.request.contextPath}/adminCategorySecond_findAll.action?page=1 ','','mainFrame');
		d.add('0104','01','Product Manage');
		d.add('010401','0104','Product manage','${pageContext.request.contextPath}/adminProduct_findAll.action?page=1 ','','mainFrame');
		d.add('0105','01','Order Manage');
		d.add('010501','0105','Order manage','${pageContext.request.contextPath}/adminOrder_findAll.action?page=1 ','','mainFrame');
		document.write(d);
		//-->
	</script>
</div>	</td>
  </tr>
</table>
</body>
</html>
