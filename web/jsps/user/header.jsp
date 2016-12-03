<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>${title}</title>
	<!-- Meta -->
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="" />
    <meta name="author" content="" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head> 
	
	<link rel="stylesheet" href="css/user/style.css" type="text/css"> 
    <link rel="stylesheet" href="bootstrap2/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="bootstrap2/css/bootstrap-responsive.min.css" type="text/css">
    <link rel="stylesheet" href="bootstrap2/css/bootstrap-overrides.css" type="text/css">
    
    <link rel="stylesheet" href="css/common/font-awesome.min.css" type="text/css">
	
	<!--导航样式表-->
	<link rel="stylesheet" href="css/user/style-responsive.css" type="text/css">
    <link rel="stylesheet" href="css/user/style_new.css" type="text/css">
	
	<!-- foot样式 -->
	<link rel="stylesheet" href="css/user/theme-default.css" type="text/css"> 
    
    <style>
       body{
		font-family: "微软雅黑";}

		textarea
		{
			width: 100%;
		}
		
		.accordion-heading a
		{
			color: #4F4A4A;
		}

	</style>
    
	<!--js文件  -->
	<!--[if lt IE 9]>
	  <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	  <script src="js/css3-mediaqueries.js"></script>
	  <script src="js/html5.js"></script>
	<![endif]-->
	
	<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
	<script src="bootstrap2/js/bootstrap.js" type="text/javascript"></script>
	<script type="text/javascript">
		var isLogin = ${not empty userSession};
	</script>
</head>

<body class="page-header-fixed page-sidebar-fixed page-sidebar-closed">

	<!-- 顶部开始 -->
	<div class=" header navbar navbar-green">
		<!-- 导航内容开始 -->
		<!-- <div class="navbar-green"> -->
			<div class="container-fluid">
				<!-- logo 开始 -->
				<a class="brand" href="#">
				<img src="img/logo.jpg" style="width:25px;"  alt="logo" /><span class="brandfont">浙江大学软件学院活动室自助预约服务系统</span>
				</a>
				<!-- logo 结束 -->
				
				<!-- 响应式折叠导航按钮开始 -->
				<a href="javascript:;" class="btn-navbar collapsed " data-toggle="collapse" data-target=".nav-collapse">
					<i class="icon-reorder" style="color:gray"></i>
				</a>          
				<!-- 响应式折叠导航按钮结束 -->            
				<ul class="nav usermenu pull-right">
					<%-- 未登入 --%>
					<c:if test="${empty userSession}">
						<a href="#login" class="dropdown-toggle" data-toggle="modal">
							<span class="login" >登入系统</span>   
						</a>
					</c:if>
					<%-- 已登入 --%>
					<c:if test="${not empty userSession}">
						<!-- 用户下拉菜单 开始 -->
						<li class="dropdown user">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								<img alt="" src="img/handsome.gif" />
								<span class="username">${userSession.sname}</span> 
								<b class="caret"></b>     
							</a>
							<ul class="dropdown-menu">
								<li>
									<a href="/reservation/user.do?flag=myReservation">
										<i class="icon-user"></i> 历史预约记录
									</a>
								</li>
								<li>
									<a href="/reservation/user.do?flag=self">
										<i class="icon-user"></i> 账户中心
									</a>
								</li>
								<li>
									<a href="/reservation/user.do?flag=logout">
										<i class="icon-key"></i> 退出登入
									</a>
								</li>
							</ul>
						</li>
						<!-- 用户下拉菜单结束 -->
					</c:if>
				</ul>
			</div>
		<!-- </div> -->
		<!-- 导航内容结束 -->
	</div>
   <!--顶部结束-->
   
   
   <script type="text/javascript">
   	$(document).ready(function(){
   		$("#sno").keyup(function(){
   			var sno = $(this).val();
   			if(sno == null || sno == ""){
   				$("#sno_tip").show();
   			}else{
   				$("#sno_tip").hide();
   			}
   		});
   		
   		$("#spassword").keyup(function(){
   			var spassword = $(this).val();
   			if(spassword == null || spassword == ""){
   				$("#spassword_tip").show();
   			}else{
   				$("#spassword_tip").hide();
   			}
   		});
   		
   		$("#commit").click(function(){
   			var sno = $("#sno").val();
   			var spassword = $("#spassword").val();
   			if(sno == null || sno == ""){
   				$("#sno_tip").show();
   				return false;
   			}
   			
   			if(spassword == null || spassword == ""){
   				$("#spassword_tip").show();
   				return false;
   			}
   			
   			//ajax
   			$.ajax({
				type: "post",
				url:"<c:url value='/user.do?flag=login'/>",
				data:{"sno":sno,"spassword":spassword},
				success: function(data) {
					//alert(data);
					data = eval("("+data+")");
					if(data.code == "0"){
						//登录成功,重新加载
						location.href = "/reservation/user.do?flag=welcome";
					}else{
						alert(data.msg);
						//$("#tip").html(data.msg);
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("请求发送失败！");
					//$("#tip").html(data.msg);
				}
			});
   			return false;
   		});
   	});
   </script>
   
   <%--登入div--%>
   <div class="modal hide fade" id="login">
   		<div class="headline">
   			<h3>登录信息</h3>
   		</div>
   		<div class="content-wrap">
		   	<form class="form-horizontal">
				<fieldset>
					<div class="control-group">
						<!--control-label可以让label和input放在同一行-->
						<label class="control-label">请输入班级账号</label>
						<div class="controls">
							<input type="text" name="sno" id="sno"/>
							<p id="sno_tip" class="help-block">请输入班级账号</p>
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label">请输入密码</label>
						<div class="controls">
							<input type="password" name="spassword" id="spassword"/>
							<p id="spassword_tip" class="help-block">请输入密码</p>
						</div>
					</div>
					
					<!-- 
						<p id="tip" class="help-block"></p>  
					-->
					
					<div class="form-actions">
						<input id="commit" type="submit" class="btn btn-primary" value="登录"></input>
					</div>
				</fieldset>
			</form>
		</div>
   </div>