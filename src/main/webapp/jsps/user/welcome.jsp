<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="header.jsp"/>

<!--表格样式-->
<%--
<link rel="stylesheet" type="text/css" href="css/user/component.css" />
 --%>

<!-- js -->
<script type="text/javascript" src="webplugins/back-to-top.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("a[rel=tooltip]").tooltip(); //tip显示

        $(".forAdd").click(function () {
            if (!isLogin) {
                alert("请点击右上角的“登入系统”进行登入！");
                return false;
            }
        });
    });
</script>


<!--容器开始-->
<div class="container page-container">
    <div class="page-content">
        <!--系统说明开始-->
        <div class="alert alert-block">
            <%--
              <p style="font-size:17px;">
                为进一步将会议室借用制度规范化，合理安排资源、保护公共财物。特对会议室借用工作安排如下，请广大师生予以配合：
                <br/>
                1. 会议室网上预约系统只针对一个工作日以后，三周以内的会议室预约安排；
                2. 会议室借用仅面向本学院老师，老师凭浙大统一身份认证系统的用户名和密码登陆借用。
                <br/>
                3. 进行网上预约时请填写完整申请表内各项信息，以便安排开门、关门确切时间，借用信息不确切不真实的申请管理员将予以删除。
                <br/>
                4. 会议室借用采用负责人制度，负责人需保证会议室使用完毕后关闭所有电器设备及门窗，并通知保安锁门。会议室内设备或物品如有损坏或其他损失，将追究相关赔偿责任。会议室使用期间，不得随意拆卸和移动室内设备。
                <br/>
                5. 为提高小型会议室使用效率，各研究所小型会议请尽量使用所室自己管辖的会议室。
                6. 借用的热水瓶、果盘等应及时归还给学院办公室。
                <br/>
                7. 会议室在使用期间，请不要大声喧哗，播放影音文件时需注意控制音量，以免影响到其他师生的正常学习、工作。
                <br/>
                8. 如借用的会议室因故不使用了，请及时登陆系统取消预订，以免影响他人使用。
                <br/>

                感谢大家对该项工作的配合，请大家爱护公共财产，自觉维护秩序。
                <br/>
                如使用过程有问题，请与学院办公室王晗滢老师联系 87952178。

                软件学院
                2015年11月27日

              </p>
               --%>
            ${notice}
        </div>
        <!--系统说明结束-->

        <!--表格说明开始-->
        <div class="alert alert-info alert-block">
            <p>
                ★提示：若显示<strong>“预约”</strong>，说明该时间段<strong>可进行预约</strong>，<strong>登入系统</strong>后便可预约。
                否则说明该时间段已经存在预约，点击可查看预约信息。
            </p>
        </div>
        <!--表格说明结束-->

        <!--表格开始-->
        <div class="">
            <table class="table table-bordered table-striped" style="font-size:15px;">
                <thead>
                <tr>
                    <th colspan="2">场所</th>
                    <%-- 显示所有教室 --%>
                    <c:forEach items="${classList}" var="c">
                        <th>
                            <a rel="tooltip"
                               title='${c.cname}(0-${c.cmax}人)<c:if test="${not empty c.cremark}">(${c.cremark})</c:if>'>
                                    ${c.cname}(0-${c.cmax}人)
                            </a>
                        </th>
                    </c:forEach>
                </tr>
                </thead>

                <%-- 循环每一天的数据 --%>
                <c:forEach items="${reservationModelList}" var="rm">
                    <%-- 循环每一个时间段 --%>
                    <c:forEach items="${rm.timequantum}" var="t">
                        <tr>
                                <%-- 日期每一天只用显示一个，所以循环第一个时间段时显示日期信息 --%>
                            <c:if test="${t.isFirst == 1}">
                                <td>
                                    <fmt:formatDate value="${rm.date}" pattern="yyyy-MM-dd"/>
                                    <br/>
                                        ${rm.week}
                                </td>
                            </c:if>
                            <c:if test="${t.isFirst == 0}">
                                <td>

                                </td>
                            </c:if>
                            <td>
                                <fmt:formatDate value="${t.timewuantum.tbegintime}" pattern="HH:mm"/>
                                -
                                <fmt:formatDate value="${t.timewuantum.tendtime}" pattern="HH:mm"/>
                            </td>
                                <%-- 循环每一个表格项 --%>
                            <c:forEach items="${t.reservations }" var="r" varStatus="status">
                                <td>
                                    <div>
                                            <%-- 可预约 --%>
                                        <c:if test="${empty r}">
                                            <a class="forAdd"
                                               title="${classList[status.index].cname} <fmt:formatDate value='${t.timewuantum.tbegintime}' pattern='HH:mm'/>-<fmt:formatDate value='${t.timewuantum.tendtime}' pattern='HH:mm'/>"
                                               rel="tooltip"
                                               href="/reservation/user.do?flag=forAddReservation&cid=${classList[status.index].cid}&date_=<fmt:formatDate value='${rm.date}' pattern='yyyy-MM-dd'/>&tid=${t.timewuantum.tid}">
                                                <strong class="text-center">预约</strong>
                                            </a>
                                        </c:if>
                                            <%-- 可查询 --%>
                                        <c:if test="${not empty r}">
                                            <a class="show"
                                               title="${r.cname} <fmt:formatDate value='${t.timewuantum.tbegintime}' pattern='HH:mm'/>-<fmt:formatDate value='${t.timewuantum.tendtime}' pattern='HH:mm'/> ${r.rspecificcontent }，负责人：${r.rchargeperson}，负责人电话：${r.rchargetelephone}"
                                               rel="tooltip"
                                               href="/reservation/user.do?flag=showReservation&rid=${r.rid}">
                                                <strong>${r.rapplyunit}</strong>
                                                <fmt:formatDate value="${t.timewuantum.tbegintime}" pattern="HH:mm"/>
                                                -
                                                <fmt:formatDate value="${t.timewuantum.tendtime}" pattern="HH:mm"/>
                                                    ${r.rspecificcontent}
                                            </a>
                                        </c:if>
                                    </div>
                                </td>
                            </c:forEach>
                                <%-- 循环每一个表格项结束 --%>
                        </tr>
                    </c:forEach>
                    <%-- 循环每一个时间段结束 --%>
                </c:forEach>
                <%-- 循环每一天的数据结束 --%>
            </table>
        </div>
        <!--表格结束-->
    </div>

</div>
<!--容器结束-->

<c:import url="footer.jsp"/>