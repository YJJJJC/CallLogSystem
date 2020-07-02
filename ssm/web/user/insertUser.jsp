<%--
  Created by IntelliJ IDEA.
  User: Y-J-C
  Date: 2019/7/1
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>insertUser.jsp</title>
</head>
<body>
<form action='<c:url value="/user/insertUser" />' method="post">
    UserID:<input type="text" name="id" ><br>
    UserName:<input type="text" name="name"><br>
    UserAge:<input type="text" name="age"><br>
    <input type="submit"/>
</form>

</body>
</html>
