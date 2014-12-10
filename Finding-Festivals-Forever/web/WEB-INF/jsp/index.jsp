<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <!--        CSS Files -->
        <!--        External-->
        <!--        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Carousel.css" />
                        
                <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
                Internal
                <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/login.css" />
                <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/register.css" />-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/default.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/components/mapsComponent.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/components/weatherComponent.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/components/festivalsList.css" />


        <!-- Custom styles for this template -->
        <link href="starter-template.css" rel="stylesheet">

        <!-- JavaScript Files -->
        <!--External-->
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> <!-- JQuery -->
        <script src="https://apis.google.com/js/platform.js" async defer></script> <!-- Google+ Share -->
        <script src="http://momentjs.com/downloads/moment.js"></script> <!-- moment.js - datetime manipulations -->

        <!--Internal-->
        <script src="${pageContext.request.contextPath}/resources/js/utils.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/dataBinder.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/share.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/geolocation.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/components/genericComponent.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/components/googleMapsComponent.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/components/weatherComponent.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/components/festivalsList.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/default.js"></script>

        <meta charset=UTF-8" />
        <title>Festival Finder Forever!</title>

    </head>

    <body>
        <!--    declaring top of the page -->
        <a name="top" />
        <div id="pageContextPath" data-page-context="${pageContext.request.contextPath}"></div>

        <div class="navbar-wrapper">
            <div class="container">

                <nav class="navbar navbar-inverse navbar-static-top" role="navigation">
                    <div class="container">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                                <span class="sr-only">Toggle navigation</span>
                            </button>
                            <a class="navbar-brand" href="#">Festival Finder Forever! </a>
                        </div>
                        <div id="navbar" class="navbar-collapse collapse">
                            <ul class="nav navbar-nav">
                                <li><a href="/Finding-Festivals-Forever/favorites">Favorites</a></li>
                                <li><a href="/Finding-Festivals-Forever/login">Login</a></li>                                
                                <li><a href="/Finding-Festivals-Forever/register">Register</a></li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>
        </div>

        <!-- START THE FEATURETTES -->

        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading">Festivals <span class="text-muted">Browse Festivals.</span></h2>
                <p class="lead">Looking for festivals around you? Looking for specific dates? Looking for specific performers? You are in the right place! </p>
            </div>
            <div class="col-md-5">

                <div class="map-canvas"></div>
                <div class="festivalsList"></div>

                <div class="mapsComponent"></div>

            </div>
        </div>

        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-5">
            </div>
            <div class="col-md-7">
                <h2 class="featurette-heading">Check the weather </h2>
                <p class="lead"> Get the latest forecast for the festival! </p>

                <form id="weatherSettings" action="">
                    <input type="radio" name="temp" value="c" checked="true">°C
                    <input type="radio" name="temp" value="f">°F<br />
                    <input type="radio" name="speed" value="mph" checked="true">mph
                    <input type="radio" name="speed" value="kph">kph
                </form>


                <div class="weatherComponent"></div>
            </div>
        </div>

        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading"> Share Festivals 
                    <p class="lead">Going to this Festival? Share it with friends!</p>
            </div>
        </div>
    </div>

    <div class="g-plus" data-action="share"></div>
    <div class="fb-share-button" data-href="${pageContext.request.contextPath}"></div>
    <a class="twitter-share-button" href="https://twitter.com/share">Tweet</a>
</div>

<hr class="featurette-divider">

</body>

<a href="#top">Back to top </a> 
<!--    bottom of the page. by clicking here the user will go back to top -->

</html>