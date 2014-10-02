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
    <link href="${ctx}/static/css/bootstrap-switch.css" rel="stylesheet">
    

    <!-- MetisMenu CSS -->
    <link href="${ctx}/static/css/plugins/metisMenu/metisMenu.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="${ctx}/static/css/plugins/timeline.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${ctx}/static/css/sb-admin-2.css" rel="stylesheet">
    <link href="${ctx}/static/css/sticky-footer.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="${ctx}/static/css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${ctx}/static/font-awesome-4.1.0/css/font-awesome.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
     <!-- jQuery Version 1.11.0 -->
    <script src="${ctx}/static/js/jquery-1.11.0.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${ctx}/static/js/bootstrap.js"></script>
    <script src="${ctx}/static/js/bootstrap-switch.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${ctx}/static/js/plugins/metisMenu/metisMenu.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${ctx}/static/js/sb-admin-2.js"></script>
    
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
	<%@ include file="/WEB-INF/layouts/footer.jsp"%>
</body>
</html>