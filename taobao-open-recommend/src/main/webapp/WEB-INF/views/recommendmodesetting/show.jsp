<%@page import="com.dream.recommend.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=RecommendModeSetting.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<form:form modelAttribute="recommendmodesetting"  >
		<input type="button" value="返回列表" onclick="window.location='${ctx}/recommendmodesetting'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<input type="hidden" id="shopId" name="shopId" value="${recommendModeSetting.shopId}"/>
	
		<table class="formTable">
			<tr>	
				<td class="tdLabel"><%=RecommendModeSetting.ALIAS_IS_SALED_FIRST%></td>	
				<td><c:out value='${recommendModeSetting.isSaledFirst}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=RecommendModeSetting.ALIAS_SALED_NUM%></td>	
				<td><c:out value='${recommendModeSetting.saledNum}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=RecommendModeSetting.ALIAS_IS_STORAGE_FILTERED%></td>	
				<td><c:out value='${recommendModeSetting.isStorageFiltered}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=RecommendModeSetting.ALIAS_STORAGE_NUM%></td>	
				<td><c:out value='${recommendModeSetting.storageNum}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=RecommendModeSetting.ALIAS_IS_OFFSHELVES_FILTERED%></td>	
				<td><c:out value='${recommendModeSetting.isOffshelvesFiltered}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=RecommendModeSetting.ALIAS_OFFSHELVES_TIME%></td>	
				<td><c:out value='${recommendModeSetting.offshelvesTime}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=RecommendModeSetting.ALIAS_IS_ONSHELVES_FILTERED%></td>	
				<td><c:out value='${recommendModeSetting.isOnshelvesFiltered}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=RecommendModeSetting.ALIAS_LAST_MODIFIED_BY%></td>	
				<td><c:out value='${recommendModeSetting.lastModifiedBy}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=RecommendModeSetting.ALIAS_LAST_MODIFIED_TIME%></td>	
				<td><c:out value='${recommendModeSetting.lastModifiedTimeString}'/></td>
			</tr>
		</table>
	</form:form>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>