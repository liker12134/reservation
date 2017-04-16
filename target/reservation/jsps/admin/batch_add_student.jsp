<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
  <head>

    <title>学生数据批量导入</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="bootstrap2/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="bootstrap2/css/bootstrap-responsive.min.css" type="text/css">
    <link rel="stylesheet" href="bootstrap2/css/bootstrap-overrides.css" type="text/css">

	<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			//提交excel
			$("#commit_excel").click(function(){
				var fileName = $("#file").val();
				//alert(fileName);
				var fileType = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length);
				//alert(fileType);
				if(fileName == "" || fileType != "xls"){
					alert("请选择文件，并且文件必须是xls类型！");
					return false;
				}
			});
			
			//页面关闭时
			$(window).unload(function (evt) {  
			   alert("close");  
			});  
		});
	</script>
  </head>
  
  <body>
    	<div class="container">
    		<%-- 示例 --%>
    		<div class="alert alert-block">
    			<table class="table table-bordered">
    				<caption>
						批量导入示例
					</caption>
					<thead>
						<th>学号</th>
						<th>姓名</th>
						<th>联系电话</th>
						<th>专业（选填）</th>
						<th>班级（选填）</th>
					</thead>
					<tbody>
						<tr>
							<td>21551133</td>
							<td>潘烨</td>
							<td>13634293592</td>
							<td>大数据</td>
							<td>1501</td>
						</tr>
						<tr>
							<td>21551076</td>
							<td>高菲</td>
							<td>13634294567</td>
							<td>大数据</td>
							<td>1501</td>
						</tr>
					</tbody>
    			</table>
    		</div>
    		
    		<%-- 警告区 --%>
    		<c:if test="${not empty error}">
    			<div class="alert-error alert-block">
    				${error}
    			</div>
    		</c:if>
    		
    		<%-- 消息区 --%>
    		<c:if test="${not empty msg}">
    			<div class="alert-success alert-block">
    				${msg}
    			</div>
    		</c:if>
    		
    		<%-- 预览区 --%>
    		<c:if test="${not empty students}">
    			<div>
    				<table class="table table-bordered">
	    				<caption>
							表格数据预览
						</caption>
						<thead>
							<th>学号</th>
							<th>姓名</th>
							<th>联系电话</th>
							<th>专业（选填）</th>
							<th>班级（选填）</th>
						</thead>
						<tbody>
							<c:forEach var="s" items="${students}">
								<tr>
									<td>${s.sno}</td>
									<td>${s.sname}</td>
									<td>${s.sphone}</td>
									<td>${s.smajor}</td>
									<td>${s.sclasscode}</td>
								</tr>
							</c:forEach>
						</tbody>
	    			</table>
    			</div>
    		</c:if>
    		
    		<%-- 操作区 --%>
    		<div class="alert-success">
    			<c:choose>
    				<%-- 用户未选择文件 --%>
				   	<c:when test="${empty type}">
				   		<form action="/reservation/admin.do?flag=uploadStudent"  method="post" enctype="multipart/form-data" class="well">
							<div>
								注意：本系统只支持<strong>.xls文件</strong>。
								且表格第一行须为数据（无需表头）。
							</div>
							<input id="file" type="file" name="file"/>
							<input id="commit_excel" type="submit" value="提交excel" />
						</form>
				   	</c:when>
				   	<%-- 数据回填 --%>
				    <c:when test="${type == 'commit'}">
				    	<form action="/reservation/admin.do?flag=batchAddStudent"  method="post" class="well">
				   			<input id="add" type="submit" value="保存数据"/>
				   		</form>
				   	</c:when>
				</c:choose>
    		</div>
    		
    	</div>
  </body>
</html>
