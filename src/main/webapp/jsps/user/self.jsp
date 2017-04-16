<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="header.jsp"/>

<!-- js -->
<script type="text/javascript">
    $(document).ready(function () {
        $("#showpassword").click(function () {
            var value = $(this).val();
            if (value == "显示密码") {
                $(this).prev().attr("type", "text");
                $(this).val("隐藏密码");
            } else {
                $(this).prev().attr("type", "password");
                $(this).val("显示密码");
            }
        });
    });
</script>


<!--容器开始-->
<!--=== Content Part ===-->
<div class="container">
    <div class="row-fluid">
        <div class="alert alert-block">
            <!-- 
            	<button type="button" class="close" data-dismiss="alert">&times;</button>
             -->
            <strong>
                <a href="/reservation/user.do?flag=welcome">首页</a>
            </strong>
            <span class="divider">/</span>
            <strong>
                ${title}
            </strong>
        </div>

        <div class="offset3 span6">
            <div class="headline">
                <h3>班级组织信息中心</h3>
            </div>

            <form action="/reservation/user.do?flag=updateSelf" method="post">
                <input type="hidden" name="sid" value="${student.sid}"/>

                <label>账号</label>
                <input class="border-radius-none" type="text" name="sno" value="${student.sno}" readonly="readonly"/>

                <label>密码</label>
                <input class="border-radius-none" name="spassword" type="password" value="${student.spassword}"
                       placeholder="请输入密码" required="required"/>
                <input id="showpassword" type="button" class="btn" value="显示密码"/>

                <label>组织名称</label>
                <input class="border-radius-none" name="sname" type="text" value="${student.sname}"
                       placeholder="请输入组织名称" required="required"/>

                <label>备注信息</label>
                <textarea class="span12 border-radius-none" name="sremark" type="text">${student.sremark }</textarea>

                <label>最近一次登入时间</label>
                <input class="border-radius-none" name="" type="text" value="${student.slastlogin }"
                       readonly="readonly"/>

                <label>登入总次数</label>
                <input class="border-radius-none" name="" type="text" value="${student.slogincount}"
                       readonly="readonly"/>

                <label>
                    状态：
                    <c:if test="${student.status == '1'}">
                        <input type="checkbox" checked="checked" name="" value="1" readonly="readonly"/>有效
                    </c:if>
                    <c:if test="${student.status == '0'}">
                        <input type="checkbox" name="" value="0" readonly="readonly"/>有效
                    </c:if>
                </label>
                <button id="submit" type="submit" class="btn btn-success">保存</button>
            </form>
        </div>
    </div><!--/row-fluid-->
</div>
<!--/container-->
<!--=== End Content Part ===-->
<!--容器结束-->

<c:import url="footer.jsp"/>