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
    <title>ADMIN OF ACCS</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
            <tr id="idRow${user.id}">
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.role}</td>
                <td>
                    <button type="submit" onclick="forBtn(${user.id})">Delete</button>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a class="btn btn-outline-info" href="/"><s:message code="home"/> </a>
</div>
<script>
    function forBtn(id){
        $.ajax({
            url: "${pageContext.request.contextPath}/admin?userId=" + id,
            method: 'POST',
            cache: false,
            type: "text/json",

            success: function (res) {
                console.log(res);
                $("#idRow"+id).html("");
            },
            error: function (res) {

            }
        })
            .always(function () {

            })
            .then(function () {

            });
    }

</script>
</body>
</html>
