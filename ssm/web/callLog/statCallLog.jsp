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
    <title>通话记录统计结果</title>
    <link rel="stylesheet" type="text/css" href="../../css/my.css">
    <script src="../js/jquery-3.2.0.min.js"></script>
    <script src="../js/echarts.js"></script>
    <script>
        $(function () {
            var myChart = echarts.init(document.getElementById("main"));
            var option = {
                title: {
                    text: '<c:out value="${title}" />'
                },
                tooltip: {},
                legend: {
                    data:['通话次数']
                },
                xAxis: {
                    data: [<c:forEach items="${stat}" var="e">'<c:out value="${e.yearMonth}"/>',</c:forEach>]
                },
                yAxis: {},
                series: [{
                    name: '通话次数',
                    type: 'bar',
                    data: [<c:forEach items="${stat}" var="e">'<c:out value="${e.count}"/>',</c:forEach>]
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        })
    </script>
</head>
<body>
    <table id="t1" border="1px" class="table" style="width: 600px">
        <thead>
            <tr>
                <td>月份</td>
                <td>次数</td>
            </tr>
        </thead>
        <tbody >
            <c:forEach items="${stat}" var="s">
                <tr>
                    <td><c:out value="${s.yearMonth}" /></td>
                    <td><c:out value="${s.count}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div id = "main" style="border: 1px solid cadetblue;width: 600px;height: 400px;"></div>
</body>
</html>
