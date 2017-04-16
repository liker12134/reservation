<%@ page language="java" pageEncoding="utf-8" %>


<!DOCTYPE HTML>
<html>
<head>

    <title>input</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

</head>

<body>
<form action="/reservation/test.do?flag=test" method="post">
    <div>
        <span>username:</span>
        <input type="text" name="roleid" placeholder="请输入roleid..." required="required"/>
    </div>
    <div>
        <input type="submit" value="test提交"/>
    </div>
</form>
</body>
</html>
