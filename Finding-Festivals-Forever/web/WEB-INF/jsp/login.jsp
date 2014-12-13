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

        <!-- Bootstrap core CSS -->
        <!--        <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">-->

        <!-- Custom styles for this template -->
        <!--        <link href="signin.css" rel="stylesheet">-->

        <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
        <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
        <script src="../../assets/js/ie-emulation-modes-warning.js"></script>



        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>

        <div class="container">
            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>
            <c:if test="${not empty msg}">
                <div class="msg">${msg}</div>
            </c:if>

            <c:url value='/login' var="loginUrl" />

            <form name='loginForm' action="${loginUrl}" method='POST'>

                <table>
                    <tr>
                        <td>User:</td>
                        <td><input type='text' name='username'></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type='password' name='password' /></td>
                    </tr>
                    <tr>
                        <td colspan='2'>
                            <input name="submit" type="submit" value="submit" />
                        </td>
                    </tr>
                </table>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

            </form>
        </div> <!-- ./container -->

        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
        <p><a href="/Finding-Festivals-Forever/index">Back to home</a></p>
    </body>
</html>
