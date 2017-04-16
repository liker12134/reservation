<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="main_head.jsp"/>

<script type="text/javascript">
    $(document).ready(function () {
        //跳转到新增管理员页面
        $("#new").click(function () {
            //创建窗体
            var diag = new Dialog();
            //设置高度和宽度
            diag.Width = 850;
            diag.Height = 500;
            //设置窗体出现的位置
            diag.Top = "20%";
            diag.Left = "50%";
            //设置窗体标题与访问地址
            diag.Title = "添加管理员";
            diag.URL = "/reservation/admin.do?flag=forAddManager";
            //关闭时重新加载
            diag.CancelEvent = function () {
                location.href = "/reservation/admin.do?flag=showManager";
                diag.close();
            };
            //显示窗体
            diag.show();
        });

        //跳转到修改页面
        $(".modified").click(function () {
            //获取ID
            var maccount = $(this).attr("name");
            //创建窗体
            var diag = new Dialog();
            //设置高度和宽度
            diag.Width = 850;
            diag.Height = 500;
            //设置窗体出现的位置
            diag.Top = "20%";
            diag.Left = "50%";
            //设置窗体标题与访问地址
            diag.Title = "修改管理员";
            diag.URL = "/reservation/admin.do?flag=forUpdateManager&maccount=" + maccount;
            //关闭时重新加载
            diag.CancelEvent = function () {
                location.href = "/reservation/admin.do?flag=showManager";
                diag.close();
            };
            //显示窗体
            diag.show();
        });

    });
</script>

<div class="row-fluid">
    <div class="page-header">
        <h1>管理员维护
            <small>管理员数据管理</small>
        </h1>
    </div>
    <table class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th>ID</th>
            <th>用户名</th>
            <th>密码（MD5加密）</th>
            <th>管理员备注</th>

            <th>操作</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="m" items="${managerList}">
            <tr class="list-roles">
                <td>${m.mid}</td>
                <td>${m.maccount}</td>
                <td>${m.mpassword}</td>
                <td>${m.mremark}</td>
                <td>
                    <c:if test="${m.maccount == admin.maccount}">
                        <input name="${m.maccount}" type="button" class="btn modified" value="修改"/>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a id="new" href="#" class="btn btn-success">新增</a>
    <a id="refresh" href="/reservation/admin.do?flag=showManager" class="btn btn-success">刷新</a>
</div>

<c:import url="main_foot.jsp"/>