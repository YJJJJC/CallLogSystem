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
    <title>通话记录</title>
    <link rel="stylesheet" type="text/css" href="../css/my.css">
    <script type="text/javascript" src="../js/jquery-3.2.0.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#btnCleanTable").click(function () {
                $("#t1 tbody").empty();
                $.getJSON("/callLog/json/findCallLog",function(data){
                    $.each(data,function (i,obj) {
                        var str = "<tr><td>" + obj.caller + "</td>";
                        str = str + "<td>" + obj.callerName + "</td>";
                        str = str + "<td>" + obj.callee + "</td>";
                        str = str + "<td>" + obj.calleeName + "</td>";
                        str = str + "<td></td>";
                        str = str + "<td>" + obj.callTime + "</td>";
                        str = str + "<td>" + obj.callDuration + "</td>";
                        str = str + "</tr>";
                        $("#t1 tbody").append(str);
                    });
                })
            });
        })
    </script>
</head>
<body>
    <input id="btnCleanTable" type="button" value="清除表格"><<br>
    <table id="t1" border="1px" class="table" style="width: 800px">
        <thead>
            <tr>
                <td>主叫</td>
                <td>主叫姓名</td>
                <td>被叫</td>
                <td>被叫姓名</td>
                <td>标记</td>
                <td>通话时间</td>
                <td>通话时长</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${callLogs}" var="log">
                <tr>
                    <td><c:out value="${log.caller}" /></td>
                    <td><c:out value="${log.callerName}" /></td>
                    <td><c:out value="${log.callee}" /></td>
                    <td><c:out value="${log.calleeName}" /></td>
                    <td>
                        <c:if test="${log.caller==param.caller}">呼出</c:if>
                        <c:if test="${log.caller!=param.caller}">接收</c:if>
                    </td>
                    <td><c:out value="${log.callTime}" /></td>
                    <td><c:out value="${log.callDuration}" /></td>
                </tr>
            </c:forEach>
        </tbody>

        <%--<tr>--%>
            <%--<td colspan="5" style="text-align:right;">--%>
                <%--<c:forEach begin="1" end="${pages}" step="1" var="i">--%>
                    <%--<c:if test="${i == requestScope.pn}">--%>
                        <%--[<c:out value="${i}"/>]&nbsp;&nbsp;--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${i != requestScope.pn}">--%>
                        <%--<a href='<c:url value="/user/findPage?pn=${i}"/> '><c:out value="${i}"/></a>&nbsp;&nbsp;--%>
                    <%--</c:if>--%>
                <%--</c:forEach>--%>
            <%--</td>--%>
        <%--</tr>--%>
    </table>
</body>
</html>
