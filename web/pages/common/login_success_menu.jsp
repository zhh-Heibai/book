<%--
  Created by IntelliJ IDEA.
  User: heng
  Date: 2020/6/27
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
    <a href="orderServlet?action=myOrders">我的订单</a>
    <a href="userServlet?action=logout">注销</a>
    <a href="index.jsp">返回</a>
</div>
