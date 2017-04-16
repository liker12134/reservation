<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="header.jsp"/>

<style>
    .red {
        color: red;
    }
</style>

<!-- js -->
<script type="text/javascript">
    $(document).ready(function () {
        $("#submit").click(function () {
            //提交前进行数据校验

        });

        $("label").each(function () {
            $(this).html($(this).html() + "<span class='red'>(*)</span>");
        });

        $("#rchargeperson").blur(function () {
            if ($("#rcontactsperson").val() == "") {
                $("#rcontactsperson").val($(this).val());
            }
        });

        $("#rchargetelephone").blur(function () {
            if ($("#rcontacttelephone").val() == "") {
                $("#rcontacttelephone").val($(this).val());
            }
        });


    });
</script>

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

        <!-- Input and Textarea-->
        <div class="offset3 span6">
            <div class="headline">
                <h3>会议室申请</h3>
            </div>

            <form action="/reservation/user.do?flag=addReservation" method="post">
                <label>场所</label>
                <select name="cid">
                    <option value="${rclass.cid}" selected="selected">${rclass.cname}</option>
                </select>

                <label>预约日期</label>
                <input class="border-radius-none" type="text" name="rtargetdate"
                       value="<fmt:formatDate value='${rtargetdate}' pattern='yyyy-MM-dd'/>" readonly="readonly"/>

                <label>时间段</label>
                <input type="hidden" id="=timequantumid" name="timequantumid" value="${timequantum.tid}"/>
                <input type="text"
                       value='<fmt:formatDate value="${timequantum.tbegintime}" pattern="HH:mm"/>'
                       readonly="readonly"
                />
                -
                <input type="text"
                       value='<fmt:formatDate value="${timequantum.tendtime}" pattern="HH:mm" />'
                       readonly="readonly"
                />

                <label>具体时间</label>
                <input class="border-radius-none" type="time" name="rspecificbegintime"
                       value="<fmt:formatDate value='${timequantum.tbegintime}' pattern='HH:mm'/>"/>
                <span>~</span>
                <input class="border-radius-none" type="time" name="rspecificendtime"
                       value="<fmt:formatDate value='${timequantum.tendtime}' pattern='HH:mm'/>"/>

                <label>申请单位</label>
                <input class="border-radius-none" name="rapplyunit" type="text" placeholder="请填写申请单位"
                       required="required"/>

                <label>负责人</label>
                <input id="rchargeperson" class="border-radius-none" name="rchargeperson" type="text" value=""
                       placeholder="请填写负责人" required="required"/>

                <label>负责人电话</label>
                <input id="rchargetelephone" class="border-radius-none" name="rchargetelephone" type="text" value=""
                       placeholder="请填写负责人电话" required="required"/>

                <label>联系人</label>
                <input id="rcontactsperson" class="border-radius-none" name="rcontactsperson" type="text" value=""
                       placeholder="请填写联系人" required="required"/>

                <label>联系人电话</label>
                <input id="rcontacttelephone" class="border-radius-none" name="rcontacttelephone" type="text" value=""
                       placeholder="请填写联系人电话" required="required"/>

                <label>详细内容</label>
                <textarea class="span12 border-radius-none" name="rspecificcontent" rows="8"
                          placeholder="请填写详细内容"></textarea>

                <button id="submit" type="submit" class="btn btn-success">保存</button>
            </form>
        </div>
    </div><!--/row-fluid-->
</div>
<!--/container-->
<!--=== End Content Part ===-->

<c:import url="footer.jsp"/>