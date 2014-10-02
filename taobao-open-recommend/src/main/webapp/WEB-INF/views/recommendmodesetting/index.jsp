<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<head>
</head>
</head>
<body>
		<div class="row">
               <div class="col-lg-12">
                   <h4 class="page-header">推荐方式设置</h4>
               </div>
               <!-- /.col-lg-12 -->
           </div>
           <!-- /.row -->
		<div class="row">
               <div class="col-lg-12">
                   <div class="panel panel-default">
                       <div class="panel-heading">
                          	 橱窗推荐优先
                       </div>
                       <div class="panel-body">
                            <ul>
                              <li>基础规则：优先推荐即将下架的宝贝</li>
                            </ul>
                       </div>
				</div>
                   <!-- /.panel -->
               </div>
               <!-- /.col-lg-12 -->
       </div>
       <div class="row">
               <div class="col-lg-6">
                   <div class="panel panel-default">
                       <div class="panel-heading">
                          	 橱窗推荐特殊优先
                       </div>
                       <div class="panel-body">
                       		    <div class="row">
	                               <div class="col-lg-6">
	                               	<form role="form"  method="post" action="${ctx}/recommendmodesetting/${shop.sid}" >
	                                    <div class="form-group">
	                                            <label>销量筛选条件</label>
	                                            <div class="checkbox">
	                                                <label>
	                                                    <input type="checkbox" name="isSaledFirst" value="1" <c:if test="${setting.isSaledFirst == '1' }">checked</c:if>> 使用销量作为筛选条件
	                                                </label>
	                                            </div>
	                                     </div>
	                                    <div class="form-group">
	                                            <label for="saledNum">销量筛选数量</label>
	                                            <input class="form-control" name="saledNum" value="${setting.saledNum}">
	                                            <p class="help-block">推荐销量排名前10的宝贝.</p>
	                                    </div>
	                                    <button type="submit" class="btn btn-primary">保存</button>
	                                  	<button type="reset" class="btn btn-default">重置</button>
	                                  </form>
	                               </div>
                               <!-- /.col-lg-12 (nested) -->
                               </div>
                       </div>
                       <!-- /.panel-body -->
					<div class="panel-footer">
                           Panel Footer
                       </div>
				</div>
                   <!-- /.panel -->
               </div>
               <!-- /.col-lg-12 -->
               <div class="col-lg-6">
                   <div class="panel panel-default">
                       <div class="panel-heading">
                          	 其他过滤设置
                       </div>
                       <div class="panel-body">
                       		<div class="row">
                               <div class="col-lg-6">
                               	<form role="form" method="post" action="${ctx}/recommendmodesetting/edit/${shop.sid}" >
                                   <div class="form-group">
                                           <label>库存筛选条件</label>
	                                        <div class="checkbox">
	                                                <label>
	                                                    <input type="checkbox" value="1" name="isStorageFiltered" <c:if test="${setting.isStorageFiltered == '1' }">checked</c:if>> 使用库存作为筛选条件
	                                                </label>
	                                       </div>
                                    </div>
                                    <div class="form-group">
	                                            <label for="saledNum">库存筛选数量</label>
	                                            <input class="form-control" name="storageNum"  value="${setting.storageNum}" >
	                                            <p class="help-block">不推荐库存小于10的宝贝</p>
	                                </div>
	                                
	                                <div class="form-group">
                                           <label>下架筛选条件</label>
	                                        <div class="checkbox">
	                                                <label>
	                                                    <input type="checkbox" value="1" name="isOffshelvesFiltered" <c:if test="${setting.isOffshelvesFiltered == '1' }">checked</c:if>> 使用即将下架时间作为筛选条件
	                                                </label>
	                                       </div>
                                    </div>
                                    <div class="form-group">
	                                            <label for="saledNum">即将下架秒数</label>
	                                            <input class="form-control" name="offshelvesTime" value="${setting.offshelvesTime}">
	                                            <p class="help-block">不推荐将于300秒内下架的宝贝</p>
	                                </div>
                                    <button type="submit" class="btn btn-primary">保存</button>
                                  	<button type="reset" class="btn btn-default">重置</button>
                                  	</form>
                               </div>
                               <!-- /.col-lg-12 (nested) -->
                            </div>
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

