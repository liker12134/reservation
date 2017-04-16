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
	
	<!-- bootstrap -->
    <link href="bootstrap2/css/bootstrap.min.css" rel="stylesheet" />
    <link href="bootstrap2/css/bootstrap-responsive.min.css" rel="stylesheet" />
    <link href="bootstrap2/css/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/common/layout.css" />
    <link rel="stylesheet" type="text/css" href="css/common/elements.css" />
	
    <!-- libraries -->
    <link rel="stylesheet" type="text/css" href="css/common/font-awesome.css" />
    
    <!-- this page specific styles -->
    <link rel="stylesheet" href="css/admin/signup.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="css/admin/theme.css" type="text/css"></link>
	
	<!-- open sans font -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' />
	
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    
    <script type="text/javascript">
    	var err = "${err}";
    	if(!(err == null || err == "")){
    		alert(err);
    	}
    </script>
  </head>
  
  <body style="background-image: url(img/admin_background.jpg);">
  
  	<div class="navbar" style="margin-bottom: 80px;">
        <div class="navbar-inner">
            <div class="container-fluid">
                <a class="brand" href="#"><span class="">浙江大学软件学院活动室自助预约服务后台管理系统</span></a>
            </div>
        </div>
    </div>
  
    <div class="row-fluid login-wrapper">
        <div class="box">
            <div class="content-wrap">
            	<h6>
		    		管理系统登录信息
		    	</h6>
		    	<form action="/reservation/admin.do?flag=login" method="post">
	                <input class="span12" type="text" name="account" placeholder="account" />
	                <input class="span12" type="password" name="password" placeholder="Password" />
	                <div class="action">
	                    <input type="submit" class="btn-glow primary signup" value="登录"/>
	                </div>
                </form>              
            </div>
        </div>
     </div>
</body>
</html>
