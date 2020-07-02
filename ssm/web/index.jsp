<%--
  Created by IntelliJ IDEA.
  User: Y-J-C
  Date: 2019/6/30
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  WELCOME TO MY FIRST SSM<br>
  <a href='<c:url value="/user/findAll"/>'>查询全部用户信息</a><br>
  <a href='<c:url value="/callLog/toFindCallLogPage"/>'>查询指定通话记录</a><br>
  <a href='<c:url value="/callLog/findAll"/>'>查询全部通话记录</a><br>
  <a href='<c:url value="/callLog/toFindLatestCallLogPage"/>'>查询最近通话记录</a><br>
  <a href='<c:url value="/callLog/toStatCallLog"/>'>统计通话记录</a><br>
  <a href='<c:url value="/monitor/monitorPage"/>'>监控进程</a><br>
  </body>
</html>
