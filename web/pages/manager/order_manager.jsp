<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%--静态包含所有头部信息，包括base、css样式、jQuery--%>
	<%@include file="/pages/common/header.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
		<%--静态包含manager	管理模块的菜单		--%>
		<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<th>日期</th>
				<th>金额</th>
				<th>详情</th>
				<th>发货</th>
			</tr>
			<c:forEach items="${sessionScope.allOrders}" var="order">
			<tr>
				<td>${order.createTime}</td>
				<td>${order.price}</td>
				<td><a href="orderServlet?action=orderDetail&orderId=${order.orderId}">查看详情</a></td>
				<td><a href="orderServlet?action=sendOrder&orderId=${order.orderId}">
						<c:if test="${order.status==0}">
							点击发货
						</c:if>
						<c:if test="${order.status==1}">
							已发货
						</c:if>
						<c:if test="${order.status==2}">
							已完成
						</c:if>
				</a></td>
			</tr>
			</c:forEach>
		</table>
	</div>
	
	<%--	页脚--%>
	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>