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
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap-nav-wizard.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/static/css/zTreeStyle/zTreeStyle.css" type="text/css">

    <style>
        .link{border:1px solid #D8D8D8;display: inline-block;padding: 0 7px；margin:0 8px 8px 0;position: relative;line-height: 23px}
        
        .alink{border:1px solid #C7000B;display:inline-block;padding: 0 7px；margin:0 8px 8px 0;position: relative;line-height: 23px}
        
        .tick{position: absolute;right: 0;bottom: 0;width: 12px;height: 12px;display: block;background: url(${ctx}/static/images/icon.png) no-repeat -572px -14px;}

        .atick{position: absolute;right: 0;bottom: 0;width: 12px;height: 12px;display: block;background: url(${ctx}/static/images/icon.png) no-repeat -572px 0px;}

        .select-inline{padding-left:0px;margin-left: 0px;}
        .div-inline{padding-left: 20px;}
　　}
    </style>


    <script type="text/javascript" src="${ctx}/static/js/jquery.bootstrap.wizard.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/zTree/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/zTree/jquery.ztree.excheck-3.5.js"></script>
  </head>
  <body>
    <div class="row">
        <div class="col-lg-12">
            <h4 class="page-header">自动橱窗设置向导</h4>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <div class="row">
        <div class="col-lg-12">
            <section id="wizard">
                <!-- <form id="commentForm" method="get" action="" class="form-horizontal"> -->
                    <div id="rootwizard">
                        <ul>
                            <li><a href="#tab1" data-toggle="tab">选择自动推荐的宝贝范围</a></li>
                            <li><a href="#tab2" data-toggle="tab">设置“不推荐和必推荐”宝贝</a></li>
                            <li><a href="#tab3" data-toggle="tab">设置“人气和销量”推荐宝贝</a></li>
                            <li><a href="#tab4" data-toggle="tab">完成向导</a></li>
                        </ul>
                        <form class="form-horizontal" role="form">
                            <div class="tab-content" style="margin-top: 20px;">
                                <div class="tab-pane" id="tab1">
                                   <!--  <div class="form-group">
                                       <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
                                       <div class="col-sm-10">
                                           <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
                                       </div>
                                   </div>
                                   <div class="form-group">
                                       <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                                       <div class="col-sm-10">
                                           <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
                                       </div>
                                   </div> -->
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="radio-inline">
                                                <input type="radio" name="scopeType" id="scopeType" value="1"> 出售中的宝贝
                                                <p class="help-block">最常用的设置，可以让所有宝贝获得推荐机会，并且最大化利用宝贝下架时间</p>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="radio-inline">
                                                <input type="radio" name="scopeType" id="scopeType" value="1"> 按店铺分类推荐&nbsp;&nbsp;<a href="javascript:void();" id="category" class="btn btn-default btn-xs" role="button">选择分类</a>
                                                <p class="help-block">可用于店铺推广某类主打宝贝，换季推荐新品尤为合适。通过分析店铺内各类宝贝数据，建议选择“XXX”进行设置。</p>
                                            </label>
                                        </div>
                                    </div>
                                    <%--
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="radio-inline">
                                                <input type="radio" name="scopeType" id="scopeType" value="1"> 按关键字推荐
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group div-inline">
                                      <div class="col-sm-10">
                                        <input class="form-control" name="storageNum" value="" />
                                         <p class="help-block">结合搜索热词设置橱窗，进一步提高搜索流量。通过分析店铺内各类宝贝数据，建议选择“XXX”关键词进行设置。</p>
                                      </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="radio-inline">
                                                <input type="radio" name="scopeType" id="scopeType" value="1"> 指定宝贝推荐&nbsp;&nbsp;<a href="javascript:void();" id="item" class="btn btn-default btn-xs" role="button">选择宝贝</a>
                                                <p class="help-block">结合对店铺宝贝的了解和店铺推广的需要，指定推荐部分宝贝到橱窗。通过分析店铺内各类宝贝数据建议选择上述“XXX”个宝贝进行推荐。</p>
                                            </label>
                                        </div>
                                    </div>
                                    --%>
                                </div>
                                <div class="tab-pane" id="tab2">
                                    <h5>不推荐</h5>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <a  href="javascript:void(0)" class="link not-recommend" >
                                                <img src="${ctx}/static/images/t.jpg" width="60px" height="60px" />
                                                <i class="tick"></i>
                                            </a>
                                            <a  href="javascript:void(0)" class="alink not-recommend">
                                                <img src="${ctx}/static/images/t.jpg"  width="60px" height="60px" />
                                                <i class="atick"></i>
                                            </a>
                                            <p class="help-block">结合对店铺宝贝的了解和店铺推广的需要，指定推荐部分宝贝到橱窗。通过分析店铺内各类宝贝数据建议选择上述“XXX”个宝贝进行推荐。</p>
                                        </div>
                                    </div>
                    
                                    <h5>必推荐</h5>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <a  href="javascript:void(0)" class="link recommend" >
                                                <img src="${ctx}/static/images/t.jpg" width="60px" height="60px" />
                                                <i class="tick"></i>
                                            </a>
                                            <a  href="javascript:void(0)" class="alink recommend">
                                                <img src="${ctx}/static/images/t.jpg" width="60px" height="60px" />
                                                <i class="atick"></i>
                                            </a>
                                            <p class="help-block">结合对店铺宝贝的了解和店铺推广的需要，指定推荐部分宝贝到橱窗。通过分析店铺内各类宝贝数据建议选择上述“XXX”个宝贝进行推荐。</p>
                                            </div>
                                    </div>
                                </div>
                                <div class="tab-pane" id="tab3">
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="checkbox-inline">
                                                <input type="checkbox" name="popularity" id="popularity" value="1"> 人气推荐前
                                            </label>
                                            <label class="checkbox-inline select-inline">
                                                <select name="popularityS" class="input-sm">
                                                    <option value="">请选择</option>
                                                    <option value="5">5</option>
                                                    <option value="10">10</option>
                                                    <option value="20">20</option>
                                                    <option value="30">30</option>
                                                    <option value="40">40</option>
                                                </select>&nbsp;&nbsp;&nbsp;&nbsp;的宝贝
                                            </label>
                                            <p class="help-block">可用于店铺推广某类主打宝贝，换季推荐新品尤为合适。通过分析店铺内各类宝贝数据，建议选择“XXX”进行设置。</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="checkbox-inline">
                                                <input type="checkbox" name="salesVolume" id="salesVolume" value="1"> 销量推荐前
                                            </label>
                                            <label class="checkbox-inline select-inline">
                                                  <select name="salesVolumeS" class="input-sm">
                                                    <option value="">请选择</option>
                                                    <option value="5">5</option>
                                                    <option value="10">10</option>
                                                    <option value="20">20</option>
                                                    <option value="30">30</option>
                                                    <option value="40">40</option>
                                                </select>&nbsp;&nbsp;&nbsp;&nbsp;的宝贝
                                            </label>
                                            <p class="help-block">可用于店铺推广某类主打宝贝，换季推荐新品尤为合适。通过分析店铺内各类宝贝数据，建议选择“XXX”进行设置。</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane" id="tab4">
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="checkbox-inline">
                                                <input type="checkbox" name="enableRecommend" id="enableRecommend" value="1"> 开启自动推荐
                                                <p class="help-block">可用于店铺推广某类主打宝贝，换季推荐新品尤为合适。通过分析店铺内各类宝贝数据，建议选择“XXX”进行设置。</p>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <ul class="pager wizard">
                                    <li class="previous first" style="display:none;"><a href="#">第一步</a></li>
                                    <li class="previous"><a href="#">上一步</a></li>
                                    <li class="next last" style="display:none;"><a href="#">最后一步</a></li>
                                    <li class="next"><a href="#">下一步</a></li>
                                </ul>
                            </div>  
                        </form>
                    </div>
                <!-- </form> -->
            </section>
        </div>
    </div>
    <!-- Modal -->
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
    <!-- /.modal -->  


   

  
    
    
    <script>
    $(document).ready(function() {
          $('#rootwizard').bootstrapWizard({
            'tabClass': 'nav nav-wizard nav-justified'
        }); 
    }); 
    </script>

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

        var zNodes = [{"name":"Level1","id":1,"pId":0},{"name":"Level1","id":11,"pId":1},{"name":"Level1","id":2,"pId":0},{"name":"Level1","id":3,"pId":0}];
        
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
    </SCRIPT>

    <script>

    $('#myModal').on('show.bs.modal', function (e) {
        remoteTree();
    })

    $("#category").click(function(){
        showTree();
    });
    

    $("#item").click(function(){
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
         /*var treeObj = $.fn.zTree.getZTreeObj("tree");
         treeObj.checkAllNodes(false);
        $.get("${ctx}/recommendscopesetting/category",function(data){
            var json = $.parseJSON(data);
            $.each(json, function(i,item){
                var node = treeObj.getNodeByParam("id", item.catId, null);
                treeObj.checkNode(node,true,true);
            });
        });*/
    };

    var showTree = function(){
        $('#myModal').modal('show');
    };
    
    
    var hiddenTree = function(){
        $('#myModal').modal('hidden');
    };


    $(".not-recommend").click(function(){
        if($(this).children("i").hasClass("tick")){
            $(this).removeClass('link');
            $(this).addClass('alink');
            $(this).children("i").removeClass("tick");
            $(this).children("i").addClass("atick");
            return;
        }
        if($(this).children("i").hasClass("atick")){
            $(this).removeClass('alink');
            $(this).addClass('link');
            $(this).children("i").removeClass("atick");
            $(this).children("i").addClass("tick");
            return;
        }
    });
    

    $(".recommend").click(function(){
        if($(this).children("i").hasClass("tick")){
            $(this).removeClass('link');
            $(this).addClass('alink');
            $(this).children("i").removeClass("tick");
            $(this).children("i").addClass("atick");
            return;
        }
        if($(this).children("i").hasClass("atick")){
            $(this).removeClass('alink');
            $(this).addClass('link');
            $(this).children("i").removeClass("atick");
            $(this).children("i").addClass("tick");
            return;
        }
    });


    $("input[name='newsletter']").attr("checked", true);

    $(":checkbox[salesVolume]").click(function(event) {
        if($(this).attr("checked")){
            $("#salesVolumeS").attr("disabled",false);
            return;
        }

        if(!$(this).attr("checked")){
            $("#salesVolumeS").attr("disabled",true);
        }
    });

    
    

    </script>


  </body>
</html>

