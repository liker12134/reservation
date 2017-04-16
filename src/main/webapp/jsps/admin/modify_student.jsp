<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML >
<html>
<head>

    <title>修改班级组织</title>

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
</head>

<body>
<div class="container">
    <div class="row-fluid">
        <div class="alert alert-block">
            <strong>
                修改班级组织
            </strong>
        </div>

        <!-- Input and Textarea-->
        <div class="offset3 span6">
            <form action="/reservation/admin.do?flag=updateStudent" method="post">

                <input name="sid" value="${student.sid}" type="hidden"/>

                <label>账号：</label>
                <input class="border-radius-none" name="sno" value="${student.sno}" type="text" readonly="readonly"/>

                <label>初始化密码：</label>
                <input class="border-radius-none" name="spassword" value="${student.spassword}" type="password"
                       placeholder="请填写初始化密码"/>
                <input id="showpassword" type="button" class="btn" value="显示密码"/>

                <label>组织名称：</label>
                <input class="border-radius-none" name="sname" value="${student.sname}" type="text"
                       placeholder="请填写组织名称"/>

                <%--
                <label>性别：
                    <c:if test="${student.ssex == '1'}">
                        <label class="radio inline">
                     <input type="radio" name="ssex" value="1"  checked="checked">男</input>
                     </label>
                     <label class="radio inline">
                         <input type="radio" name="ssex" value="2">女</input>
                     </label>
                 </c:if>
                 <c:if test="${student.ssex == '2'}">
                        <label class="radio inline">
                     <input type="radio" name="ssex" value="1" >男</input>
                     </label>
                     <label class="radio inline">
                     <input type="radio" name="ssex" value="2" checked="checked">女</input>
                     </label>
                 </c:if>
                </label>

             <label>专业：</label>
             <input class="border-radius-none" name="smajor" value="${student.smajor}" type="text" placeholder="请填写专业" />

             <label>班级：</label>
             <input class="border-radius-none" name="sclasscode" value="${student.sclasscode}" type="text" placeholder="请填写班级" />

             <label>联系电话：</label>
             <input class="border-radius-none" name="sphone" value="${student.sphone}" type="text" placeholder="请填写联系电话" />
              --%>

                <label>备注信息：</label>
                <textarea class="span12 border-radius-none" name="sremark" rows="6"
                          placeholder="请填写备注信息">${student.sremark}</textarea>

                <label>
                    状态：
                    <c:if test="${student.status == '1'}">
                        <input type="checkbox" name="status" checked="checked"/>有效
                    </c:if>
                    <c:if test="${student.status == '0'}">
                        <input type="checkbox" name="status"/>有效
                    </c:if>
                </label>

                <div>
                    <button id="submit" type="submit" class="btn btn-success">保存</button>
                </div>
            </form>
        </div>
    </div><!--/row-fluid-->
</div><!--/container-->
</body>
</html>
