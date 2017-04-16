<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="main_head.jsp"/>

<script type="text/javascript">
    $(document).ready(function () {
        //跳转到新增教室页面
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
            diag.Title = "添加教室";
            diag.URL = "/reservation/admin.do?flag=forAddClass";
            //关闭时重新加载
            diag.CancelEvent = function () {
                location.href = "/reservation/admin.do?flag=showClass";
                diag.close();
            };
            //显示窗体
            diag.show();
        });

        //跳转到修改页面
        $(".modified").click(function () {
            //获取ID
            var id = $(this).attr("name");
            //创建窗体
            var diag = new Dialog();
            //设置高度和宽度
            diag.Width = 850;
            diag.Height = 500;
            //设置窗体出现的位置
            diag.Top = "20%";
            diag.Left = "50%";
            //设置窗体标题与访问地址
            diag.Title = "修改教室";
            diag.URL = "/reservation/admin.do?flag=forUpdateClass&cid=" + id;
            //关闭时重新加载
            diag.CancelEvent = function () {
                location.href = "/reservation/admin.do?flag=showClass";
                diag.close();
            };
            //显示窗体
            diag.show();
        });
    });
</script>

<div class="row-fluid">
    <div class="page-header">
        <h1>教室维护
            <small>活动室数据管理</small>
        </h1>
    </div>
    <table class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th>ID</th>
            <th>教室名称</th>
            <th>最大容量</th>
            <th>教室备注</th>
            <th>教室状态</th>
            <th>操作者</th>
            <th>修改时间</th>

            <th>操作</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="c" items="${classList}">
            <tr class="list-roles">
                <td>${c.cid}</td>
                <td>${c.cname}</td>
                <td>${c.cmax}</td>
                <td>${c.cremark}</td>
                <td>
                    <c:if test="${c.cstatus == '1'}">
                        有效
                    </c:if>
                    <c:if test="${c.cstatus == '0'}">
                        无效
                    </c:if>
                </td>
                <td>${c.resManager.maccount}</td>
                <td>${c.cmodifiedtime}</td>

                <td>
                    <input name="${c.cid}" type="button" class="btn modified" value="修改"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a id="new" href="#" class="btn btn-success">新增</a>
    <a id="refresh" href="/reservation/admin.do?flag=showClass" class="btn btn-success">刷新</a>
</div>

<c:import url="main_foot.jsp"/>