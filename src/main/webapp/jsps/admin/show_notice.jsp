<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="main_head.jsp"/>
	<script type="text/javascript" src="webplugins/ckeditor/ckeditor.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			var content = document.getElementById("ckcontent");
			 CKEDITOR.replace(content,
			 {
			 	skin: "kama", width:900, height:300
			 });
			
			$("#submit").click(function(){
				var text = CKEDITOR.instances['ckcontent'].getData();
				text = encodeURI(encodeURI(text));
				$.ajax({
					type: "get",
					async:false,
					url:"/reservation/admin.do?flag=updateNotice&notice="+text,
					success: function(data) {
						//alert(data);
						data = eval("("+data+")");
						if(data.code == "0"){
							alert("通知修改成功！");
						}else{
							alert("通知修改失败！");
						}
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {
						alert("请求发送失败！");
					}
				});
			}); 
			 
		});
	</script>
	
	
	<div class="row-fluid">
		<div class="page-header">
			<h1>管理员维护<small>管理员数据管理</small></h1>
		</div>
		<textarea class='content' name="pcontent" id="ckcontent">${notice}</textarea>
		<div class="action">
	        <input id="submit" type="submit" class="btn btn-primary" value="保存"/>
	    </div>
	</div>
	
<c:import url="main_foot.jsp"/>