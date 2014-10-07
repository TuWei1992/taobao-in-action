<%@page import="com.dream.recommend.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=RecommendStatus.TABLE_ALIAS%> 维护</title>
	
	<script src="${ctx}/scripts/rest.js" ></script>
	<link href="<c:url value="/widgets/simpletable/simpletable.css"/>" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/widgets/simpletable/simpletable.js"/>"></script>
	
	<script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('queryForm',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
	</script>
</rapid:override>

<rapid:override name="content">
	<form id="queryForm" name="queryForm" method="get" style="display: inline;">
	<div class="queryPanel">
		<fieldset>
			<legend>搜索</legend>
			<table>
				<tr>	
					<td class="tdLabel"><%=RecommendStatus.ALIAS_STATUS%></td>		
					<td>
						<input value="${query.status}" id="status" name="status" maxlength="1"  class=""/>
					</td>
					<td class="tdLabel"><%=RecommendStatus.ALIAS_LAST_MODIFIED_TIME%></td>		
					<td>
						<input value="<fmt:formatDate value='${query.lastModifiedTimeBegin}' pattern='<%=RecommendStatus.FORMAT_LAST_MODIFIED_TIME%>'/>" onclick="WdatePicker({dateFmt:'<%=RecommendStatus.FORMAT_LAST_MODIFIED_TIME%>'})" id="lastModifiedTimeBegin" name="lastModifiedTimeBegin"   />
						<input value="<fmt:formatDate value='${query.lastModifiedTimeEnd}' pattern='<%=RecommendStatus.FORMAT_LAST_MODIFIED_TIME%>'/>" onclick="WdatePicker({dateFmt:'<%=RecommendStatus.FORMAT_LAST_MODIFIED_TIME%>'})" id="lastModifiedTimeEnd" name="lastModifiedTimeEnd"   />
					</td>
					<td class="tdLabel"><%=RecommendStatus.ALIAS_LAST_MODIFIED_BY%></td>		
					<td>
						<input value="${query.lastModifiedBy}" id="lastModifiedBy" name="lastModifiedBy" maxlength="19"  class="validate-integer "/>
					</td>
					<td class="tdLabel"><%=RecommendStatus.ALIAS_UPDATED_TIME%></td>		
					<td>
						<input value="<fmt:formatDate value='${query.updatedTimeBegin}' pattern='<%=RecommendStatus.FORMAT_UPDATED_TIME%>'/>" onclick="WdatePicker({dateFmt:'<%=RecommendStatus.FORMAT_UPDATED_TIME%>'})" id="updatedTimeBegin" name="updatedTimeBegin"   />
						<input value="<fmt:formatDate value='${query.updatedTimeEnd}' pattern='<%=RecommendStatus.FORMAT_UPDATED_TIME%>'/>" onclick="WdatePicker({dateFmt:'<%=RecommendStatus.FORMAT_UPDATED_TIME%>'})" id="updatedTimeEnd" name="updatedTimeEnd"   />
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel"><%=RecommendStatus.ALIAS_UPDATED_USER_ID%></td>		
					<td>
						<input value="${query.updatedUserId}" id="updatedUserId" name="updatedUserId" maxlength="19"  class="validate-integer "/>
					</td>
				</tr>	
			</table>
		</fieldset>
		<div class="handleControl">
			<input type="submit" class="stdButton" style="width:80px" value="查询" onclick="getReferenceForm(this).action='${ctx}/recommendstatus'"/>
			<input type="button" class="stdButton" style="width:80px" value="新增" onclick="window.location = '${ctx}/recommendstatus/new'"/>
			<input type="button" class="stdButton" style="width:80px" value="删除" onclick="doRestBatchDelete('${ctx}/recommendstatus','items',document.forms.queryForm)"/>
		<div>
	
	</div>
	
	<div class="gridTable">
	
		<simpletable:pageToolbar page="${page}">
		显示在这里是为了提示你如何自定义表头,可修改模板删除此行
		</simpletable:pageToolbar>
	
		<table width="100%"  border="0" cellspacing="0" class="gridBody">
		  <thead>
			  
			  <tr>
				<th style="width:1px;"> </th>
				<th style="width:1px;"><input type="checkbox" onclick="setAllCheckboxState('items',this.checked)"></th>
				
				<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
				<th sortColumn="STATUS" ><%=RecommendStatus.ALIAS_STATUS%></th>
				<th sortColumn="LAST_MODIFIED_TIME" ><%=RecommendStatus.ALIAS_LAST_MODIFIED_TIME%></th>
				<th sortColumn="LAST_MODIFIED_BY" ><%=RecommendStatus.ALIAS_LAST_MODIFIED_BY%></th>
				<th sortColumn="UPDATED_TIME" ><%=RecommendStatus.ALIAS_UPDATED_TIME%></th>
				<th sortColumn="UPDATED_USER_ID" ><%=RecommendStatus.ALIAS_UPDATED_USER_ID%></th>
	
				<th>操作</th>
			  </tr>
			  
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.result}" var="item" varStatus="status">
		  	  
			  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.thisPageFirstElementNumber + status.index}</td>
				<td><input type="checkbox" name="items" value="${item.shopId}"></td>
				
				<td><c:out value='${item.status}'/>&nbsp;</td>
				<td><c:out value='${item.lastModifiedTimeString}'/>&nbsp;</td>
				<td><c:out value='${item.lastModifiedBy}'/>&nbsp;</td>
				<td><c:out value='${item.updatedTimeString}'/>&nbsp;</td>
				<td><c:out value='${item.updatedUserId}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/recommendstatus/${item.shopId}">查看</a>&nbsp;&nbsp;
					<a href="${ctx}/recommendstatus/${item.shopId}/edit">修改</a>&nbsp;&nbsp;
					<a href="${ctx}/recommendstatus/${item.shopId}" onclick="doRestDelete(this,'你确认删除?');return false;">删除</a>
				</td>
			  </tr>
			  
		  	  </c:forEach>
		  </tbody>
		</table>
	
		<simpletable:pageToolbar page="${page}">
		显示在这里是为了提示你如何自定义表头,可修改模板删除此行
		</simpletable:pageToolbar>
		
	</div>
	</form>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>
