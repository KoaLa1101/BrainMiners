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
<html>
<head>
    <title><s:message code="profile"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/css/bootstrap.min.css"
          integrity="sha384-DhY6onE6f3zzKbjUPRc2hOzGAdEf4/Dz+WJwBvEYL/lkkIsI3ihufq9hk9K4lVoK" crossorigin="anonymous">
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
                <a class="navbar-brand" href="${pageContext.request.contextPath}/profile"> Profile </a>
            </sec:authorize>
            <a class="btn btn-outline-dark my-3 ml-3" href='${s:mvcUrl("MC#change").arg(0, "ru_RU").build()}'>Eng</a>
            <a class="btn btn-outline-dark my-3 ml-3" href='${s:mvcUrl("MC#change").arg(0, "en_US").build()}'>Рус</a>
        </div>
    </div>
</nav>

<main class="vh-100 bg-gradient-primary" style="background-color: darkblue">
    <div class="d-flex  align-items-center justify-content-center ml-3 mr-3">
        <div class="vh-100 d-flex flex-column align-items-center justify-content-center h4"
             style="background-color: aliceblue; width: 20%; ">
            <p class="ml-3 mr-3"><s:message code="firstName"/>: <br> ${myAcc.firstName} <br></p>
            <p class="ml-3 mr-3"><s:message code="lastName"/>: <br> ${myAcc.lastName} <br></p>
            <p class="ml-3 mr-3"><s:message code="username"/>: <br> ${myAcc.username} <br></p>
            <p class="ml-3 mr-3"><s:message code="role"/>: <br> ${myAcc.role} <br></p>
            <a class="btn btn-link btn-outline-primary btn-lg ml-auto mr-auto"
               href="${pageContext.request.contextPath}/profile/edit" role="button"><s:message code="editProfile"/> </a>
            <br>
            <sec:authorize access="hasAuthority('EMPLOYER')">
                <a class="btn btn-link btn-outline-primary btn-lg ml-auto mr-auto"
                   href="${pageContext.request.contextPath}/profile/myProps" role="button"><s:message
                        code="props"/> </a>
                <br>
                <a class="btn btn-outline-info btn-lg ml-auto mr-auto"
                   href="${pageContext.request.contextPath}/profile/myMes" role="button"><s:message code="my_mes"/></a>
                <br>
            </sec:authorize>
            <a class="btn btn-lg btn-danger ml-auto mr-auto" href="${pageContext.request.contextPath}/logout"
               role="button"><s:message code="logout"/> </a>

        </div>
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