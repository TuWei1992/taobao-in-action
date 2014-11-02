<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<body>
	<h2>Index</h2>
	<h3><a href="https://oauth.tbsandbox.com/authorize?response_type=code&client_id=1021697746&redirect_uri=http://www.dreamlabs.com:8080/taobao-open-recommend&state=1212&view=web">请求授权用户登录</a></h3>
	<h3><a href="${ctx}/item/list/?id=${param.code}">获取商品列表</a></h3>
	<c:out value="${param.code}"></c:out>
</body>
</html>
