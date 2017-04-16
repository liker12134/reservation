<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>

    <title>出错页面</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link rel="stylesheet" href="bootstrap2/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="bootstrap2/css/bootstrap-responsive.min.css" type="text/css">
    <link rel="stylesheet" href="bootstrap2/css/bootstrap-overrides.css" type="text/css"></link>


    <style>
        * {
            line-height: 1.2;
            margin: 0;
        }

        html {
            color: #888;
            display: table;
            font-family: sans-serif;
            height: 100%;
            text-align: center;
            width: 100%;
        }

        body {
            display: table-cell;
            vertical-align: middle;
            margin: 2em auto;
        }

        h1 {
            color: #555;
            font-size: 2em;
            font-weight: 400;
        }

        p {
            margin: 0 auto;
            width: 280px;
        }

        @media only screen and (max-width: 280px) {

            body, p {
                width: 95%;
            }

            h1 {
                font-size: 1.5em;
                margin: 0 0 0.3em;
            }

        }
    </style>

<body>
<h1>错误页面</h1>
<p><i>${err}</i></p>

<c:choose>
    <c:when test="${type == 'loginadmin'}">
        <p><a href="/reservation/admin.do?flag=forlogin">管理员登录</a></p>
    </c:when><%-- 管理员错误页面  --%>
    <c:when test="${type == 'admin'}">
        <p><a href="/reservation/admin.do?flag=main">管理员操作主页面</a></p>
    </c:when>
    <c:otherwise><%-- 默认为用户主页面（欢迎页） --%>
        <p><a href="/reservation/user.do?flag=welcome">返回首页</a></p>
    </c:otherwise>
</c:choose>

</body>
</html>