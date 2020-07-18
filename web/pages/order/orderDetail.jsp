<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--静态包含所有头部信息，包括base、css样式、jQuery--%>
	<%@include file="/pages/common/header.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%--静态替换登录成功的菜单			--%>
		<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
			</tr>
			<c:forEach items="${sessionScope.orderDetail}" var="orderDetail">
				<tr>
					<td>${orderDetail.name}</td>
					<td>${orderDetail.count}</td>
					<td>${orderDetail.price}</td>
					<td>${orderDetail.totalPrice}</td>
				</tr>
			</c:forEach>
		</table>

	</div>
	                            <%--	页脚--%>
	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>