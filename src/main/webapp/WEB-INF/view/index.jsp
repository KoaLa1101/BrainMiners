<%--
  Created by IntelliJ IDEA.
  User: koala
  Date: 24.10.2020
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>BrainMiners</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/css/bootstrap.min.css"
          integrity="sha384-DhY6onE6f3zzKbjUPRc2hOzGAdEf4/Dz+WJwBvEYL/lkkIsI3ihufq9hk9K4lVoK" crossorigin="anonymous">
</head>
<body >

<!--navigation-->
<nav class="navbar sticky-top navbar-expand-lg navbar-light">
    <div class="container-fluid" style="background-color: aliceblue">
        <a href="${pageContext.request.contextPath}/" class="navbar-brand ml-3" style="color: blue"><s:message
                code="header"/></a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarContent">
            <sec:authorize access="isAnonymous()">
                <div class="d-flex justify-content-start">
                    <a class="btn btn-outline-dark my-3" href="${pageContext.request.contextPath}/signIn"><s:message code="sign_in"/></a>
                    <a class="btn btn-outline-dark my-3 ml-3" href="${pageContext.request.contextPath}/signUp" ><s:message
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


<!--Main-->
<main>

    <sec:authorize access="isAuthenticated()">
        <h2>Hello ${myAcc.firstName} ${myAcc.lastName} </h2>
        <sec:authorize access="hasAuthority('EMPLOYEE')">
        <a class="btn btn-outline-link my-3 ml-3" href="${pageContext.request.contextPath}/findEmployee">Find Employer</a>
        </sec:authorize>
        <a class="btn btn-outline-danger my-3 ml-3" href='${pageContext.request.contextPath}/logout'>logout</a>
    </sec:authorize>



</main>

<!-- Footer -->
<footer class="page-footer font-small blue fixed-bottom ml-3 mr-3" style="position: absolute; background-color: cornflowerblue">

    <!-- Copyright -->
    <div class="footer-copyright text-center py-3">© 2020 Copyright:
        <a href="https://vk.com/koala1101"> vk.com/koala1101</a>
    </div>
    <!-- Copyright -->

</footer>


<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/js/bootstrap.bundle.min.js" integrity="sha384-BOsAfwzjNJHrJ8cZidOg56tcQWfp6y72vEJ8xQ9w6Quywb24iOsW913URv1IS4GD" crossorigin="anonymous"></script>

</body>
</html>
