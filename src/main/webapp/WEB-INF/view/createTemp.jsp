<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: koala
  Date: 20.03.2021
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>BrainMiners</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/css/bootstrap.min.css"
          integrity="sha384-DhY6onE6f3zzKbjUPRc2hOzGAdEf4/Dz+WJwBvEYL/lkkIsI3ihufq9hk9K4lVoK" crossorigin="anonymous">
    <script src="https://cdn.tiny.cloud/1/xh32za4tf46uoqx53xou51b07063vnglx7te4mq2jyxky1jd/tinymce/5/tinymce.min.js"
            referrerpolicy="origin"></script>
</head>
<body>

<!--navigation-->
<nav class="navbar sticky-top navbar-expand-lg navbar-light">
    <div class="container-fluid" style="background-color: aliceblue">
        <a href="${pageContext.request.contextPath}/" class="navbar-brand ml-3" style="color: blue"><s:message
                code="header"/></a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent"
                aria-controls="navbarContent" aria-expanded="false">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarContent">
            <sec:authorize access="isAnonymous()">
                <div class="d-flex justify-content-start">
                    <a class="btn btn-outline-dark my-3" href="${pageContext.request.contextPath}/signIn"><s:message
                            code="sign_in"/></a>
                    <a class="btn btn-outline-dark my-3 ml-3"
                       href="${pageContext.request.contextPath}/signUp"><s:message
                            code="sign_up"/></a>
                </div>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/profile">
                    Profile
                </a>
            </sec:authorize>
            <a class="btn btn-outline-dark my-3 ml-3" href='${s:mvcUrl("MC#change").arg(0, "ru_RU").build()}'>Eng</a>
            <a class="btn btn-outline-dark my-3 ml-3" href='${s:mvcUrl("MC#change").arg(0, "en_US").build()}'>Рус</a>
        </div>
    </div>
</nav>
<!-- Main -->
<main>
    <div class="text-center ml-3 my-3">
        <form:form method="POST" modelAttribute="newTemp">
            <h2>Let's create template for other Employee </h2>
            <br><br>
            <s:message code="valueChange"/>
            <form:textarea path="mes" cssStyle="width: 100%; height: 600px"/>
            <script>
                tinymce.init({
                    selector: 'textarea',
                    plugins: 'a11ychecker advcode casechange formatpainter linkchecker autolink lists checklist media mediaembed pageembed permanentpen powerpaste table advtable tinymcespellchecker',
                    toolbar: 'a11ycheck addcomment showcomments casechange checklist code formatpainter pageembed permanentpen table',
                    toolbar_mode: 'floating',
                    tinycomments_author: 'Koala1101'
                });
            </script>
            <br><br>
            <form:input path="userList" type="text" hidden="hidden" value="${myAcc.id}"/>

            <button class="btn btn-outline-success" type="submit"><s:message code="submit"/></button>
        </form:form>
    </div>
</main>
<footer class="page-footer font-small blue fixed-bottom ml-3 mr-3"
        style="position: absolute; background-color: cornflowerblue">

    <!-- Copyright -->
    <div class="footer-copyright text-center py-3">© 2020 Copyright:
        <a href="https://vk.com/koala1101"> vk.com/koala1101</a>
    </div>
    <!-- Copyright -->

</footer>
</body>
</html>