<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Carousel.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">

    <!--    links to the rest of css files -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/css.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/login.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/register.css">

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Festival Finder Forever!</title>

        <!--    declaring top of the page -->
    <a name="top"> </a> 


    <!--    adding cookies http://localhost:8080/Finding-Festivals-Forever -->

    <script>

        function setCookie(cname, cvalue, exdays) {
            var d = new Date();
            d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
            var expires = "expires=" + d.toGMTString();
            document.cookie = cname + "=" + cvalue + "; " + expires;
        }

        function getCookie(cname) {
            var name = cname + "=";
            var ca = document.cookie.split(';');
            for (var i = 0; i < ca.length; i++) {
                var c = ca[i];
                while (c.charAt(0) === ' ')
                    c = c.substring(1);
                if (c.indexOf(name) !== -1) {
                    return c.substring(name.length, c.length);
                }
            }
            return "";
        }

        function checkCookie() {
            var user = getCookie("username");
            if (user !== "") {
                alert("Welcome again " + user);
            } else {
                user = prompt("Please enter your name:", "");
                if (user !== "" && user !== null) {
                    setCookie("username", user, 30);
                }
            }
        }
    </script>
</head>

<body>
    <div class="navbar-wrapper">
        <div class="container">

            <nav class="navbar navbar-inverse navbar-static-top" role="navigation">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">Festival Finder Forever! </a>
                    </div>
                    <div id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <!--                                <li class="active"><a href="#">Home</a></li>-->
                            <!--                            NOTE FOR THE TEAM: Here i'm not sure if we should link to 'href="#login' or 'href="#login.jsp'-->
                            <li><a href="#about.jsp">About</a></li>
                            <li><a href="#favorites.jsp">Favorites</a></li>
                            <li><a href="#register.jsp">Register</a></li>
                            <li><a href="#login.jsp">Login</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    </div>


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
            <!--                                <img src="resources/images/find.jpg" alt="First slide">-->
                <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="First slide">
                <div class="container">
                    <div class="carousel-caption">
                        <h1>Festival Finder Forever!</h1>
                        <p>Looking for festivals around you? Looking for specific dates? Looking for performers? You are in the right place! </p>
                        <p><a class="btn btn-lg btn-primary" href="#" role="button">Browse Festivals</a></p>
                    </div>
                </div>
            </div>
            <div class="item">
                <img src="data:image/gif;base64,R0lGODlhAQABAIAAAGZmZgAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Second slide">
                <div class="container">
                    <div class="carousel-caption">
                        <h1> Check the weather</h1>
                        <p><a class="btn btn-lg btn-primary" href="#" role="button"> Get the latest forecast for the festival! </a></p>
                    </div>
                </div>
            </div>
            <div class="item">
                <img src="data:image/gif;base64,R0lGODlhAQABAIAAAFVVVQAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Third slide">
                <div class="container">
                    <div class="carousel-caption">
                        <h1>Share Festivals</h1>
                        <p> Going to this Festival? Share it with friends! </p>
                        <p><a class="btn btn-lg btn-primary" href="#" role="button"> Share this festival</a></p>
                    </div>
                </div>
            </div>
        </div>
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right"></span>
            <span class="sr-only">Next</span>
        </a>
    </div><!-- /.carousel -->


    <!-- START THE FEATURETTES -->

    <hr class="featurette-divider">

    <div class="row featurette">
        <div class="col-md-7">
            <h2 class="featurette-heading">Festivals <span class="text-muted">Browse Festivals.</span></h2>
            <p class="lead">Looking for festivals around you? Looking for specific dates? Looking for specific performers? You are in the right place! </p>
        </div>
        <div class="col-md-5">
            <img class="featurette-image img-responsive" data-src="find.jpg/200x200/auto" alt="find">
        </div>
    </div>

    <hr class="featurette-divider">

    <div class="row featurette">
        <div class="col-md-5">
            <img class="featurette-image img-responsive" data-src="weather.jpg/200x200/auto" alt="weather">
        </div>
        <div class="col-md-7">
            <h2 class="featurette-heading">Check the weather 
                <p class="lead"> Get the latest forecast for the festival! </p>
        </div>
    </div>

    <hr class="featurette-divider">

    <div class="row featurette">
        <div class="col-md-7">
            <h2 class="featurette-heading"> Share Festivals 
                <p class="lead">Going to this Festival? Share it with friends!</p>
        </div>
        <div class="col-md-5">
            <img class="featurette-image img-responsive" data-src="share.jpg/200x200/auto" alt="share">
        </div>
    </div>

    <hr class="featurette-divider">

    <!-- /END THE FEATURETTES source: view-source:http://getbootstrap.com/examples/carousel/  -->

    <p>Hello! </p>
    <p>Looking forward to the next festival season? You're in the right place! Find festivals around you,choose performers and dates. Load up your tent and coolers and get ready to be part of the biggest festivals.   </p>

    <div class="festivalFinder">
        <div class="mapsComponent"></div>
    </div>

    <div class="allFestivals"></div>

    <div class="social"></div>
    <h1>Login:</h1> 
    <form method="POST" action="/Finding-Festivals-Forever/login">
        <p>Username: <input type="text" name="username"  placeholder="Enter username"</p>
        <p>Password: <input type="password" name="password" placeholder="Enter Password"</p>
        <div class="checkbox">
            <label><input type="checkbox"> Remember me</label>
        </div>
        <p><input type="submit" value="Login" </p>
    </form>



    <h1>Register:</h1> <form method="POST" action="/Finding-Festivals-Forever/register">
        <p> Username: <input type="text" name="username" placeholder="Enter username"</p>
        <p>Password: <input type="password" name="password" placeholder="Enter password"</p>
        <p>Confirm Password: <input type="password" name="confirmedPassword" placeholder="Confirm password" </p>
        <p>Name: <input type="text" name="name" placeholder="Enter name" </p>
        <p>Email: <input type="email" name="email" placeholder="Enter email" </p>
        <p>Postcode: <input type="text" name="postcode" placeholder="Enter postcode"</p>
        <p>Country: <input placeholder="Choose country"</p>
        <p><input type="submit" value="Register" </p> 
    </form>
</body>

<a href="#top">Back to top </a> 
<!--    bottom of the page. by clicking here the user will go back to top -->

<footer class="footer">
    <a href="/contact">Contact Us</a>
</footer> 

</html>

​
