<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<!--
	<link href="${ctx}/static/css/bootstrap-checkbox-tree.css" rel="stylesheet">
	<script src="${ctx}/static/js/bootstrap-checkbox-tree.js"></script>
	-->
  	<!-- 
	<script src="${ctx}/static/js/highlight.js"></script>
    <script src="${ctx}/static/js/main.js"></script>
     -->
	
	<link rel="stylesheet" href="${ctx}/static/css/zTreeStyle/zTreeStyle.css" type="text/css">
	
	<script type="text/javascript" src="${ctx}/static/js/zTree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/zTree/jquery.ztree.excheck-3.5.js"></script>
	 
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
                            全部商品数=在售商品数+仓库商品数
                        </div>
					</div>
                </div>
                <!-- /.col-lg-12 -->

                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            橱窗使用情况
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
                            店铺橱窗总数=剩余橱窗总数+已用橱窗总数
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.row -->
			
			<div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            自动橱窗推荐范围设置
                        </div>
                        <div class="panel-body">
                                <div class="col-lg-12">
                                    <form role="form" class="form-horizontal" method="post" action="${ctx}/recommendscopesetting/${shop.sid}">
                                        <%--<div class="form-group">
                                            <label for="isEnabled" class="col-sm-2 control-label">启动自动橱窗</label>
                                            <div class="col-sm-8">
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" name="isEnabled" id="isEnabled" value="1" <c:if test="${setting.isEnabled == '1' }">checked</c:if> >
                                                    <input type="hidden" name="cats" value="" />&nbsp;
                                                </label>
                                            </div>
                                        </div> --%>
                                        <div class="form-group">
                                            <label for="scopeType" class="col-sm-2 control-label">宝贝范围筛选</label>
                                            <div class="col-sm-8">
                                                <label class="radio-inline">
                                                    <input type="radio" name="scopeType" id="scopeType" value="0" <c:if test="${setting.scopeType == '0' }">checked</c:if> > 推荐出售中的宝贝
                                                </label>
                                                <label class="radio-inline">
                                                    <input type="radio" name="scopeType" id="scopeType" value="1" <c:if test="${setting.scopeType == '1' }">checked</c:if> > 推荐指定<a href="javascript:void();" id="category">分类</a>中的宝贝
                                                </label>
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <div class="col-sm-offset-2 col-sm-8">
                                                <button type="submit" class="btn btn-primary">保存设置</button>
                                                <button type="reset" class="btn btn-default">重置设置</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <!-- /.col-lg-12 (nested) -->
                        </div>
                        <!-- /.panel-body -->
						<div class="panel-footer">
                            	必须将“启动自动橱窗设置”设置为“启动”时，“自动橱窗推荐范围设置”及后续的“自动橱窗推荐方式设置”才可生效。
                        </div>
					</div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
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
	</SCRIPT>
	<script>

    $('#myModal').on('show.bs.modal', function (e) {
        remoteTree();
    })

	$("#category").click(function(){
		showTree();
	});
	
	$("input:radio[value='1']").click(function(){
    	showTree();
    });
	
	$("input:radio[value!='1']").click(function(){
		hiddenTree();
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
    
    </script>
</body>
</html>

