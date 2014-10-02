<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  	<script src="${ctx}/static/js/highlight.js"></script>
    <script src="${ctx}/static/js/main.js"></script>
</head>
<body>
			<div class="row">
                <div class="col-lg-12">
                    <h4 class="page-header">橱窗设置</h4>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-6">
                    <div class="panel panel-default">
						<div class="panel-heading">
                            	宝贝相关信息
                        </div>
                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>
                                        全部商品数
                                    </th>
                                    <th>
                                        在售商品数
                                    </th>
                                    <th>
                                        仓库商品数
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>${all}</td>
                                    <td>${onsale}</td>
                                    <td>${inventory}</td>
                                </tr>                                        
                            </tbody>
                        </table>
						<div class="panel-footer">
                            Panel Footer
                        </div>
					</div>
                </div>
                <!-- /.col-lg-12 -->

                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            自动橱窗设置
                        </div>
                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>
                                        店铺橱窗总数
                                    </th>
                                    <th>
                                        剩余橱窗总数
                                    </th>
                                    <th>
                                        已用橱窗总数
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>${showcase.allCount}</td>
                                    <td>${showcase.remainCount}</td>
                                    <td>${showcase.usedCount}</td>
                                </tr>                                        
                            </tbody>
                        </table>
                        <div class="panel-footer">
                            Panel Footer
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.row -->
			
			<div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            自动橱窗设置
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form role="form" method="post" action="${ctx}/recommendscopesetting/${shop.sid}">
                                        <div class="form-group">
                                            <label>启动自动橱窗</label>
                                            <label class="checkbox-inline">
                                                <input type="checkbox" name="isEnabled" id="isEnabled" value="1" <c:if test="${setting.isEnabled == '1' }">checked</c:if> >
                                            </label>
                                        </div>
                                        <div class="form-group">
                                            <label>宝贝筛选条件</label>
                                            <label class="radio-inline">
                                                <input type="radio" name="scopeType" id="scopeType" value="0" <c:if test="${setting.scopeType == '0' }">checked</c:if> > 推荐出售中的宝贝
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="scopeType" id="scopeType" value="1" <c:if test="${setting.scopeType == '1' }">checked</c:if> >
                                                推荐指定分类中的宝贝
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="scopeType" id="scopeType" value="2" <c:if test="${setting.scopeType == '2' }">checked</c:if> >
                                                推荐包含指定关键字的宝贝
                                            </label>
                                        </div>
                                        <button type="submit" class="btn btn-primary">保存设置</button>
                                        <button type="reset" class="btn btn-default">重置橱窗</button>
                                    </form>
                                </div>
                                <!-- /.col-lg-12 (nested) -->
                            </div>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
						<div class="panel-footer">
                            Panel Footer
                        </div>
					</div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
</body>
</html>

