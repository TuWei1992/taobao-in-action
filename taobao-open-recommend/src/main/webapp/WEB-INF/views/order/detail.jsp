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
		<input type="hidden" name="id" value="${trade.tid}"/>
		<fieldset>
			<legend><small>交易详情</small></legend>
			<div class="control-group">
				<label for="name" class="control-label">支付时间:</label>
				<div class="controls">
					${trade.payTime}
				</div>
			</div>
			<div class="control-group">
				<label for="plainPassword" class="control-label">关闭时间:</label>
				<div class="controls">
					${trade.endTime}
				</div>
			</div>
			<div class="control-group">
				<label for="confirmPassword" class="control-label">创建时间:</label>
				<div class="controls">
					${trade.created}
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