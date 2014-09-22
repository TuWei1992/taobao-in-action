<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
</head>

<body>
    
       
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Dashboard</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-comments fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">26</div>
                                    <div>新的评价!</div>
                                </div>
                            </div>
                        </div>
                        <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">详情</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-tasks fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">12</div>
                                    <div>新的任务!</div>
                                </div>
                            </div>
                        </div>
                        <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">详情</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-shopping-cart fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">124</div>
                                    <div>新的订单!</div>
                                </div>
                            </div>
                        </div>
                        <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">详情</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-red">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-support fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">13</div>
                                    <div>其他事项!</div>
                                </div>
                            </div>
                        </div>
                        <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">详情</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-8">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> 最近访问轨迹
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div id="morris-area-chart"></div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-credit-card fa-fw"></i> 最近成交订单
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-4">
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover table-striped">
                                            <thead>
                                                <tr>
                                                    <th>订单编号</th>
                                                    <th>成交时间</th>
                                                    <th>成交金额</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>3326</td>
                                                    <td>3:29 PM</td>
                                                    <td>$321.33</td>
                                                </tr>
                                                <tr>
                                                    <td>3326</td>
                                                    <td>3:29 PM</td>
                                                    <td>$321.33</td>
                                                </tr>
                                                <tr>
                                                    <td>3326</td>
                                                    <td>3:29 PM</td>
                                                    <td>$321.33</td>
                                                </tr>
                                                <tr>
                                                    <td>3326</td>
                                                    <td>3:29 PM</td>
                                                    <td>$321.33</td>
                                                </tr>
                                                <tr>
                                                    <td>3326</td>
                                                    <td>3:29 PM</td>
                                                    <td>$321.33</td>
                                                </tr>
                                                <tr>
                                                    <td>3326</td>
                                                    <td>3:29 PM</td>
                                                    <td>$321.33</td>
                                                </tr>
                                                <tr>
                                                    <td>3326</td>
                                                    <td>3:29 PM</td>
                                                    <td>$321.33</td>
                                                </tr>
                                                <tr>
                                                    <td>3326</td>
                                                    <td>3:29 PM</td>
                                                    <td>$321.33</td>
                                                </tr>

                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- /.table-responsive -->
                                </div>
                                <!-- /.col-lg-4 (nested) -->
                                <div class="col-lg-8">
                                    <div id="morris-bar-chart"></div>
                                </div>
                                <!-- /.col-lg-8 (nested) -->
                            </div>
                            <!-- /.row -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-8 -->
                <div class="col-lg-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-user fa-fw"></i> 店铺信息
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">                        
                            <ul>
                                <li>店铺名称：Dreamtown's Store</li>
                                <li>用户名：wallace46886799</li>
                                <li>创店时间：2011/9/2 23:49:30</li>
                                <li>好评率：0%</li>
                                <li>卖家信用：0</li>
                                <li>全部宝贝：4  
                                    <button type="button" class="btn btn-default btn-xs">
                                        <span class="glyphicon glyphicon-refresh"></span> 刷新
                                    </button>
                                </li>
                                <li>在售宝贝：4</li>
                                <li>库存宝贝：0</li>
                            </ul>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> 店铺销量占比图
                        </div>
                        <div class="panel-body">
                            <div id="morris-donut-chart"></div>
                            <a href="#" class="btn btn-default btn-block">详情</a>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                 </div>
                <!-- /.col-lg-4 -->

                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> 已订购产品
                        </div>
                        <div class="panel-body">
                            <div class="col-sm-4 col-md-3">
                                <div class="thumbnail">
                                  <img src="http://www.w3cschool.cc/wp-content/uploads/2014/06/kittens.jpg" alt="...">
                                  <div class="caption">
                                    <h5> 最强掌柜</h5>
                                    <p>...</p>
                                    <p><a href="#" class="btn btn-primary" role="button">Button</a></p>
                                  </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                                       
                                
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> 更多产品
                        </div>
                        <div class="panel-body">
                             <div class="col-sm-4 col-md-3">
                                <div class="thumbnail">
                                  <img src="http://www.w3cschool.cc/wp-content/uploads/2014/06/kittens.jpg" alt="...">
                                  <div class="caption">
                                    <h5> 最强掌柜</h5>
                                    <p>...</p>
                                    <p><a href="#" class="btn btn-primary" role="button">Button</a></p>
                                  </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-md-3">
                                <div class="thumbnail">
                                  <img src="http://www.w3cschool.cc/wp-content/uploads/2014/06/kittens.jpg" alt="...">
                                  <div class="caption">
                                    <h5> 最强掌柜</h5>
                                    <p>...</p>
                                    <p><a href="#" class="btn btn-primary" role="button">Button</a></p>
                                  </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-md-3">
                                <div class="thumbnail">
                                  <img src="http://www.w3cschool.cc/wp-content/uploads/2014/06/kittens.jpg" alt="...">
                                  <div class="caption">
                                    <h5> 最强掌柜</h5>
                                    <p>...</p>
                                    <p><a href="#" class="btn btn-primary" role="button">Button</a></p>
                                  </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-md-3">
                                <div class="thumbnail">
                                  <img src="http://www.w3cschool.cc/wp-content/uploads/2014/06/kittens.jpg" alt="...">
                                  <div class="caption">
                                    <h5> 最强掌柜</h5>
                                    <p>...</p>
                                    <p><a href="#" class="btn btn-primary" role="button">Button</a></p>
                                  </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.row -->
       
    
</body>
</html>
