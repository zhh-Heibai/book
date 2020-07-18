<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: heng
  Date: 2020/6/29
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav" align="left">
   <c:if test="${requestScope.page.pageNo >1}">
    <a href="${requestScope.page.url}&pageNo=1">首页</a>
    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
   </c:if>
<%-- 页码输出的开始--%>
    <c:choose>
<%--    页码少于等于5时--%>
        <c:when test="${requestScope.page.pageTotal<=5}">
            <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
                <c:if test="${i==requestScope.page.pageNo}">
                【${i}】
                </c:if>
                <c:if test="${i!=requestScope.page.pageNo}">
                <a href="${requestScope.page.url}&pageNo=${i}">${i}
                </c:if>
            </c:forEach>
        </c:when>
<%--                    页面大于5时--%>
        <c:when test="${requestScope.page.pageTotal>5}">
            <c:choose>
                    <%--前三个页面的显示--%>
                    <c:when test="${requestScope.page.pageNo<=3}">
                        <c:forEach begin="1" end="5" var="i">
                            <c:if test="${i==requestScope.page.pageNo}">
                            【${i}】
                            </c:if>
                            <c:if test="${i!=requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}
                            </c:if>
                        </c:forEach>
                    </c:when>
                    <%--后三个页面的显示--%>
                    <c:when test="${requestScope.page.pageNo>=requestScope.page.pageTotal-2}">
                        <c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">
                            <c:if test="${i==requestScope.page.pageNo}">
                            【${i}】
                            </c:if>
                            <c:if test="${i!=requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}
                            </c:if>
                        </c:forEach>
                    </c:when>
                    <%--其他情况--%>
                    <c:otherwise>
                        <c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}" var="i">
                            <c:if test="${i==requestScope.page.pageNo}">
                            【${i}】
                            </c:if>
                            <c:if test="${i!=requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}
                            </c:if>
                        </c:forEach>
                    </c:otherwise>
            </c:choose>

        </c:when>
    </c:choose>

        <%-- 页码输出的结束--%>



        <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
        </c:if>
        共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
        到第<input value="${empty param.pageNo?1:param.pageNo}" name="pn" id="pn_input"/>页
        <input id="searchPageBtn" type="button" value="确定">
        <script type="text/javascript">
            $(function () {
                $("#searchPageBtn").click(function () {
                    var pageNo=$("#pn_input").val();
                    var pageTotal=${requestScope.page.pageTotal};
                    if (pageNo<1||pageNo>pageTotal){
                        alert("跳转页面不合法");
                        location.href="${pageContext.getAttribute("basePath")}${requestScope.page.url}&pageNo=1";
                    }else {
                        location.href="${pageContext.getAttribute("basePath")}${requestScope.page.url}&pageNo="+pageNo;
                    }
                })
            })
        </script>
</div>

