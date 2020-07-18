<%--
  Created by IntelliJ IDEA.
  User: heng
  Date: 2020/6/27
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--写base标签，永远固定相对路径跳转的结果-->
<%
    String base=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()
            +request.getContextPath()+"/";
    pageContext.setAttribute("basePath",base);
%>
<%--${pageContext.getAttribute("base")}--%>
<base href="<%=base%>>">

<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>