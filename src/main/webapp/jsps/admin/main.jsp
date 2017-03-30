<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


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
	
	<link rel="stylesheet" href="bootstrap2/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="bootstrap2/css/bootstrap-responsive.min.css" type="text/css">
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
	
	</head>
<body>
  <!-- 上面的导航栏 -->
  <div class="navbar">
        <div class="navbar-inner">
            <div class="container-fluid">
                <ul class="nav pull-right">
                    
                    <li id="fat-menu" class="dropdown">
                    	<a href="#" id="drop3" role="button" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-user"></i> 
                            	欢迎班级组织：${admin.maccount}<span class="caret"></span>
                            <i class="icon-caret-down"></i>
                        </a>

                        <ul class="dropdown-menu">
                            <li><a tabindex="-1" href="#">班级组织中心</a></li>
                            <li class="divider"></li>
                            <li><a tabindex="-1" href="#">退出登录</a></li>
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
            <div class="span3">
                <div class="sidebar-nav">
                  <div class="nav-header" data-toggle="collapse" data-target="#dashboard-menu"><i class="icon-dashboard"></i>Dashboard</div>
                    <ul id="dashboard-menu" class="nav nav-list collapse in">
                        <li><a href="http://www.baidu.com" target="contentFrame">Home</a></li>
                        <li ><a href="users.html">Sample List</a></li>
                        <li ><a href="user.html">Sample Item</a></li>
                        <li ><a href="gallery.html">Gallery</a></li>
                        <li ><a href="calendar.html">Calendar</a></li>
                        <li ><a href="faq.html">Faq</a></li>
                        <li ><a href="help.html">Help</a></li>                     
                    </ul>
            	</div>
        	</div>
        	
	        <div class="span9"> 
				
	        </div>
    	</div>
   	</div>
   
   <!-- 版权信息 -->
  	<div class="copyright">
		<div class="container">
			<div class="offset2 span8 text-center">						
	            <p>Copyright &copy; 2016.浙江大学软件学院.
	            <a href="/reservation/user.do?flag=welcome">预约系统入口</a></p>
			</div>
		</div><!--/container-->	
	</div><!--/copyright-->	
  </body>
</html>
