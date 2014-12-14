<%-- 
    Document   : register
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
        <!--        CSS Files -->
        <!--        External-->

        <link href="http://getbootstrap.com/dist/css/bootstrap.min.css" rel="stylesheet"> 
        <title>Register</title>


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

        <h1>Register:</h1> 
        <form id="registration" method="POST" action="/Finding-Festivals-Forever/register">
            <p>Username: <input type="text" name="username" data-validation="length alphanumeric" 
                                data-validation-length="4-12" data-validation-error-msg="The username has to contain at least 4 characters "</p>
            <p> Password (Min 8 Characters): <input type="password" name="pass_confirmation" data-validation="length" data-validation-length="min8"  data-validation-error-msg="password need to contain at least 8 characters ">
            </p>
            <p>Confirm password:<input type="password" name="pass" data-validation="confirmation">
            </p>
            <p>Name: <input type="text" name="name"  </p>
            <p>Email: <input type="email" name="email" data-validation="email"</p>
            <p>Postcode: <input type="text" name="postcode" </p>
            <!-- <p>Country: 
                 <select name="countryLst" id="countryLst">
                     <option value=""></option>
                     <c:forEach items="${countryList}" var="option">
                         <option value="${option}">
                             <c:out value="${option.name}"></c:out>
                             </option>
                     </c:forEach>
                 </select> -->


            <!-- Feel free to delete if you want the old way-->
            <p>
                Country
                <input name="country" id="country" data-validation="country">
            </p>
            <p><input type="submit" value="Register" </p>
        </form>


        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
        <p><a href="/Finding-Festivals-Forever/index">Back to home</a></p>
        <p><a href="/Finding-Festivals-Forever/login">Go to Login page</a></p>

        <!-- JavaScript Files -->
        <!--External-->
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> <!-- JQuery -->
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.1.47/jquery.form-validator.min.js"></script>


        <script>

            $.validate({
                modules: 'location, security',
                onModulesLoaded: function () {
                    $('#country').suggestCountry();
                },
            });

        </script>    
    </body>
</html>
