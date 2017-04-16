<%@ page language="java" pageEncoding="utf-8" %>

<!DOCTYPE HTML >
<html>
<head>

    <title>添加班级组织</title>

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
        var canSumbit = false;
        $("#sno_tip").hide();

        $("#sno").keyup(function () {
            var sno = $(this).val();
            $.ajax({
                type: "post",
                url: "/reservation/admin.do?flag=checkBySno",
                data: {"sno": sno},
                success: function (data) {
                    //alert(data);
                    data = eval("(" + data + ")");
                    if (data.code == "0") {
                        canSumbit = true;
                        $("#sno_tip").hide();
                    } else if (data.code == "-1") {
                        canSumbit = false;
                        $("#sno_tip").html("账号已存在");
                        $("#sno_tip").show();
                    } else {
                        canSumbit = false;
                        $("#sno_tip").html(code.msg);
                        $("#sno_tip").show();
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {

                }
            });
        });

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

        $("#submit").click(function () {
            if (!canSumbit) {
                return false;
            }
        });
    });
</script>
<body>
<div class="container">
    <div class="row-fluid">
        <div class="alert alert-block">
            <strong>
                新增班级组织
            </strong>
        </div>

        <!-- Input and Textarea-->
        <div class="offset3 span6">
            <form action="/reservation/admin.do?flag=addStudent" method="post">
                <label>账号（一旦设置不可修改）：</label>
                <input id="sno" class="border-radius-none" name="sno" type="text" placeholder="请填写账号"
                       required="required"/>
                <span id="sno_tip" style="color:red;"></span>

                <label>初始化密码：</label>
                <input class="border-radius-none" name="spassword" type="password" placeholder="请填写初始化密码"
                       value="zjdxrjxy" required="required"/>
                <input id="showpassword" type="button" class="btn" value="显示密码"/>

                <label>组织名称：</label>
                <input class="border-radius-none" name="sname" type="text" placeholder="请填写组织名称" required="required"/>

                <%--
                <label>性别：</label>
                <label class="radio inline">
                    <input type="radio" name="ssex" value="1">男</input>
                </label>
                <label class="radio inline">
                    <input type="radio" name="ssex" value="2">女</input>
                </label>

                <label>专业：</label>
                <input class="border-radius-none" name="smajor" type="text" placeholder="请填写专业" />

                <label>班级：</label>
                <input class="border-radius-none" name="sclasscode" type="text" placeholder="请填写班级" />

                <label>联系电话：</label>
                <input class="border-radius-none" name="sphone" type="text" placeholder="请填写联系电话" />
                 --%>

                <label>备注信息：</label>
                <textarea class="span12 border-radius-none" name="sremark" rows="6" placeholder="请填写备注信息"></textarea>

                <label>状态：
                    <input type="checkbox" name="status" checked="checked"/>有效
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
