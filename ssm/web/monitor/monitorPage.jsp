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
    <link rel="stylesheet" type="text/css" href="../../css/my.css">
    <script type="text/javascript" src="../js/jquery-3.2.0.min.js"></script>
    <script type="text/javascript">

        //javaScript定义函数的语法
        function gerHeartbeatInfo(){
            $("#div1").empty();
            $.getJSON("/json/monitor/getMonitorInfo",function(data){
                $("#div1").append(data);
            });
        }

        $(function(){
            setInterval(gerHeartbeatInfo,1000);
        })
    </script>
</head>
<body>
    <div id="div1" style="border: 1px solid cadetblue;width: 600px;height: 300px">
    </div>
</body>
</html>
