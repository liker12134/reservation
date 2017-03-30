<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
  <head>
    <title>活动室自助预约服务后台管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta charset="utf-8">
	
	<link rel="stylesheet" href="bootstrap2/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="bootstrap2/css/bootstrap-responsive.min.css" type="text/css">
    <link rel="stylesheet" href="bootstrap2/css/bootstrap-overrides.css" type="text/css"></link>
    <link rel="stylesheet" href="css/admin/theme.css" type="text/css"></link>
	<link rel="stylesheet" href="css/common/font-awesome.min.css" type="text/css"></link>
	
	<style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
    </style>

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="javascripts/html5.js"></script>
    <![endif]-->
	
	<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="bootstrap2/js/bootstrap.min.js"></script>
	
	<script type="text/javascript" src="webplugins/zdialog/zDialog.js"></script>
	<script type="text/javascript" src="webplugins/zdialog/zDrag.js"></script></head>
	
<body>
  <!-- 上面的导航栏 -->
  <div class="navbar">
        <div class="navbar-inner">
            <div class="container-fluid">
                <ul class="nav pull-right">
                    
                    <li id="fat-menu" class="dropdown">
                    	<a href="#" id="drop3" role="button" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-user"></i> 
                            	欢迎用户：${admin.maccount}<span class="caret"></span>
                            <i class="icon-caret-down"></i>
                        </a>

                        <ul class="dropdown-menu">
                            <li><a tabindex="-1" href="#">个人中心</a></li>
                            <li class="divider"></li>
                            <li><a tabindex="-1" href="/reservation/admin.do?flag=logout">退出登录</a></li>
                        </ul>
                    </li>
                    
                </ul>
                <a class="brand" href="#">
                	<span class="first">
                		活动室自助预约服务
                	</span> 
                	<span class="second">
                		后台管理系统
                	</span>
                </a>
            </div>
        </div>
    </div>
    
	<!-- 左侧菜单 -->
      <div class="container-fluid">
      
       <div class="row-fluid">
           <div class="span2">
               <div class="sidebar-nav">
                 <div class="nav-header" data-toggle="collapse" data-target="#dashboard-menu">
                 	<i class="icon-dashboard"></i>
                 	菜单项
                 </div>
                 <ul id="dashboard-menu" class="nav nav-list">
                     <li><a href="/reservation/admin.do?flag=showReservation">预约查询</a></li>
                     <li ><a href="/reservation/admin.do?flag=showClass">教室管理</a></li>
                     <li ><a href="/reservation/admin.do?flag=showStudent">班级组织管理</a></li>
                     <li ><a href="/reservation/admin.do?flag=showManager">管理员管理</a></li>
                     <!-- <li ><a href="/reservation/admin.do?flag=showTimequantum">时间段管理</a></li> -->
                     <li ><a href="/reservation/admin.do?flag=showNotice">通知管理</a></li>                
                 </ul>
           	</div>
       	</div>
       	
       	<!-- 动态页面 -->
        <div class="span10"> 