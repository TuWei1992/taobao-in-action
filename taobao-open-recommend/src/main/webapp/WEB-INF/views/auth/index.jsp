<%@page import="com.dream.auth.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=Auth.TABLE_ALIAS%> 维护</title>
	
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
					<td class="tdLabel"><%=Auth.ALIAS_TB_USER_NICK%></td>		
					<td>
						<input value="${query.tbUserNick}" id="tbUserNick" name="tbUserNick" maxlength="60"  class=""/>
					</td>
					<td class="tdLabel"><%=Auth.ALIAS_IS_LOCK%></td>		
					<td>
						<input value="${query.isLock}" id="isLock" name="isLock" maxlength="1"  class=""/>
					</td>
					<td class="tdLabel"><%=Auth.ALIAS_IS_ENABLE%></td>		
					<td>
						<input value="${query.isEnable}" id="isEnable" name="isEnable" maxlength="1"  class=""/>
					</td>
					<td class="tdLabel"><%=Auth.ALIAS_TAOBAO_USER_NICK%></td>		
					<td>
						<input value="${query.taobaoUserNick}" id="taobaoUserNick" name="taobaoUserNick" maxlength="20"  class=""/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel"><%=Auth.ALIAS_TAOBAO_USER_ID%></td>		
					<td>
						<input value="${query.taobaoUserId}" id="taobaoUserId" name="taobaoUserId" maxlength="30"  class=""/>
					</td>
					<td class="tdLabel"><%=Auth.ALIAS_SUB_TAOBAO_USER_ID%></td>		
					<td>
						<input value="${query.subTaobaoUserId}" id="subTaobaoUserId" name="subTaobaoUserId" maxlength="20"  class=""/>
					</td>
					<td class="tdLabel"><%=Auth.ALIAS_SUB_TAOBAO_USER_NICK%></td>		
					<td>
						<input value="${query.subTaobaoUserNick}" id="subTaobaoUserNick" name="subTaobaoUserNick" maxlength="30"  class=""/>
					</td>
					<td class="tdLabel"><%=Auth.ALIAS_REFRESHED_TIME%></td>		
					<td>
						<input value="<fmt:formatDate value='${query.refreshedTimeBegin}' pattern='<%=Auth.FORMAT_REFRESHED_TIME%>'/>" onclick="WdatePicker({dateFmt:'<%=Auth.FORMAT_REFRESHED_TIME%>'})" id="refreshedTimeBegin" name="refreshedTimeBegin"   />
						<input value="<fmt:formatDate value='${query.refreshedTimeEnd}' pattern='<%=Auth.FORMAT_REFRESHED_TIME%>'/>" onclick="WdatePicker({dateFmt:'<%=Auth.FORMAT_REFRESHED_TIME%>'})" id="refreshedTimeEnd" name="refreshedTimeEnd"   />
					</td>
				</tr>	
			</table>
		</fieldset>
		<div class="handleControl">
			<input type="submit" class="stdButton" style="width:80px" value="查询" onclick="getReferenceForm(this).action='${ctx}/auth'"/>
			<input type="button" class="stdButton" style="width:80px" value="新增" onclick="window.location = '${ctx}/auth/new'"/>
			<input type="button" class="stdButton" style="width:80px" value="删除" onclick="doRestBatchDelete('${ctx}/auth','items',document.forms.queryForm)"/>
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
				<th sortColumn="TB_USER_NICK" ><%=Auth.ALIAS_TB_USER_NICK%></th>
				<th sortColumn="IS_LOCK" ><%=Auth.ALIAS_IS_LOCK%></th>
				<th sortColumn="IS_ENABLE" ><%=Auth.ALIAS_IS_ENABLE%></th>
				<th sortColumn="TAOBAO_USER_NICK" ><%=Auth.ALIAS_TAOBAO_USER_NICK%></th>
				<th sortColumn="TAOBAO_USER_ID" ><%=Auth.ALIAS_TAOBAO_USER_ID%></th>
				<th sortColumn="SUB_TAOBAO_USER_ID" ><%=Auth.ALIAS_SUB_TAOBAO_USER_ID%></th>
				<th sortColumn="SUB_TAOBAO_USER_NICK" ><%=Auth.ALIAS_SUB_TAOBAO_USER_NICK%></th>
				<th sortColumn="REFRESHED_TIME" ><%=Auth.ALIAS_REFRESHED_TIME%></th>
	
				<th>操作</th>
			  </tr>
			  
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.result}" var="item" varStatus="status">
		  	  
			  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.thisPageFirstElementNumber + status.index}</td>
				<td><input type="checkbox" name="items" value="${item.userId}"></td>
				
				<td><c:out value='${item.tbUserNick}'/>&nbsp;</td>
				<td><c:out value='${item.isLock}'/>&nbsp;</td>
				<td><c:out value='${item.isEnable}'/>&nbsp;</td>
				<td><c:out value='${item.taobaoUserNick}'/>&nbsp;</td>
				<td><c:out value='${item.taobaoUserId}'/>&nbsp;</td>
				<td><c:out value='${item.subTaobaoUserId}'/>&nbsp;</td>
				<td><c:out value='${item.subTaobaoUserNick}'/>&nbsp;</td>
				<td><c:out value='${item.refreshedTimeString}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/auth/${item.userId}">查看</a>&nbsp;&nbsp;
					<a href="${ctx}/auth/${item.userId}/edit">修改</a>&nbsp;&nbsp;
					<a href="${ctx}/auth/${item.userId}" onclick="doRestDelete(this,'你确认删除?');return false;">删除</a>
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
