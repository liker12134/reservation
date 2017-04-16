<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML >
<html>
<head>

    <title>修改教室</title>

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
                修改教室
            </strong>
        </div>

        <!-- Input and Textarea-->
        <div class="offset3 span6">
            <form action="/reservation/admin.do?flag=updateClass" method="post">

                <input name="cid" value="${classDetail.cid}" type="hidden"/>


                <label>教室名称：</label>
                <input class="border-radius-none" name="cname" value="${classDetail.cname}" type="text"
                       placeholder="请填写教室名称"/>

                <label>最大容量：</label>
                <input class="border-radius-none" type="number" name="cmax" value="${classDetail.cmax}"
                       placeholder="请填写最大容量"/>

                <label>备注信息：</label>
                <textarea class="span12 border-radius-none" name="cremark" rows="6"
                          placeholder="请填写备注信息">${classDetail.cremark}</textarea>

                <label>
                    教室状态：
                    <c:if test="${classDetail['cstatus'] == '1'}">
                        <input type="checkbox" name="cstatus" checked="checked"/>有效
                    </c:if>
                    <c:if test="${classDetail['cstatus'] == '0'}">
                        <input type="checkbox" name="cstatus"/>有效
                    </c:if>
                </label>

                <label>操作者：</label>
                <input class="border-radius-none" type="text" value="${admin.maccount}" readonly="readonly"/>

                <div>
                    <button id="submit" type="submit" class="btn btn-success">保存</button>
                </div>
            </form>
        </div>
    </div><!--/row-fluid-->
</div><!--/container-->
</body>
</html>
