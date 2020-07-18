<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%--静态包含所有头部信息，包括base、css样式、jQuery--%>
	<%@include file="/pages/common/header.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
			<%--静态替换登录成功的菜单			--%>
			<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>订单号</td>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>
			<c:forEach items="${sessionScope.myOrders}" var="order">
			<tr>
				<td>${order.orderId}</td>
				<td>${order.createTime}</td>
				<td>${order.price}</td>
				<c:choose>
					<c:when test="${order.status==0}">
						<td>未发货</td>
					</c:when>
					<c:when test="${order.status==1}">
						<td style="width: 200px">
							已发货<span> </span>
							<a href="orderServlet?action=receiveOrder&orderId=${order.orderId}">确认收货</a>
						</td>
					</c:when>
					<c:when test="${order.status==2}">
						<td>订单已完成</td>
					</c:when>
				</c:choose>
				<td><a href="orderServlet?action=orderDetail&orderId=${order.orderId}">查看详情</a></td>
			</tr>
			</c:forEach>
		</table>
		
	
	</div>
	
	                            <%--	页脚--%>
	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>