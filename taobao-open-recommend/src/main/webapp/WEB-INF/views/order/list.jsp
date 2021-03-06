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
	<h3>Trade List</h3>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>编号</th><th>支付时间</th><th>关闭时间</th><th>创建时间</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${trades}" var="trade">
			<tr>
				<td>${trade.tid}</td>
				<td>${trade.payTime}</td>
				<td>${trade.endTime}</td>
				<td>${trade.created}</td>
				<td><a href="${ctx}/order/detail/${trade.tid}">详情</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>
