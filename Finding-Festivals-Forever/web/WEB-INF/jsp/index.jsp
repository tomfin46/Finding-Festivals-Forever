<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <!--  <link href="../../css/css.css" rel="stylesheet" type="text/css"/> 
  <link href="${pageContext.request.contextPath}/resources/css/css.css" rel="stylesheet" >
-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"/>
    
    <head>
    
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        <h1>Finding Festivals Forever!</h1>
        <p>Hello! This is the default welcome page for a Spring Web MVC project.</p>
        <p><i>To display a different welcome page for this project, modify</i>
            <tt>index.jsp</tt> <i>, or create your own welcome page then change
                the redirection in</i> <tt>redirect.jsp</tt> <i>to point to the new
                welcome page and also update the welcome-file setting in</i>
            <tt>web.xml</tt>.</p>
        <div class="navBar"></div>
        <div class="festivalFinder">
            <div class="mapsComponent"></div>
        </div>
        <div class="allFestivals"></div>
        <div class="social"></div>
        <form method="POST" action="/Finding-Festivals-Forever/login">
            Username: <input type="text" name="username" />
            Password: <input type="password" name="password" />
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>
