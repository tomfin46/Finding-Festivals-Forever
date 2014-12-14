<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <!--        CSS Files -->
        <!--        External-->

        <link href="http://getbootstrap.com/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="http://getbootstrap.com/examples/carousel/carousel.css" rel="stylesheet">

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/default.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/components/mapsComponent.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/components/weatherComponent.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/components/festivalsList.css" />


        <!-- Custom styles for this template -->
        <!--        <link href="starter-template.css" rel="stylesheet">
        
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
        <div class="authorizeUser">
            <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                <div class="toggleFavourite"></div>
            </sec:authorize>
        </div>

        <script>
            function formSubmit() {
                document.getElementById("logoutForm").submit();
            }
        </script>

    <nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Finding Festival Forever </a>
            </div><!-- /.navbar-header -->

            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/Finding-Festivals-Forever/favourites">Favourites</a></li>

                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name != null}">
                            <li><a>Welcome <c:out value="${pageContext.request.userPrincipal.name}" /></a></li>
                            <li><a href="javascript:formSubmit()">Logout</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="/Finding-Festivals-Forever/login">Login</a></li>                                
                            <li><a href="/Finding-Festivals-Forever/register">Register</a></li>
                        </c:otherwise>
                    </c:choose>

                    <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                        <c:url value="/logout" var="logoutUrl" />

                        <form action="${logoutUrl}" method="post" id="logoutForm">
                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}" />
                        </form>
                    </sec:authorize>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container -->
    </nav><!-- /.navbar -->


    <!-- Carousel
    ================================================== -->
    <div id="myCarousel" class="carousel slide" data-ride="carousel">

        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>

        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img src="${pageContext.request.contextPath}/resources/images/test.JPG" alt="">
                <div class="container">
                    <div class="carousel-caption">
                        <h1>Festival Finder Forever!</h1>
                        <p>Looking for festivals around you? Looking for specific dates? Looking for performers? You are in the right place! </p>
                    </div>
                </div> <!-- ./container -->
            </div> <!-- ./item active -->

            <div class="item">
                <img src="${pageContext.request.contextPath}/resources/images/festivals1.jpg" alt="">
                <div class="container">
                    <div class="carousel-caption">
                        <h1>Another example headline.</h1>
                        <p><a class="btn btn-lg btn-primary" href="#" role="button">Contact us</a></p>
                    </div>
                </div> <!-- ./container -->
            </div> <!-- ./item -->

            <div class="item">
                <img src="${pageContext.request.contextPath}/resources/images/test.JPG" alt="">
                <div class="container">
                    <div class="carousel-caption">
                        <h1>One more for good measure.</h1>
                        <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                    </div>
                </div> <!-- ./container -->
            </div> <!-- ./item -->
        </div> <!-- ./carousel-inner -->

        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div><!-- /.carousel -->


    <!-- START THE FEATURETTES -->

    <div class="row featurette">
        <div class="col-md-7">
            <h2 class="featurette-heading">Festivals <span class="text-muted">Browse Festivals.</span></h2>
            <p class="lead">Looking for festivals around you? Looking for specific dates? Looking for specific performers? You are in the right place! </p>
        </div>

        <div class="col-md-6">
            <div class="festivalsList"></div>
        </div>

        <div class="col-md-5">
            <div class="map-canvas"></div>            
            <div class="mapsComponent"></div>
        </div>

    </div> <!-- ./featurette -->

    <hr class="featurette-divider">

    <div class="row featurette">
        <div class="col-md-5">
            <h2 class="featurette-heading">Check the weather </h2>
            <p class="lead"> Get the latest forecast for the festival! </p>

            <div class="col-md-10">
                <form  id="weatherSettings" action="">
                    <input type="radio" name="temp" value="c" checked="true">°C
                    <input type="radio" name="temp" value="f">°F<br />
                    <input type="radio" name="speed" value="mph" checked="true">mph
                    <input type="radio" name="speed" value="kph">kph
                </form>
            </div>
        </div>

        <div class="col-md-10">
            <div class="weatherComponent"></div>
        </div>

    </div> <!-- ./featurette -->

    <hr class="featurette-divider">

    <div class="row featurette">
        <div class="col-md-7">
            <h2 class="featurette-heading"> Share Festivals 
                <p class="lead">Going to this Festival? Share it with friends!</p>

                <div class="g-plus" data-action="share"></div>
                <div class="fb-share-button" data-href="${pageContext.request.contextPath}"></div>
                <a class="twitter-share-button" href="https://twitter.com/share">Tweet</a>
        </div>
    </div> <!-- ./featurette -->

    <hr class="featurette-divider">

    <div class="row featurette">
        <div class="col-md-2">

            <!--    bottom of the page. by clicking here the user will go back to top -->
            <a href="#top">Back to top </a> 
        </div>
    </div> <!-- ./featurette -->

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
    <script src="http://getbootstrap.com/assets/js/docs.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="http://getbootstrap.com/assets/js/ie10-viewport-bug-workaround.js"></script>

</body>

</html>