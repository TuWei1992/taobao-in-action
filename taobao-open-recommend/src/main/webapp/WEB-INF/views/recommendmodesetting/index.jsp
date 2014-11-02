<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <script type="text/javascript">
    jQuery(document).ready(function($) {
      $('#saleFrom').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
           saledNum: {
                validators: {
                    notEmpty: {
                        message: '请选择销量排名值。'
                    }
                }
            }
        }
      });

      $('#storageFrom').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
           storageNum: {
                validators: {
                    notEmpty: {
                        message: '请输入剩余库存数量。'
                    },
                    between: {
                        min: 5,
                        max: 100,
                        message: '输入剩余库存数量应在5-100之间。'
                    }
                }
            }
        }
      });

    });
  </script>
<head>
</head>
</head>
<body>
		<div class="row">
       <div class="col-lg-12">
           <h4 class="page-header">推荐方式设置</h4>
       </div>               <!-- /.col-lg-12 -->
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
                      <li>基础规则：优先推荐即将下架的宝贝，即按下架时间顺序推荐宝贝，越接近下架时间的宝贝越有可能被推荐。</li>
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
               <div class="panel-heading">自动橱窗推荐销量筛选</div>
               <div class="panel-body">
                      <div class="alert alert-success alert-dismissible" role="alert">
                              <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                              <strong>OK!</strong> 您的请求已提交成功。
                      </div>
                      <div class="alert alert-danger alert-dismissible" role="alert">
                              <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                              <strong>OK!</strong> 您的请求已提交成功。
                      </div>
                     <div class="col-lg-12">
                     	<form role="form" class="form-horizontal" id="saleFrom" method="post" action="${ctx}/recommendmodesetting/${shop.sid}" >
                          <div class="form-group">
                              <label for="isSaledFirst" class="col-sm-3 control-label">销量筛选条件</label>
                              <div class="col-sm-9">
                                  <label class="checkbox-inline">
                                  <input type="checkbox" name="isSaledFirst" value="1" <c:if test="${setting.isSaledFirst == '1' }">checked</c:if>> 使用销量作为筛选条件，查看销量靠前的宝贝。
                                  </label>
                              </div>
                           </div>
                          <div class="form-group">
                              <label for="saledNum" class="col-sm-3 control-label">销量筛选数量</label>
                              <div class="col-sm-9">
                                  <select name="saledNum" class="form-control">
                                        <option value="">请选择</option>
                                        <option value="5">5</option>
                                        <option value="10">10</option>
                                        <option value="20">20</option>
                                        <option value="30">30</option>
                                        <option value="40">40</option>
                                   </select>
                                   <p class="help-block">推荐销量排名靠前的宝贝.</p>
                              </div>
                          </div>
                        

                          <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <button type="submit" class="btn btn-primary">保存</button>
                                    <button type="reset" class="btn btn-default">重置</button>
                                </div>
                          </div>
                      </form>
                     </div>
                       <!-- /.col-lg-12 (nested) -->
               </div>
               <!-- /.panel-body -->
               <div class="panel-footer">
                   设置自动橱窗推荐的销量排名，小于该值的商品才可能被推荐到橱窗。
               </div>
            </div>
            <!-- /.panel -->
        </div>
               <!-- /.col-lg-12 -->
        <div class="col-lg-6">
          <div class="panel panel-default">
               <div class="panel-heading">
                  	 自动橱窗推荐库存筛选
               </div>
               <div class="panel-body">
               		
                       <div class="col-lg-12">
                       	<form role="form" id="storageFrom" class="form-horizontal" method="post" action="${ctx}/recommendmodesetting/edit/${shop.sid}" >
                           <div class="form-group">
                              <label for="isStorageFiltered" class="col-sm-3 control-label">库存筛选条件</label>
                              <div class="col-sm-9">
                                <label class="checkbox-inline">
                                 <input type="checkbox" value="1" name="isStorageFiltered" <c:if test="${setting.isStorageFiltered == '1' }">checked</c:if>> 使用库存作为筛选条件
                               </label>
                              </div>
                            </div>
                            <div class="form-group">
                              <label for="storageNum" class="col-sm-3 control-label">库存筛选数量</label>
                              <div class="col-sm-9">
                                <input class="form-control" name="storageNum"  value="${setting.storageNum}" >
                                <p class="help-block">不推荐库存小于<span class="sales">${setting.storageNum}</span>的宝贝</p>
                              </div>
                            </div>
                          <!--
                          
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
                        -->
                              <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <button type="submit" class="btn btn-primary">保存</button>
                                    <button type="reset" class="btn btn-default">重置</button>
                                </div>
                              </div>
                          	</form>
                       </div>
                       <!-- /.col-lg-12 (nested) -->
                </div>
               <!-- /.panel-body -->
               <div class="panel-footer">
                   设置自动橱窗推荐的库存最小值，大于该值的商品才可能被推荐到橱窗。
               </div>
  				</div>
        <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>
</body>
</html>

