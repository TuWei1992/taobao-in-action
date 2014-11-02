<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
     <!-- DataTables CSS -->
    <link href="${ctx}/static/css/plugins/dataTables.bootstrap.css" rel="stylesheet">
    
    <!-- DataTables JavaScript -->
    <script src="${ctx}/static/js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="${ctx}/static/js/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script src="${ctx}/static/js/plugins/dataTables/dataTables.bootstrap_zh_CN.js"></script>

  <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    $(document).ready(function() {
        $('#dataTables').dataTable({
            "sDom": "<'row'<'col-sm-6'l><'col-sm-6'f>r>" + "t" + "<'row'<'col-sm-6'i><'col-sm-6'p>>",
            "oLanguage": dataTable_zh
        });
    });
    </script>
</head>
<body>

        <div class="row">
            <div class="col-lg-12">
                <h4 class="page-header">  当前橱窗宝贝</h4>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                          	当前橱窗宝贝
                    </div>
                     <div class="panel-body">
                            <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover" id="dataTables">
                                        <thead>
                                            <tr role="row">
                                                <th>
                                                        宝贝名称
                                                </th>
                                                <th>
                                                        库存
                                                </th>

                                                <th>
                                                        上架时间
                                                </th>
                                                <th>
                                                        下架时间
                                                </th>
                                                <th>
                                                        出售状态
                                                </th>
                                                <th>
                                                        橱窗状态
                                                </th>
                                                <th>
                                                        操作
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="item" items="${items}">  
                                                <tr><td><img src="${ctx}/static/js/plugins/jquery-lazyload/grey.gif" data-original="${item.picUrl}" alt="" width="60px" height="65px" class="lazy img-thumbnail"> ${item.title}</td>
                                                <td>${item.num}</td>
                                                <td><fmt:formatDate value="${item.listTime}" type="both"/></td>
                                                <td><fmt:formatDate value="${item.delistTime}" type="both"/></td>
                                                <td>${item.approveStatus}</td>
                                                <td>${item.hasShowcase}</td>
                                                <td><a href="${ctx}/recommendspecified/${item.numIid}/edit/1" class="btn btn-primary btn-xs active" role="button">必推</a> <a href="${ctx}/recommendspecified/${item.numIid}/edit/0" class="btn btn-default btn-xs active" role="button"> 必不推</a></td>
                                                </tr>
                                            </c:forEach> 
                                        </tbody>
                            </table>
                            </div>
                        </div>
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
        </div>
		<!-- /.row-->
</body>
</html>

