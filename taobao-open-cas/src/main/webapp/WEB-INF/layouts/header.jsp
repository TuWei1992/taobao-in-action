<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

		<!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">最强掌柜 - 七种武器之长生剑</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="profile.html"><i class="fa fa-user fa-fw"></i> 用户信息</a>
                        </li>
                        <li><a href="setting.html"><i class="fa fa-gear fa-fw"></i> 设置</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i>退出</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                            <!-- /input-group -->
                        </li>
                        <li>
                            <a class="active" href="${ctx}"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-files-o fa-fw"></i> 自动橱窗<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="recommend.html"> 橱窗设置</a>
                                </li>
                                <li>
                                    <a href="recommend-add.html"> 推荐宝贝</a>
                                </li>
                                <li>
                                    <a href="recommend-delete.html">未推荐宝贝</a>
                                </li>
                                <li>
                                    <a href="recommend-list.html">当前橱窗宝贝</a>
                                </li>
                                <li>
                                    <a href="recommend-log.html">自动橱窗日志</a>
                                </li>
                                <li>
                                    <a href="recommend-off.html">查看快下架宝贝</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-files-o fa-fw"></i> 自动上架<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="blank.html">批量上架计划</a>
                                </li>
                                <li>
                                    <a href="blank.html">指定宝贝上架计划</a>
                                </li>
                                 <li>
                                    <a href="blank.html">上架日志</a>
                                </li>
                                  <li>
                                    <a href="blank.html">计划上架分布</a>
                                </li>
                                  <li>
                                    <a href="blank.html">当前上架分布</a>
                                </li>
                                <li>
                                    <a href="blank.html">当前上架趋势</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-files-o fa-fw"></i> 自动评价<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="blank.html">自动评价设置</a>
                                </li>
                                <li>
                                    <a href="blank.html">自动评价记录</a>
                                </li>
                                  <li>
                                    <a href="blank.html">收到中评记录</a>
                                </li>
                                <li>
                                    <a href="blank.html">收到差评记录</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>