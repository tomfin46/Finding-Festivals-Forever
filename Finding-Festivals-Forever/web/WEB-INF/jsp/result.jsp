<%-- 
    Document   : newjspresult
    Created on : 28-Oct-2014, 19:25:52
    Author     : Tom
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Finding Festivals Forever!!</title>

        <!-- CSS Files -->
        <!-- External-->
        <link href="http://getbootstrap.com/dist/css/bootstrap.min.css" rel="stylesheet"> <!-- Bootstrap -->

        <!--Internal-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/result.css" />
    </head>
    <body>
        <div class="container">
            <c:choose>
                <c:when test="${result eq 'success'}">
                    <h3 class="success">Successfully registered.<br/>Please proceed to login below</h3>
                    <a class="btn btn-primary" href="/Finding-Festivals-Forever/login">Login</a>
                </c:when>
                <c:when test="${result eq 'alreadyexists'}">
                    <h3 class="alreadyexists">That user already exists.<br/>Please proceed to login below</h3>
                    <a class="btn btn-primary" href="/Finding-Festivals-Forever/login">Login</a>
                </c:when>
                <c:otherwise>
                    <h3 class="error">There has been an error.<br/>Please try again.</h3>
                    <a class="btn btn-primary" href="/Finding-Festivals-Forever/register">Register</a>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
