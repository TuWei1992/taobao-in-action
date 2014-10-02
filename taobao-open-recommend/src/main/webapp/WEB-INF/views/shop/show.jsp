<%@page import="com.dream.shop.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=Shop.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<form:form modelAttribute="shop"  >
		<input type="button" value="返回列表" onclick="window.location='${ctx}/shop'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<input type="hidden" id="sid" name="sid" value="${shop.sid}"/>
	
		<table class="formTable">
			<tr>	
				<td class="tdLabel"><%=Shop.ALIAS_CID%></td>	
				<td><c:out value='${shop.cid}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Shop.ALIAS_NICK%></td>	
				<td><c:out value='${shop.nick}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Shop.ALIAS_TITLE%></td>	
				<td><c:out value='${shop.title}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Shop.ALIAS_DESC%></td>	
				<td><c:out value='${shop.desc}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Shop.ALIAS_BULLETIN%></td>	
				<td><c:out value='${shop.bulletin}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Shop.ALIAS_PIC_PATH%></td>	
				<td><c:out value='${shop.picPath}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Shop.ALIAS_CREATED%></td>	
				<td><c:out value='${shop.createdString}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Shop.ALIAS_MODIFIED%></td>	
				<td><c:out value='${shop.modifiedString}'/></td>
			</tr>
		</table>
	</form:form>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>