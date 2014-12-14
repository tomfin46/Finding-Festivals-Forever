<%-- 
    Document   : login
    Created on : Oct 31, 2014, 11:14:38 PM
    Author     : CharaKatiri
    source: view-source:http://getbootstrap.com/examples/signin/
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../favicon.ico">

        <title>Log in</title>

        <!-- CSS Files -->
        <!-- External-->
        <link href="http://getbootstrap.com/dist/css/bootstrap.min.css" rel="stylesheet"> <!-- Bootstrap -->

        <!--Internal -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/forms.css" />
    </head>

    <body>

        <div class="container">


            <c:url value='/login' var="loginUrl" />

            <form class="form" name='loginForm' action="${loginUrl}" method='POST'>
                <h2 class="form-heading">Please Login to continue</h2>
                
                <c:if test="${not empty error}">
                    <div class="error">${error}</div>
                </c:if>
                <c:if test="${not empty logout}">
                    <div class="logout">${logout}</div>
                </c:if>
                
                <input class="form-control" type='text' name='username' placeholder="Username" required autofocus />
                <input class="form-control last-input" type='password' name='password' placeholder="Password" required />
                <input class="btn btn-lg btn-primary btn-block" type="submit" value="Login" />
                <input class="form-control" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                <a class="btn btn-primary" href="/Finding-Festivals-Forever/index">Back to home</a>
                <a class="btn btn-primary" href="/Finding-Festivals-Forever/register">Register</a>
            </form>
        </div> <!-- ./container -->
    </body>
</html>
