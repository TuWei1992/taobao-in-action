<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>

<body>
	<h2>Detail</h2>
	<form id="inputForm" action="${ctx}/profile" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${detail.numIid}"/>
		<fieldset>
			<legend><small>商品详情</small></legend>
			<div class="control-group">
				<label for="name" class="control-label">编号:</label>
				<div class="controls">
					${detail.numIid}
				</div>
			</div>
			<div class="control-group">
				<label for="plainPassword" class="control-label">标题:</label>
				<div class="controls">
					${detail.title}
				</div>
			</div>
			<div class="control-group">
				<label for="confirmPassword" class="control-label">价格:</label>
				<div class="controls">
					${detail.price}
				</div>
			</div>
			<div class="control-group">
				<label for="confirmPassword" class="control-label">描述信息:</label>
				<div class="controls">
					${detail.descModules}
				</div>
			</div>
			<div class="control-group">
				<label for="confirmPassword" class="control-label">卖点:</label>
				<div class="controls">
					${detail.sellPoint}
				</div>
			</div>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
			</div>
		</fieldset>
	</form>
</body>
</html>