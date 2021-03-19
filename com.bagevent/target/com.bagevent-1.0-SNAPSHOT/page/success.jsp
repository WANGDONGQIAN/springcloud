<%--
  Created by IntelliJ IDEA.
  User: wangdongqian
  Date: 2020/5/27
  Time: 9:27 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table border="1" width="500" align="center">
        <tr>
            <th>login_id</th>
            <th>user_id</th>
            <th>login_ip</th>
            <th>login_time</th>
        </tr>
        <%--数据行--%>
        <c:forEach items="${data}" var="user" varStatus="s">
            <c:if test="${s.count % 2 != 0}">

                <tr bgcolor="red">
                    <td>${user.loginId}</td>
                    <td>${user.userId}</td>
                    <td>${user.loginIp}</td>
                    <td>${user.loginTime}</td>
                </tr>
            </c:if>
            <c:if test="${s.count % 2 == 0}">

                <tr  bgcolor="green">
                    <td>${user.loginId}</td>
                    <td>${user.userId}</td>
                    <td>${user.loginIp}</td>
                    <td>${user.loginTime}</td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</body>
</html>
