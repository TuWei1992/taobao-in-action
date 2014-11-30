<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta http-equiv="Cache-Control" content="no-store" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />

	<title><fmt:message key="webapp.name"/> | <sitemesh:title/></title>
	
	<link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon">

	<!-- Bootstrap Core CSS -->
    <link href="${ctx}/static/css/bootstrap.css" rel="stylesheet">
    
    <!-- Bootstrap Switch CSS -->
    <link href="${ctx}/static/css/bootstrap-switch.css" rel="stylesheet">
    
    <!-- MetisMenu CSS -->
    <link href="${ctx}/static/css/plugins/metisMenu/metisMenu.css" rel="stylesheet">

    <!-- Sticky Footer CSS -->
    <link href="${ctx}/static/css/sticky-footer.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="${ctx}/static/css/plugins/morris.css" rel="stylesheet">
    
    <!-- BootstrapValidator CSS -->
    <link href="${ctx}/static/js/plugins/bootstrapvalidator/css/bootstrapValidator.css" rel="stylesheet" type="text/css">

    <!-- Custom Fonts -->
    <link href="${ctx}/static/font-awesome-4.1.0/css/font-awesome.css" rel="stylesheet" type="text/css">

    <!-- Custom CSS -->
    <link href="${ctx}/static/css/sb-admin-2.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <%--
     <!-- jQuery Version 1.11.0 -->
    <script src="${ctx}/static/js/jquery-1.11.0.js"></script>
    
    <!-- Bootstrap Core JavaScript -->
    <script src="${ctx}/static/js/bootstrap.js"></script>

    <!-- Bootstrap Switch JavaScript -->
    <script src="${ctx}/static/js/bootstrap-switch.js"></script>

    <!-- Bootstrap Validator JavaScript -->
    <script src="${ctx}/static/js/plugins/bootstrapvalidator/js/bootstrapValidator.js"></script>
    <script src="${ctx}/static/js/plugins/bootstrapvalidator/js/language/zh_CN.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${ctx}/static/js/plugins/metisMenu/metisMenu.js"></script>

    <!-- JQuery Lazyload JavaScript -->
    <script src="${ctx}/static/js/plugins/jquery-lazyload/jquery.lazyload.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${ctx}/static/js/sb-admin-2.js"></script>

    <script type="text/javascript">
    
    </script>
    --%>
    
	<sitemesh:head/>
</head>

<body>
	<div id="wrapper">
		<%@ include file="/WEB-INF/layouts/header.jsp"%>
		<div id="page-wrapper">
			<sitemesh:body/>
		</div>
        <!-- /#page-wrapper -->
	</div>
    <!-- /#wrapper -->
<div class="tool" id="goTop">
</div>


<script type="text/javascript" src="${ctx}/static/js/require.js" defer async="true" data-main="${ctx}/static/js/main"></script>
<%--
<script type="text/javascript" src="${ctx}/static/js/gotop.js"></script>
<script type="text/javascript">
$(function(){
    $(document).goTop({
        showAfter:'#goTop', //必选 需执行返回顶部的元素
        debug:true,         //开启调试状态
        showPixels:10,     //滚动条滚动高度 单位 px
        scrollSpeed:1000,   //返回顶部速度
        callback:function(e){ //回调执行函数
            //alert(e);
        }
    });
})
</script>
--%>
</body>
</html>