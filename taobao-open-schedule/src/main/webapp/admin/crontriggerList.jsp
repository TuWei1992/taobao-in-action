<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
  <table border="3">
   <tr>
    <th>jobName</th>
     <th>jobGroup</th>
     <th>jobClassName</th>
     <th>state</th>
    </tr>
  
    <s:iterator var="obj" value="jobList">
    <tr>
    <td>${obj.id.jobName}</td>
     <td>${obj.id.jobGroup}</td>
     <td>${obj.jobClassName}</td>
     <td>${obj.state}</td>
    </tr>
    </s:iterator>
   </table>
</body>
</html>