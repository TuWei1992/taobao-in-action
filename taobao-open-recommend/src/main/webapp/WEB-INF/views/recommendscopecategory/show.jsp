<%@page import="com.dream.recommend.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=RecommendScopeCategory.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<form:form modelAttribute="recommendscopecategory"  >
		<input type="button" value="返回列表" onclick="window.location='${ctx}/recommendscopecategory'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<input type="hidden" id="id" name="id" value="${recommendScopeCategory.id}"/>
	
		<table class="formTable">
			<tr>	
				<td class="tdLabel"><%=RecommendScopeCategory.ALIAS_SHOP_ID%></td>	
				<td><c:out value='${recommendScopeCategory.shopId}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=RecommendScopeCategory.ALIAS_CAT_ID%></td>	
				<td><c:out value='${recommendScopeCategory.catId}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=RecommendScopeCategory.ALIAS_CAT_NAME%></td>	
				<td><c:out value='${recommendScopeCategory.catName}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=RecommendScopeCategory.ALIAS_LAST_MODIFIED_TIME%></td>	
				<td><c:out value='${recommendScopeCategory.lastModifiedTimeString}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=RecommendScopeCategory.ALIAS_LAST_MODIFIED_BY%></td>	
				<td><c:out value='${recommendScopeCategory.lastModifiedBy}'/></td>
			</tr>
		</table>
	</form:form>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>