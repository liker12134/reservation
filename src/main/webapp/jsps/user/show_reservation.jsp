<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="header.jsp"/>

<!-- js -->
<script type="text/javascript">
    $(document).ready(function () {

    });
</script>


<!--容器开始-->
<!--=== Content Part ===-->
<div class="container">
    <div class="row">
        <div class="alert alert-block">
            <strong>
                <a href="/reservation/user.do?flag=welcome">首页</a>
            </strong>
            <span class="divider">/</span>
            <strong>
                ${title}
            </strong>
        </div>

        <div>
            <div class="span4">
                <div class="service clearfix">
                    <i class="icon-resize-small"></i>
                    <div class="desc">
                        <h4>场所</h4>
                        <p>${res.resClass.cname}（容量：${res.resClass.cmax }人以内）</p>
                    </div>
                </div>
            </div>

            <div class="span4">
                <div class="service clearfix">
                    <i class="icon-cogs"></i>
                    <div class="desc">
                        <h4>时间信息</h4>
                        <p>
	                    	<span>
	                    		申请日期：<fmt:formatDate value="${res.rrecordcreatetime}" pattern="yyyy-MM-dd"/> 
	                    	</span>
                            <span>
	                    		借用日期：<fmt:formatDate value="${res.rtargetdate}" pattern="yyyy-MM-dd"/> 
	                    	</span>
                            <br/>
                            <span>
	                    		借用时间：<fmt:formatDate value="${res.tbegintime}" pattern="HH:mm"/> ~ <fmt:formatDate
                                    value="${res.tendtime}" pattern="HH:mm"/>
	                    	</span>
                            <span>
	                    		具体时间：<fmt:formatDate value="${res.rspecificbegintime}"
                                                     pattern="HH:mm"/> ~ <fmt:formatDate value="${res.rspecificendtime}"
                                                                                         pattern="HH:mm"/>
	                    	</span>
                        </p>
                    </div>
                </div>
            </div>


            <div class="span4">
                <div class="service clearfix">
                    <i class="icon-plane"></i>
                    <div class="desc">
                        <h4>申请单位</h4>
                        <p><br/>${res.rapplyunit}</p>
                    </div>
                </div>
            </div>

            <div class="span4">
                <div class="service clearfix">
                    <i class="icon-plane"></i>
                    <div class="desc">
                        <h4>负责人信息</h4>
                        <p>
                            姓名：${res.rchargeperson}
                            <br/>
                            联系电话：${res.rchargetelephone}
                        </p>
                    </div>
                </div>
            </div>

            <div class="span4">
                <div class="service clearfix">
                    <i class="icon-plane"></i>
                    <div class="desc">
                        <h4>联系人信息</h4>
                        <p>
                            姓名：${res.rcontactsperson}
                            <br/>
                            联系电话：${res.rcontacttelephone}
                        </p>
                    </div>
                </div>
            </div>

            <div class="span4">
                <div class="service clearfix">
                    <i class="icon-plane"></i>
                    <div class="desc">
                        <h4>详细内容</h4>
                        <p>
                            ${res.rspecificcontent}
                        </p>
                    </div>
                </div>
            </div>

        </div><!--/row-fluid-->

    </div><!--/row-fluid-->
</div>
<!--/container-->
<!--=== End Content Part ===-->
<!--容器结束-->


<c:import url="footer.jsp"/>