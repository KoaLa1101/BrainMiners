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
    <title><s:message code="sign_up"/></title>
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
        <form:form method="POST" modelAttribute="propForm">
            <div class="text-center ml-3 my-3">
                <h6>Select busyness</h6>
                <form:select path="busyness" type="text">
                    <form:option value="_"/>
                    <form:option value="Full day (8 hour)"/>
                    <form:option value="Part of day (4 hour)"/>
                    <form:option value="2/2"/>
                </form:select>
            </div>
            <div class="text-center ml-3 my-3">
                <h6>Select education</h6>
                <form:select path="education" type="text">
                    <form:option value="_"/>
                    <form:option value="Higher education"/>
                    <form:option value="Incomplete higher education"/>
                    <form:option value="Secondary education"/>
                </form:select>
            </div>
            <div class="text-center ml-3 my-3">
                <h6>Select experience</h6>
                <form:select path="experience" type="text">
                    <form:option value="_"/>
                    <form:option value="Haven't an exp"/>
                    <form:option value="Less 2 years"/>
                    <form:option value="More 2 years"/>
                </form:select>
            </div>
            <div class="text-center ml-3 my-3">
                <h6>Select level of English</h6>
                <form:select path="levelOfEnglish" type="text">
                    <form:option value="_"/>
                    <form:option value="A1-A2"/>
                    <form:option value="B1-C1"/>
                    <form:option value="C2 or Native"/>
                </form:select>
            </div>
            <div class="text-center ml-3 my-3">
                <h6>Select salary</h6>
                <form:select path="salaryWork" type="text">
                    <form:option value="_"/>
                    <form:option value="15000 - 25000"/>
                    <form:option value="25001 - 50000"/>
                    <form:option value="more than 50001"/>
                </form:select>
            </div>
            <div class="text-center ml-3 my-3">
                <h6>Select sphere of work</h6>
                <form:select path="sphereOfWork" type="text">
                    <form:option value="_"/>
                    <form:option value="warehouse workers"/>
                    <form:option value="office worker"/>
                    <form:option value="worker with people"/>
                    <form:option value="distant worker"/>
                </form:select>
            </div>
            <button class="btn btn-outline-primary my-3" type="submit"><s:message code="submit"/></button>
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