<%@page import="com.dream.recommend.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=RecommendStatus.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<form:form modelAttribute="recommendstatus"  >
		<input type="button" value="返回列表" onclick="window.location='${ctx}/recommendstatus'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<input type="hidden" id="shopId" name="shopId" value="${recommendStatus.shopId}"/>
	
		<table class="formTable">
			<tr>	
				<td class="tdLabel"><%=RecommendStatus.ALIAS_STATUS%></td>	
				<td><c:out value='${recommendStatus.status}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=RecommendStatus.ALIAS_LAST_MODIFIED_TIME%></td>	
				<td><c:out value='${recommendStatus.lastModifiedTimeString}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=RecommendStatus.ALIAS_LAST_MODIFIED_BY%></td>	
				<td><c:out value='${recommendStatus.lastModifiedBy}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=RecommendStatus.ALIAS_UPDATED_TIME%></td>	
				<td><c:out value='${recommendStatus.updatedTimeString}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=RecommendStatus.ALIAS_UPDATED_USER_ID%></td>	
				<td><c:out value='${recommendStatus.updatedUserId}'/></td>
			</tr>
		</table>
	</form:form>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>