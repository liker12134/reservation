<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML >
<html>
  <head>

    <title>添加教室</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="bootstrap2/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="bootstrap2/css/bootstrap-responsive.min.css" type="text/css">
    <link rel="stylesheet" href="bootstrap2/css/bootstrap-overrides.css" type="text/css">
        	
	<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
  </head>
  
  <body>
  

<script type="text/javascript">
	$(document).ready(function(){
		var canSubmit = false;
		$("#msg").hide();
		$("#cname").keyup(checkClassName);
		
		function checkClassName(){
			var cname=$("#cname").val();
			$("#msg").hide();
			
			if(cname == null || cname == ""){
				$("#msg").show().text("教室名不能为空");
				return false;
			}
			if(cname.length > 40){
				$("#msg").show().text("教室名长度不可大于40个字符");
				return false;
			}
			
			//ajax调用
			$.ajax({
				type: "post",
				async:false,
				url:"/reservation/admin.do?flag=selectClassByName",
				data:{"cname":cname},
				success: function(data) {
					//alert(data);
					data = eval("("+data+")");
					var code=data.code;
					if(code == "0"){//不存在此教室名
						//location.href = $("#addform").attr("action") + "&" + $("#addform").serialize();
						canSubmit = true;
						return true;
					}else if(code == "1"){ //存在此教室名,提示
						$("#msg").show().text("该教室名称已存在");
					}else{
						$("#msg").show().text(data.msg);
					}							
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					$("#msg").show().text("请求发送失败！");
				}
			}); 
			return false;
		}
	
	
		$("#submit").click(function(){
			if(!canSubmit){
				//不能提交时的业务处理
				
				return false;
			}
			return true;
		});
	});

</script>
  
  
    <div class="container">		
		<div class="row-fluid">
	        <div class="alert alert-block">
	            <strong>
	            	新增教室
	            </strong>
	        </div>
	
	 		<!-- Input and Textarea-->
	         <div class="offset3 span6">
				<form id="addform" action="/reservation/admin.do?flag=addClass" method="post">
	        		<label>教室名称：</label>
	        		<input id="cname" class="border-radius-none" name="cname" type="text" placeholder="请填写教室名称" />
	    			<span id="msg" style="color: red;"></span>
	    			    
	               	<label>最大容量：</label>
	    			<input class="border-radius-none" type="number" name="cmax" placeholder="请填写最大容量" />                    
	                
	    			<label>备注信息：</label>
	    			<textarea class="span12 border-radius-none" name="cremark" rows="6" placeholder="请填写备注信息"></textarea> 
		    		
	    			<label>教室状态：
	    			<input type="checkbox" name="cstatus" checked="checked"/>有效
	    			</label>
	    			
		    		<label>操作者：</label>
		    		<input class="border-radius-none" type="text" value="${admin.maccount}" readonly="readonly"/>                    
	                
	                <div>
	                	<input id="submit" type="submit" class="btn btn-success" value="保存"></input>
	             	</div>
	             </form>
	         </div>     
	    </div><!--/row-fluid-->  
	</div><!--/container-->		
  </body>
</html>
