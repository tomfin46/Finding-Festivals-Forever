<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>

<html>
    <head>
        <meta charset=UTF-8>
        <!-- CSS Files -->
        <!-- External-->
        <link href="http://getbootstrap.com/dist/css/bootstrap.min.css" rel="stylesheet"> <!-- Bootstrap -->
        <link href="http://getbootstrap.com/examples/carousel/carousel.css" rel="stylesheet"> <!-- Bootstrap Carousel -->

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/default.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/components/mapsComponent.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/components/weatherComponent.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/components/festivalsList.css" />

        <!-- JavaScript Files -->
        <!--External-->
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> <!-- JQuery -->
        <script src="https://apis.google.com/js/platform.js" async defer></script> <!-- Google+ Share -->
        <script src="http://momentjs.com/downloads/moment.js"></script> <!-- moment.js - datetime manipulations -->
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.1.47/jquery.form-validator.min.js"></script> <!-- JQuery Form Validator -->

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

        <title>Festival Finder Forever!</title>

    </head>

    <body>
        <!--    declaring top of the page -->
        <a id="top">
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
                        <!-- The line below is invalid HTML, but I do not know how to make it valid. The error is: "The element button must not appear as a descendant of the a element." -->
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">Finding Festivals Forever</a>
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

                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Login<span class="caret"></span></a>
                                        <ul class="dropdown-menu">
                                            <c:url value='/login' var="loginUrl" />
                                            <form class="navbar-form" name='loginForm' action="${loginUrl}" method='POST'>
                                                <input class="form-control" type='text' name='username' placeholder="Username" />
                                                <input class="form-control" type='password' name='password' placeholder="Password" />
                                                <input class="btn btn-default" name="submit" type="submit" value="Login" />
                                                <input class="form-control" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                            </form>
                                        </ul>
                                    </li>

                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">Register<span class="caret"></span></a>
                                        <ul class="dropdown-menu">
                                            <c:url value='/register' var="registerUrl" />
                                            <form class="navbar-form" name="registrationForm" action="${registerUrl}" method="POST">
                                                <input class="form-control" type="text" name="username" placeholder="Username"
                                                       data-validation="length alphanumeric" 
                                                       data-validation-length="4-12"
                                                       data-validation-error-msg="The username has to contain at least 4 characters " />
                                                <input class="form-control" type="password" name="pass_confirmation" placeholder="Password (Min 8 Characters)"
                                                       data-validation="length"
                                                       data-validation-length="min8"
                                                       data-validation-error-msg="password need to contain at least 8 characters " />
                                                <input class="form-control" type="password" name="pass" placeholder="Confirm Password"
                                                       data-validation="confirmation">
                                                <input class="form-control" type="text" name="name" placeholder="Name" />
                                                <input class="form-control" type="email" name="email" placeholder="Email"
                                                       data-validation="email" />
                                                <input class="form-control" type="text" name="postcode" placeholder="Postcode" />
                                                <input class="form-control" name="country" id="country" placeholder="Country"
                                                       data-validation="country" />
                                                <input class="btn btn-default" type="submit" value="Register" />
                                                <input class="form-control" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                            </form>
                                        </ul>
                                    </li>

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

            <script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
            <script src="http://getbootstrap.com/assets/js/docs.min.js"></script>
            <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
            <script src="http://getbootstrap.com/assets/js/ie10-viewport-bug-workaround.js"></script>

            <script src="${pageContext.request.contextPath}/resources/js/register.js"></script>
    </body>

</html>