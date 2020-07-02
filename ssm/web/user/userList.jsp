<%--
  Created by IntelliJ IDEA.
  User: Y-J-C
  Date: 2019/6/30
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>用户列表</title>
    <style type="text/css">
        #t1{
            background-color: blanchedalmond;
        }
    </style>
</head>
<body>
<table id="t1" border="1px">
    <tr>
        <tb>ID</tb>
        <tb>NAME</tb>
        <tb>AGE</tb>
    </tr>
    <c:forEach items="${allUsers}" var="u">
        <tr>
            <td><c:out value="${u.id}" /></td>
            <td><c:out value="${u.name}" /></td>
            <td><c:out value="${u.age}" /></td>
            <td><a href='<c:url value="/user/deleteUser?uid=${u.id}"/> '>删除</a></td>
            <td><a href='<c:url value="/user/editUser?uid=${u.id}"/> '>修改</a></td>
        </tr>
    </c:forEach>
    <td><a href='<c:url value="/user/insertUser.jsp"/> '>插入用户</a></td>
    <tr>
        <td colspan="5" style="text-align:right;">
            <c:forEach begin="1" end="${pages}" step="1" var="i">
                <c:if test="${i == requestScope.pn}">
                    [<c:out value="${i}"/>]&nbsp;&nbsp;
                </c:if>
                <c:if test="${i != requestScope.pn}">
                    <a href='<c:url value="/user/findPage?pn=${i}"/> '><c:out value="${i}"/></a>&nbsp;&nbsp;
                </c:if>
            </c:forEach>
        </td>

    </tr>
</table>
</body>
</html>
