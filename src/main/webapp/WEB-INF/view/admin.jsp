<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: koala
  Date: 24.03.2021
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
</head>

<body>
<div>
    <table>
        <thead>
        <th>ID</th>
        <th>UserName</th>
        <th>Password</th>
        <th>Roles</th>
        </thead>
        <c:forEach items="${allUsers}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.role}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin" method="post">
                        <input type="hidden" name="userId" value="${user.id}"/>
                        <input type="hidden" name="action" value="delete"/>
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/"><s:message code="home"/> </a>
</div>
</body>
</html>
