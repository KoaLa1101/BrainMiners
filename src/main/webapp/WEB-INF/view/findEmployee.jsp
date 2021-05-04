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


<!--Main-->
<main>

    <h3>Let's find employee for ${myAcc.username}</h3>
    <div class="d-flex justify-content-around">
        <c:if test="${empty allEmployee}"><br><br><h2>Not Employee</h2> </c:if>
        <c:if test="${not empty allEmployee}">
        <table style="background-color: aliceblue">
            <thead>
            <th>FirstName</th>
            <th>LastName</th>
            <th>Properties</th>
            </thead>
            <c:forEach items="${allEmployee}" var="user">
                <div style="background-color: darkgray">
                    <tr style=" background-color: aliceblue; border: black solid 1px">

                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>
                            <div class="text-center">
                                <p>education : ${user.properties.education} </p>
                                <p>busyness : ${user.properties.busyness} </p>
                                <p>experience : ${user.properties.experience} </p>
                                <p>level of English : ${user.properties.levelOfEnglish} </p>
                                <p>salary : ${user.properties.salaryWork} </p>
                                <p>sphere of work : ${user.properties.sphereOfWork} </p>
                            </div>
                        </td>
                    </tr>
                </div>


            </c:forEach>
        </table>
        </c:if>

        <div class="d-flex flex-column" style="background-color: darkgray">
            <h3 class="text-center">Filter</h3>
            <form:form method="POST" modelAttribute="filter">
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
    </div>
    <a href="/"><s:message code="home"/> </a>


</main>

<!-- Footer -->
<footer class="page-footer font-small blue fixed-bottom ml-3 mr-3"
        style="position: absolute; background-color: cornflowerblue">

    <!-- Copyright -->
    <div class="footer-copyright text-center py-3">© 2020 Copyright:
        <a href="https://vk.com/koala1101"> vk.com/koala1101</a>
    </div>
    <!-- Copyright -->

</footer>


<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/js/bootstrap.bundle.min.js"
        integrity="sha384-BOsAfwzjNJHrJ8cZidOg56tcQWfp6y72vEJ8xQ9w6Quywb24iOsW913URv1IS4GD"
        crossorigin="anonymous"></script>

</body>
</html>
