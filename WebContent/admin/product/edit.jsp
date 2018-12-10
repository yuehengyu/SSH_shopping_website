<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath}/css/Style1.css"
	type="text/css" rel="stylesheet">
</HEAD>

<body>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/adminProduct_update.action"
		method="post" enctype="multipart/form-data">
		<input type="hidden" name="pid" value='<s:property value="model.pid"/>' />
		<input type="hidden" name="image" value='<s:property value="model.image"/>' />
		&nbsp;
		<table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
					height="26"><STRONG>Edit Products</STRONG></td>
			</tr>

			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					Name of Products：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="pname" value='<s:property value="model.pname"/>' class="bg" />
				</td>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					Belongs which Second Category：</td>
				<td class="ta_01" bgColor="#ffffff"><select name="csid">
						<s:iterator value="csList" var="cs">
							<option value="<s:property value="#cs.csid"/>"
								<s:if test="#cs.csid==model.categorySecond.csid">selected</s:if>><s:property
									value="#cs.csname" /></option>
						</s:iterator>
				</select></td>
			</tr>

			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					Market price：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="number"
					name="market_price" value="<s:property value="model.market_price"/>" class="bg" /></td>

				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					Shop price：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="number"
					name="shop_price" value="<s:property value="model.shop_price"/>" class="bg" /></td>
			</tr>

			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					Hot/Popular：</td>
				<td class="ta_01" bgColor="#ffffff" colspan="3"><select
					name="is_hot">
						<option value="1" <s:if test="model.is_hot==1">selected</s:if>>Yes</option>
						<option value="0" <s:if test="model.is_hot==0">selected</s:if>>No</option>
				</select></td>
			</tr>

			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					Image：</td>
				<td class="ta_01" bgColor="#ffffff" colspan="3"><input
					type="file" name="upload" /></td>
			</tr>

			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					Describe：</td>
				<td class="ta_01" bgColor="#ffffff"><textarea rows="10"
						cols="40" name="pdesc"><s:property value="model.pdesc"/></textarea></td>
			</tr>

			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center"
					bgColor="#f5fafe" colSpan="4">
					<button type="submit" value="Submit" class="button_ok">Submit</button>
					<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
					<button type="reset" value="Reset" class="button_cancel">Reset</button>

					<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT> <INPUT
					class="button_ok" type="button" onclick="history.go(-1)"
					value="Return" /> <span id="Label1"></span>
				</td>
			</tr>
		</table>
	</form>
</body>
</HTML>