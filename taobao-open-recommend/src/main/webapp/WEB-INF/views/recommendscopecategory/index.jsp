<%@page import="com.dream.recommend.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=RecommendScopeCategory.TABLE_ALIAS%> 维护</title>
	
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
					<td class="tdLabel"><%=RecommendScopeCategory.ALIAS_SHOP_ID%></td>		
					<td>
						<input value="${query.shopId}" id="shopId" name="shopId" maxlength="10"  class=""/>
					</td>
					<td class="tdLabel"><%=RecommendScopeCategory.ALIAS_CAT_ID%></td>		
					<td>
						<input value="${query.catId}" id="catId" name="catId" maxlength="10"  class=""/>
					</td>
					<td class="tdLabel"><%=RecommendScopeCategory.ALIAS_CAT_NAME%></td>		
					<td>
						<input value="${query.catName}" id="catName" name="catName" maxlength="30"  class=""/>
					</td>
					<td class="tdLabel"><%=RecommendScopeCategory.ALIAS_LAST_MODIFIED_TIME%></td>		
					<td>
						<input value="<fmt:formatDate value='${query.lastModifiedTimeBegin}' pattern='<%=RecommendScopeCategory.FORMAT_LAST_MODIFIED_TIME%>'/>" onclick="WdatePicker({dateFmt:'<%=RecommendScopeCategory.FORMAT_LAST_MODIFIED_TIME%>'})" id="lastModifiedTimeBegin" name="lastModifiedTimeBegin"   />
						<input value="<fmt:formatDate value='${query.lastModifiedTimeEnd}' pattern='<%=RecommendScopeCategory.FORMAT_LAST_MODIFIED_TIME%>'/>" onclick="WdatePicker({dateFmt:'<%=RecommendScopeCategory.FORMAT_LAST_MODIFIED_TIME%>'})" id="lastModifiedTimeEnd" name="lastModifiedTimeEnd"   />
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel"><%=RecommendScopeCategory.ALIAS_LAST_MODIFIED_BY%></td>		
					<td>
						<input value="${query.lastModifiedBy}" id="lastModifiedBy" name="lastModifiedBy" maxlength="19"  class="validate-integer "/>
					</td>
				</tr>	
			</table>
		</fieldset>
		<div class="handleControl">
			<input type="submit" class="stdButton" style="width:80px" value="查询" onclick="getReferenceForm(this).action='${ctx}/recommendscopecategory'"/>
			<input type="button" class="stdButton" style="width:80px" value="新增" onclick="window.location = '${ctx}/recommendscopecategory/new'"/>
			<input type="button" class="stdButton" style="width:80px" value="删除" onclick="doRestBatchDelete('${ctx}/recommendscopecategory','items',document.forms.queryForm)"/>
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
				<th sortColumn="SHOP_ID" ><%=RecommendScopeCategory.ALIAS_SHOP_ID%></th>
				<th sortColumn="CAT_ID" ><%=RecommendScopeCategory.ALIAS_CAT_ID%></th>
				<th sortColumn="CAT_NAME" ><%=RecommendScopeCategory.ALIAS_CAT_NAME%></th>
				<th sortColumn="LAST_MODIFIED_TIME" ><%=RecommendScopeCategory.ALIAS_LAST_MODIFIED_TIME%></th>
				<th sortColumn="LAST_MODIFIED_BY" ><%=RecommendScopeCategory.ALIAS_LAST_MODIFIED_BY%></th>
	
				<th>操作</th>
			  </tr>
			  
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.result}" var="item" varStatus="status">
		  	  
			  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.thisPageFirstElementNumber + status.index}</td>
				<td><input type="checkbox" name="items" value="${item.id}"></td>
				
				<td><c:out value='${item.shopId}'/>&nbsp;</td>
				<td><c:out value='${item.catId}'/>&nbsp;</td>
				<td><c:out value='${item.catName}'/>&nbsp;</td>
				<td><c:out value='${item.lastModifiedTimeString}'/>&nbsp;</td>
				<td><c:out value='${item.lastModifiedBy}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/recommendscopecategory/${item.id}">查看</a>&nbsp;&nbsp;
					<a href="${ctx}/recommendscopecategory/${item.id}/edit">修改</a>&nbsp;&nbsp;
					<a href="${ctx}/recommendscopecategory/${item.id}" onclick="doRestDelete(this,'你确认删除?');return false;">删除</a>
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
