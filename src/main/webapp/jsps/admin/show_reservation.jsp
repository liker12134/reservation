<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="main_head.jsp"/>

<!-- 前端分页开始 -->
<link rel="stylesheet" href="css/common/jquery.dataTables.css" type="text/css"></link>
<style type="text/css" class="init"></style>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript" class="init"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#reservationList').dataTable();

        //点击"有效"/"无效"修改按钮
        $(".changestatus").click(function () {
            //alert(this.value);
            var status = 1;
            var rid = $(this).attr("name");
            if ("无效" == this.value) {
                status = 0;
                // 	alert(this.value+"  status="+status+"  rid="+rid);
            }
            var $button = $(this);
            //ajax调用,修改状态
            $.ajax({
                type: "post",
                async: false,
                url: "/reservation/admin.do?flag=updateReservationStatus",
                data: {"rid": rid, "status": status},
                success: function (data) {
                    //alert(data);
                    data = eval("(" + data + ")");
                    var code = data.code;
                    //alert("code="+code);
                    if (code == 0) {
                        //设置跳转路径与参数
                        //var href ="/reservation/admin.do?flag=showReservation";
                        //href 的特殊处理
                        //页面跳转
                        //location.href = href;
                        if (status == 0) { //现在被改成了无效
                            $button.val("有效");
                            $button.parent().prev().text("无效");
                        } else if (status == 1) { //现在被改成了有效
                            $button.val("无效");
                            $button.parent().prev().text("有效");
                        }
                    }
                    else {
                        alert("状态修改失败：" + data.msg);
                    }

                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("请求发送失败！");
                }
            });
        });

    });
</script>
<!-- 前端分页结束 -->

<div class="row-fluid">
    <div class="page-header">
        <h1>用户预约维护
            <small>用户预约记录管理</small>
        </h1>
    </div>
    <table id="reservationList" class="display table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th>教室</th>
            <th>申请时间</th>
            <th>预约时间段</th>
            <%--<th>星期</th> --%>
            <th>申请单位</th>
            <th>负责人</th>
            <th>负责人电话</th>
            <th>联系人</th>
            <th>联系人电话</th>
            <th>详细信息</th>
            <%--<th>rid</th>--%>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="r" items="${resList}">
            <tr class="list-roles">
                <td>${r.cname}</td>
                <td>
                    <fmt:formatDate value='${r.rrecordcreatetime}' pattern='yyyy-MM-dd HH:mm:ss'/>
                </td>
                <td>
                    <fmt:formatDate value='${r.rtargetdate}' pattern='yyyy-MM-dd'/>

                    <br/>
                    <fmt:formatDate value='${r.tbegintime}' pattern='HH:mm'/>
                    -
                    <fmt:formatDate value='${r.tendtime}' pattern='HH:mm'/>
                </td>
                    <%--<td>${r.rweeknum}</td> --%>
                <td>${r.rapplyunit}</td>
                <td>${r.rchargeperson}</td>
                <td>${r.rchargetelephone}</td>
                <td>${r.rcontactsperson}</td>
                <td>${r.rcontacttelephone}</td>
                <td>${r.rspecificcontent}</td>
                    <%--  <td>${r.rid}</td> --%>

                <td>
                    <c:if test="${r.rstatus == '1'}">
                        有效
                    </c:if>
                    <c:if test="${r.rstatus == '0'}">
                        无效
                    </c:if>
                </td>
                <td>
                    <c:if test="${r.rstatus == '1'}">
                        <input name="${r.rid}" type="button" class="btn changestatus" value="无效"/>
                    </c:if>
                    <c:if test="${r.rstatus == '0'}">
                        <input name="${r.rid}" type="button" class="btn changestatus" value="有效"/>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a id="refresh" href="/reservation/admin.do?flag=showReservation" class="btn btn-success">刷新</a>
</div>

<c:import url="main_foot.jsp"/>