<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="tc" uri="http://www.dreamlabs.com/tags/common"  %> 
 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <link rel="stylesheet" href="${ctx}/static/css/zTreeStyle/zTreeStyle.css" type="text/css">
    
    <script type="text/javascript" src="${ctx}/static/js/zTree/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/zTree/jquery.ztree.excheck-3.5.js"></script>
</head>
<body>
			<div class="row">
                <div class="col-lg-12">
                    <h4 class="page-header">Dashboard</h4>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <%--
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
            --%>
            <div class="row">
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> 橱窗概况
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <ul>
                                <li>橱窗状态：<tc:appCodeName groupId="1000" codeValue="${status.status}" />&nbsp;<c:if test="${status.status == '1' }"><a href="${ctx}/recommendstatus/update" class="btn btn-default btn-xs" role="button">关闭</a></c:if><c:if test="${status.status == '0' }"><a href="${ctx}/recommendstatus/update" class="btn btn-default btn-xs" role="button">开启</a></c:if> </li>
                                <li>自动推荐范围：<tc:appCodeName groupId="1001" codeValue="${scopeSetting.scopeType}" />  <c:if test="${scopeSetting.scopeType == '1' }">&nbsp;<a href="javascript:void();" class="btn btn-default btn-xs" role="button" id="category">查看分类</a></c:if></li>
                                <li>自动推荐方式：
                                    <ul>
                                      <li>不推荐宝贝：${fn:length(mustRecommend)}</li>
                                      <li>必推荐宝贝：${fn:length(notRecommend)}</li>
                                      <li>销量推荐宝贝：</li>
                                      <li>人气推荐宝贝：</li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-credit-card fa-fw"></i> 橱窗使用情况
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <%--
                                <ul>
                                    <li>橱窗位数量：${showcases.allCount}</li>
                                    <li>已推荐橱窗：${showcases.usedCount}</li>
                                    <li>剩余橱窗数：${showcases.remainCount}</li>
                                </ul>
                            --%>
                                <div id="showcase" style="height:300px;width:590px"></div>            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-8 -->
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-user fa-fw"></i> 店铺信息
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">                        
                            <ul>
                                <li>店铺名称：${store.title}</li>
                                <li>用户名：${seller.nick}</li>
                                <li>创店时间：<fmt:formatDate value="${store.created}" pattern="yyyy-MM-dd" /></li>
                                <li>好评率：${seller.sellerCredit.score} </li>
                                <li>卖家信用：${seller.sellerCredit.level}</li>
                                <li>全部宝贝：${fn:length(onsales)}  
                                    <button type="button" class="btn btn-default btn-xs">
                                        <span class="glyphicon glyphicon-refresh"></span> 刷新
                                    </button>
                                </li>
                                <li>在售宝贝：${fn:length(onsales)};库存宝贝：${fn:length(instocks)}</li>
                                
                            </ul>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> 橱窗分布情况
                        </div>
                        <div class="panel-body">
                            <%--
                            <div class="col-lg-4">
                                <ul>
                                    <li>自动推荐：</li>
                                    <li>必推荐：</li>
                                    <li>人气推荐：</li>
                                    <li>销量推荐：</li>
                                </ul>
                            </div>
                             <div class="col-lg-8">
                                <div id="recommend" style="height:300px;width:590px"></div>
                            </div>   
                            --%>
                            <div id="recommend" style="height:300px;width:590px"></div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                 </div>
                <!-- /.col-lg-4 -->
                <%--
                <div id="showcase" class="col-lg-12" style="height:400px">
                    
                </div>
               
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
            <!-- /.row --> --%>
       
 <!-- ECharts单文件引入 -->
      <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">请选择店铺自定义分类</h4>
                </div>
                <div class="modal-body">
                       <ul id="tree" class="ztree"></ul>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" id="modalConfirm">确认</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>   


    <script src="${ctx}/static/js/echarts/echarts-plain.js"></script>

    <SCRIPT type="text/javascript">
        <!--
        var setting = {
            view: {
                selectedMulti: false
            },
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                beforeCheck: beforeCheck,
                onCheck: onCheck
            }
        };

        var zNodes = $.parseJSON('${shopcats}');
        
        var code, log, className = "dark";
        function beforeCheck(treeId, treeNode) {
            className = (className === "dark" ? "":"dark");
            showLog("[ "+getTime()+" beforeCheck ]&nbsp;&nbsp;&nbsp;&nbsp;" + treeNode.name );
            return (treeNode.doCheck !== false);
        }
        function onCheck(e, treeId, treeNode) {
            showLog("[ "+getTime()+" onCheck ]&nbsp;&nbsp;&nbsp;&nbsp;" + treeNode.name );
        }       
        function showLog(str) {
            if (!log) log = $("#log");
            log.append("<li class='"+className+"'>"+str+"</li>");
            if(log.children("li").length > 6) {
                log.get(0).removeChild(log.children("li")[0]);
            }
        }
        function getTime() {
            var now= new Date(),
            h=now.getHours(),
            m=now.getMinutes(),
            s=now.getSeconds(),
            ms=now.getMilliseconds();
            return (h+":"+m+":"+s+ " " +ms);
        }

        function checkNode(e) {
            var zTree = $.fn.zTree.getZTreeObj("tree"),
            type = e.data.type,
            nodes = zTree.getSelectedNodes();
            if (type.indexOf("All")<0 && nodes.length == 0) {
                alert("请先选择一个节点");
            }

            if (type == "checkAllTrue") {
                zTree.checkAllNodes(true);
            } else if (type == "checkAllFalse") {
                zTree.checkAllNodes(false);
            } else {
                var callbackFlag = $("#callbackTrigger").attr("checked");
                for (var i=0, l=nodes.length; i<l; i++) {
                    if (type == "checkTrue") {
                        zTree.checkNode(nodes[i], true, false, callbackFlag);
                    } else if (type == "checkFalse") {
                        zTree.checkNode(nodes[i], false, false, callbackFlag);
                    } else if (type == "toggle") {
                        zTree.checkNode(nodes[i], null, false, callbackFlag);
                    }else if (type == "checkTruePS") {
                        zTree.checkNode(nodes[i], true, true, callbackFlag);
                    } else if (type == "checkFalsePS") {
                        zTree.checkNode(nodes[i], false, true, callbackFlag);
                    } else if (type == "togglePS") {
                        zTree.checkNode(nodes[i], null, true, callbackFlag);
                    }
                }
            }
        }

        function setAutoTrigger(e) {
            var zTree = $.fn.zTree.getZTreeObj("tree");
            zTree.setting.check.autoCheckTrigger = $("#autoCallbackTrigger").attr("checked");
            $("#autoCheckTriggerValue").html(zTree.setting.check.autoCheckTrigger ? "true" : "false");
        }

        $(document).ready(function(){
            $.fn.zTree.init($("#tree"), setting, zNodes);
            $("#checkTrue").bind("click", {type:"checkTrue"}, checkNode);
            $("#checkFalse").bind("click", {type:"checkFalse"}, checkNode);
            $("#toggle").bind("click", {type:"toggle"}, checkNode);
            $("#checkTruePS").bind("click", {type:"checkTruePS"}, checkNode);
            $("#checkFalsePS").bind("click", {type:"checkFalsePS"}, checkNode);
            $("#togglePS").bind("click", {type:"togglePS"}, checkNode);
            $("#checkAllTrue").bind("click", {type:"checkAllTrue"}, checkNode);
            $("#checkAllFalse").bind("click", {type:"checkAllFalse"}, checkNode);
            $("#autoCallbackTrigger").bind("change", {}, setAutoTrigger);
        });
        //-->

    $('#myModal').on('show.bs.modal', function (e) {
        remoteTree();
    });

    $("#category").click(function(){
        showTree();
    });

    $("#modalConfirm").click(function(){
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        var nodes = treeObj.getCheckedNodes(true);
        var catIds = "";
        $.each(nodes,function(i,item){
          catIds = catIds+item.id+"_"+item.name+"_"+item.level+",";
        });
        $("input:hidden").val(catIds);
    });
    
    var remoteTree = function () {
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        
         treeObj.checkAllNodes(false);
        $.get("${ctx}/recommendscopesetting/category",function(data){
            var json = $.parseJSON(data);
            $.each(json, function(i,item){
                var node = treeObj.getNodeByParam("id", item.catId, null);
                treeObj.checkNode(node,true,true);
            });
        });
    };

    var showTree = function(){
        $('#myModal').modal('show');
    };
    
    
    var hiddenTree = function(){
        $('#myModal').modal('hidden');
    };
    </SCRIPT>

    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts图表
        var showcase = echarts.init(document.getElementById('showcase')); 
        var option = {
             tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient : 'vertical',
                x : 'left',
                data:['已推荐橱窗','剩余橱窗数']
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            series : [
                {
                    name:'访问来源',
                    type:'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data:[
                        {value:${showcases.usedCount}, name:'已推荐橱窗'},
                        {value:${showcases.remainCount}, name:'剩余橱窗数'}
                    ]
                }
            ]
        };
        // 为echarts对象加载数据 
        showcase.setOption(option); 

        var recommend = echarts.init(document.getElementById('recommend')); 
        var option1 = {
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient : 'vertical',
                x : 'left',
                data:['自动推荐','人气推荐','销量推荐','必推荐']
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            series : [
                {
                    name:'访问来源',
                    type:'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data:[
                        {value:20, name:'自动推荐'},
                        {value:10, name:'人气推荐'},
                        {value:30, name:'销量推荐'},
                        {value:40, name:'必推荐'}
                    ]
                }
            ]
        };
        recommend.setOption(option1);
    </script>   
</body>
</html>
