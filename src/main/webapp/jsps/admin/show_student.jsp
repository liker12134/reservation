<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="main_head.jsp"/>

<!-- 前端分页开始 -->
<link rel="stylesheet" href="css/common/jquery.dataTables.css" type="text/css"></link>
<style type="text/css" class="init"></style>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript" class="init"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#studentList').dataTable();
	});
</script>
<!-- 前端分页结束 -->

	<script type="text/javascript">
		$(document).ready(function(){
			$("#batch").click(function(){
				//创建窗体
				var diag = new Dialog();
				//设置高度和宽度
				diag.Width = 950;
				diag.Height = 530;
				//设置窗体出现的位置
				diag.Top="20%";
				diag.Left="50%";
				//设置窗体标题与访问地址
				diag.Title = "批量导入";
				diag.URL = "/reservation/admin.do?flag=forBatchAddStudent";
				//关闭时重新加载
				diag.CancelEvent=function(){
					location.href = "/reservation/admin.do?flag=showStudent";
					diag.close();
				};
				//显示窗体
				diag.show();
				return false;
			});
			
			//跳转到新增班级组织页面
			$("#new").click(function(){
				//创建窗体
				var diag = new Dialog();
				//设置高度和宽度
				diag.Width = 850;
				diag.Height = 500;
				//设置窗体出现的位置
				diag.Top="20%";
				diag.Left="50%";
				//设置窗体标题与访问地址
				diag.Title = "添加班级组织";
				diag.URL = "/reservation/admin.do?flag=forAddStudent";
				//关闭时重新加载
				diag.CancelEvent=function(){
					location.href = "/reservation/admin.do?flag=showStudent";
					diag.close();
				};
				//显示窗体
				diag.show();
			});
			
			//跳转到修改页面
			$(".modified").click(function(){
				//获取ID
				var id = $(this).attr("name");
				//创建窗体
				var diag = new Dialog();
				//设置高度和宽度
				diag.Width = 850;
				diag.Height = 500;
				//设置窗体出现的位置
				diag.Top="20%";
				diag.Left="50%";
				//设置窗体标题与访问地址
				diag.Title = "修改班级组织";
				diag.URL = "/reservation/admin.do?flag=forUpdateStudent&sid="+id;			
				//关闭时重新加载
				diag.CancelEvent=function(){
					location.href = "/reservation/admin.do?flag=showStudent";
					diag.close();
				};
				//显示窗体
				diag.show();
			});
		});
	</script>
	
	<div class="row-fluid">
		<div class="page-header">
			<h1>班级组织维护<small>班级组织数据管理</small></h1>
		</div>
		<table id="studentList" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>账号</th>
					<th>密码</th>
					<th>组织名称</th>
					<%--
					<th>性别</th>
					<th>专业</th>
					<th>班级</th>
					<th>联系电话</th>
					 --%>
					<th>备注信息</th>
					<th>最近一次登入</th>
					<th>登入总次数</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="s" items="${stuList}">
					<tr class="list-roles">
						<td>${s.sno}</td>
						<td>${s.spassword}</td>
						<td>${s.sname}</td>
						<%-- 
						<c:if test="${s.ssex == '1'}">
							<td>男</td>	
						</c:if>	
						<c:if test="${s.ssex == '2'}">
							<td>女</td>	
						</c:if>										
						<td>${s.smajor}</td>											
						<td>${s.sclasscode}</td>											
						<td>${s.sphone}</td>	
						--%>										
						<td>${s.sremark}</td>											
						<td>${s.slastlogin}</td>											
						<td>${s.slogincount}</td>
						<c:if test="${s.status == '1'}">
							<td>有效</td>
						</c:if>
						<c:if test="${s.status == '0'}">
							<td>无效</td>
						</c:if>
						<td>
							<input class="btn btn-success modified" type="button" name="${s.sid}" value="修改"/>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a id="new" href="#" class="btn btn-success">新增</a>
		<a id="refresh" href="/reservation/admin.do?flag=showStudent" class="btn btn-success">刷新</a>
		<%-- 
		<a id="batch" href="#" class="btn btn-success">批量导入</a>
		--%>
	 </div>

<c:import url="main_foot.jsp"/>