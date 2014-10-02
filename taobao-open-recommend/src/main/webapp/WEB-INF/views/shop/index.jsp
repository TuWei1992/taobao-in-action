<%@page import="com.dream.shop.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=Shop.TABLE_ALIAS%> 维护</title>
	
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
					<td class="tdLabel"><%=Shop.ALIAS_CID%></td>		
					<td>
						<input value="${query.cid}" id="cid" name="cid" maxlength="19"  class="validate-integer "/>
					</td>
					<td class="tdLabel"><%=Shop.ALIAS_NICK%></td>		
					<td>
						<input value="${query.nick}" id="nick" name="nick" maxlength="20"  class=""/>
					</td>
					<td class="tdLabel"><%=Shop.ALIAS_TITLE%></td>		
					<td>
						<input value="${query.title}" id="title" name="title" maxlength="20"  class=""/>
					</td>
					<td class="tdLabel"><%=Shop.ALIAS_DESC%></td>		
					<td>
						<input value="${query.desc}" id="desc" name="desc" maxlength="60"  class=""/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel"><%=Shop.ALIAS_BULLETIN%></td>		
					<td>
						<input value="${query.bulletin}" id="bulletin" name="bulletin" maxlength="100"  class=""/>
					</td>
					<td class="tdLabel"><%=Shop.ALIAS_PIC_PATH%></td>		
					<td>
						<input value="${query.picPath}" id="picPath" name="picPath" maxlength="100"  class=""/>
					</td>
					<td class="tdLabel"><%=Shop.ALIAS_CREATED%></td>		
					<td>
						<input value="<fmt:formatDate value='${query.createdBegin}' pattern='<%=Shop.FORMAT_CREATED%>'/>" onclick="WdatePicker({dateFmt:'<%=Shop.FORMAT_CREATED%>'})" id="createdBegin" name="createdBegin"   />
						<input value="<fmt:formatDate value='${query.createdEnd}' pattern='<%=Shop.FORMAT_CREATED%>'/>" onclick="WdatePicker({dateFmt:'<%=Shop.FORMAT_CREATED%>'})" id="createdEnd" name="createdEnd"   />
					</td>
					<td class="tdLabel"><%=Shop.ALIAS_MODIFIED%></td>		
					<td>
						<input value="<fmt:formatDate value='${query.modifiedBegin}' pattern='<%=Shop.FORMAT_MODIFIED%>'/>" onclick="WdatePicker({dateFmt:'<%=Shop.FORMAT_MODIFIED%>'})" id="modifiedBegin" name="modifiedBegin"   />
						<input value="<fmt:formatDate value='${query.modifiedEnd}' pattern='<%=Shop.FORMAT_MODIFIED%>'/>" onclick="WdatePicker({dateFmt:'<%=Shop.FORMAT_MODIFIED%>'})" id="modifiedEnd" name="modifiedEnd"   />
					</td>
				</tr>	
			</table>
		</fieldset>
		<div class="handleControl">
			<input type="submit" class="stdButton" style="width:80px" value="查询" onclick="getReferenceForm(this).action='${ctx}/shop'"/>
			<input type="button" class="stdButton" style="width:80px" value="新增" onclick="window.location = '${ctx}/shop/new'"/>
			<input type="button" class="stdButton" style="width:80px" value="删除" onclick="doRestBatchDelete('${ctx}/shop','items',document.forms.queryForm)"/>
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
				<th sortColumn="CID" ><%=Shop.ALIAS_CID%></th>
				<th sortColumn="NICK" ><%=Shop.ALIAS_NICK%></th>
				<th sortColumn="TITLE" ><%=Shop.ALIAS_TITLE%></th>
				<th sortColumn="DESC" ><%=Shop.ALIAS_DESC%></th>
				<th sortColumn="BULLETIN" ><%=Shop.ALIAS_BULLETIN%></th>
				<th sortColumn="PIC_PATH" ><%=Shop.ALIAS_PIC_PATH%></th>
				<th sortColumn="CREATED" ><%=Shop.ALIAS_CREATED%></th>
				<th sortColumn="MODIFIED" ><%=Shop.ALIAS_MODIFIED%></th>
	
				<th>操作</th>
			  </tr>
			  
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.result}" var="item" varStatus="status">
		  	  
			  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.thisPageFirstElementNumber + status.index}</td>
				<td><input type="checkbox" name="items" value="${item.sid}"></td>
				
				<td><c:out value='${item.cid}'/>&nbsp;</td>
				<td><c:out value='${item.nick}'/>&nbsp;</td>
				<td><c:out value='${item.title}'/>&nbsp;</td>
				<td><c:out value='${item.desc}'/>&nbsp;</td>
				<td><c:out value='${item.bulletin}'/>&nbsp;</td>
				<td><c:out value='${item.picPath}'/>&nbsp;</td>
				<td><c:out value='${item.createdString}'/>&nbsp;</td>
				<td><c:out value='${item.modifiedString}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/shop/${item.sid}">查看</a>&nbsp;&nbsp;
					<a href="${ctx}/shop/${item.sid}/edit">修改</a>&nbsp;&nbsp;
					<a href="${ctx}/shop/${item.sid}" onclick="doRestDelete(this,'你确认删除?');return false;">删除</a>
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
