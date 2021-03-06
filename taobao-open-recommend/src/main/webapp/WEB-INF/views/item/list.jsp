<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<body>
	<h2>List</h2>
	<h3>On Sale List</h3>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>编号</th><th>价格</th><th>审批状态</th><th>商品标题</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${onsale}" var="item">
			<tr>
				<td>${item.numIid}</td>
				<td>${item.price}</td>
				<td>${item.approveStatus}</td>
				<td>${item.title}</td>
				<td><a href="${ctx}/item/detail/${item.numIid}">详情</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	
	<h3>Inventory List</h3>
	
	
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>编号</th><th>价格</th><th>审批状态</th><th>商品标题</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${inventory}" var="item">
			<tr>
				<td>${item.numIid}</td>
				<td>${item.price}</td>
				<td>${item.approveStatus}</td>
				<td>${item.title}</td>
				<td><a href="${ctx}/item/detail/${item.numIid}">详情</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>
