<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>图书管理</title>
	<%--静态包含所有头部信息，包括base、css样式、jQuery--%>
	<%@include file="/pages/common/header.jsp"%>
	<%--<script type="text/javascript">
		$(function () {
			$("#searchPageBtn").click(function () {
				var pageNo=$("#pn_input").val();
				location.href="http://localhost:8080/book/manager/bookServlet?action=page&pageNo="+pageNo;
			})
		})
	</script>--%>
</head>
<body>

<div id="header">
	<img class="logo_img" alt="" src="static/img/logo.gif" >
	<span class="wel_word">图书管理系统</span>
	<%--静态包含manager	管理模块的菜单		--%>
	<%@include file="/pages/common/manager_menu.jsp"%>
</div>

<div id="main">
	<table>
		<tr>
			<th>名称</th>
			<th>价格</th>
			<th>作者</th>
			<th>销量</th>
			<th>库存</th>
			<th colspan="2">操作</th>
		</tr>
		<c:forEach items="${requestScope.page.items}" var="book">
			<tr>
				<td>${book.name}</td>
				<td>${book.price}</td>
				<td>${book.author}</td>
				<td>${book.sales}</td>
				<td>${book.stock}</td>
				<td><a href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a> </td>
				<td><a href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a> </td>
			</tr>
		</c:forEach>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
		</tr>
	</table>
	<%@include file="/pages/common/page.jsp"%>

</div>
<%--	页脚--%>
<%@include file="/pages/common/foot.jsp"%>

</body>
</html>