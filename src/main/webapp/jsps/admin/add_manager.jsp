<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML >
<html>
  <head>

    <title>添加管理员</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="bootstrap2/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="bootstrap2/css/bootstrap-responsive.min.css" type="text/css">
    <link rel="stylesheet" href="bootstrap2/css/bootstrap-overrides.css" type="text/css">
    
	
  </head>
  
  <body>
    <div class="container">		
		<div class="row-fluid">
	        <div class="alert alert-block">
	            <strong>
	            	新增管理员
	            </strong>
	        </div>
	
	 		<!-- Input and Textarea-->
	         <div class="offset3 span6">
				<form action="/reservation/admin.do?flag=addManager" method="post">
	        		<label>用户名：</label>
	        		<input class="border-radius-none" name="maccount" type="text" placeholder="请填写用户名" required="required"/>
	    			    
	               	<label>密码：</label>
	    			<input class="border-radius-none" name="mpassword" type="password"  placeholder="请输入密码" required="required"/>                    
	                
	    			<label>管理员备注：</label>
	    			<textarea class="span12 border-radius-none" name="mremark" rows="6" placeholder="请填写备注信息"></textarea> 		    			    			
	    				    		               
	                <div>
	                	<button id="submit" type="submit" class="btn btn-success">保存</button>
	             	</div>
	             </form>
	         </div>     
	    </div><!--/row-fluid-->  
	</div><!--/container-->		
  </body>
</html>
