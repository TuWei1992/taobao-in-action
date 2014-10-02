<%@page import="com.dream.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=Auth.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<form:form modelAttribute="auth"  >
		<input type="button" value="返回列表" onclick="window.location='${ctx}/auth'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<input type="hidden" id="userId" name="userId" value="${auth.userId}"/>
	
		<table class="formTable">
			<tr>	
				<td class="tdLabel"><%=Auth.ALIAS_TB_USER_NICK%></td>	
				<td><c:out value='${auth.tbUserNick}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Auth.ALIAS_IS_LOCK%></td>	
				<td><c:out value='${auth.isLock}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Auth.ALIAS_IS_ENABLE%></td>	
				<td><c:out value='${auth.isEnable}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Auth.ALIAS_TAOBAO_USER_NICK%></td>	
				<td><c:out value='${auth.taobaoUserNick}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Auth.ALIAS_TAOBAO_USER_ID%></td>	
				<td><c:out value='${auth.taobaoUserId}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Auth.ALIAS_SUB_TAOBAO_USER_ID%></td>	
				<td><c:out value='${auth.subTaobaoUserId}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Auth.ALIAS_SUB_TAOBAO_USER_NICK%></td>	
				<td><c:out value='${auth.subTaobaoUserNick}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Auth.ALIAS_REFRESHED_TIME%></td>	
				<td><c:out value='${auth.refreshedTimeString}'/></td>
			</tr>
		</table>
	</form:form>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>