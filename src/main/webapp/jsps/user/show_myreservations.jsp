<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="header.jsp"/>

<!-- js -->
<script	type="text/javascript">
	$(document).ready(function(){
		$(".cancel").click(function(){
			var rid = $(this).attr("title");
			var $this = $(this);
			//ajax调用
			$.ajax({
				type: "post",
				async:false,
				url:"/reservation/user.do?flag=updateReservationStatus",
				data:{"rid":rid,"status":"0"},
				success: function(data) {
					//alert(data);
					data = eval("("+data+")");
					var code=data.code;
					if(code == "0"){
						$this.hide();
						$this.parent().prev().text("未通过/取消");
					}else{
						alert(data.msg);
					}							
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("请求发送失败！");
				}
			}); 
		});
	});
</script>


<!--容器开始-->   
<!--=== Content Part ===-->
<div class="container">		
	<div class="row-fluid">
        <div class="alert alert-block">
            <strong>
				<a href="/reservation/user.do?flag=welcome">首页</a>
			</strong>
			<span class="divider">/</span>
			<strong>
            	${title}
            </strong>
        </div>
		
		<table class="table table-bordered">
		    <thead>
		        <tr>
		            <th>场地</th>
		            <th>预约日期</th>
		            <th>预约时间段</th>
		            <th>负责人</th>
		            <th>联系人</th>
		            <th>预约说明</th>
		            <th>状态</th>
		            <th>操作</th>
		        </tr>
		    </thead>
		    <tbody>
		    	<c:forEach items="${list}" var="r">
		        <tr>
		            <td>${r.cname}</td>
		            <td><fmt:formatDate value="${r.rtargetdate}" pattern="yyyy-MM-dd"/></td>
		            <td>
		            	<fmt:formatDate value="${r.tbegintime}" pattern="HH:mm"/>
		            	~
		            	<fmt:formatDate value="${r.tendtime}" pattern="HH:mm"/>
		            </td>
		            <td>${r.rchargeperson}(${r.rchargetelephone})</td>
		            <td>${r.rcontactsperson}(${r.rcontacttelephone})</td>
		            <td>${r.rspecificcontent}</td>
		            <td>
		            	<c:if test="${r.rstatus == '1'}">
		            		通过
		            	</c:if>
		            	<c:if test="${r.rstatus == '0'}">
		            		未通过/取消
		            	</c:if>
		            </td>
		            <td>
		            	<c:if test="${r.rstatus == '1'}">
		            		<input type="button" value="取消" class="cancel" title="${r.rid}"/>
		            	</c:if>
		            </td>
		        </tr>
		        </c:forEach>
		    </tbody>
		</table>
 		     
    </div><!--/row-fluid-->  
</div><!--/container-->		
<!--=== End Content Part ===-->
<!--容器结束-->

<c:import url="footer.jsp"/>