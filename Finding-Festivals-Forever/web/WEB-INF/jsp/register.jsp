<%-- 
    Document   : login
    Created on : Oct 31, 2014, 11:14:28 PM
    Author     : CharaKatiri
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

        <title>Register</title>

        <!-- Bootstrap core CSS -->
        <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="signin.css" rel="stylesheet">

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
        <h1>Register:</h1> <form method="POST" action="/Finding-Festivals-Forever/register">
            <p>Username: <input type="text" name="username" placeholder="Enter username"</p>
            <p>Password: <input type="password" name="password" placeholder="Enter password"</p>
            <p>Confirm Password: <input type="password" name="confirmedPassword" placeholder="Confirm password" </p>
            <p>Name: <input type="text" name="name" placeholder="Enter name" </p>
            <p>Email: <input type="email" name="email" placeholder="Enter email" </p>
            <p>Postcode: <input type="text" name="postcode" placeholder="Enter postcode"</p>
            <p>Country: 
                <select name="countryLst" id="countryLst">
                    <option value=""></option>
                    <c:forEach items="${countryList}" var="option">
                        <option value="${option}">
                        <c:out value="${option.name}"></c:out>
                        </option>
                    </c:forEach>
                </select>
            <p><input type="submit" value="Register" </p> 
        </form>
    </body>
    
</html>