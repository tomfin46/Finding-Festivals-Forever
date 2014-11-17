/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*global $, navigator, localStorage, Utils, WeatherComponent */

var GeoLocation = (function () {

    "use strict";

    var geolocationSuccess, geolocationError, shouldUseWindIcon, showError;

    geolocationSuccess = function (position) {
        try {

            // Retrive the cache
            var cache = localStorage.weatherCache && JSON.parse(localStorage.weatherCache),
                    date = new Date(),
                    thirtyMin = 30 * 60 * 1000,
                    offset, city, country;

            // If the cache is newer than 30 minutes, use the cache
            if (cache && cache.timestamp && cache.timestamp > date.getTime() - thirtyMin) {

                // Get the offset from UTC (turn the offset minutes into ms)
                offset = date.getTimezoneOffset() * 60 * 1000;
                city = cache.data.city_Object.name;
                country = cache.data.city_Object.countryCode;

                var firstTime = new Date(cache.data.forecast_List[0].dateTime - offset);
                var firstDayOfWeek = firstTime.getDay();

                $.each(cache.data.forecast_List, function () {
                    // "this" holds a forecast object

                    // Get the local time of this forecast (the api returns it in utc)
                    var localTime = new Date(this.dateTime - offset);
                    var momentTime = moment(localTime);

                    var timeOfDay = null;

                    switch (momentTime.hour()) {
                        case 9:
                            timeOfDay = 0;
                            break;
                        case 15:
                            timeOfDay = 1;
                            break;
                        case 21:
                            timeOfDay = 2;
                            break;
                        default:
                            break;
                    }

                    if (timeOfDay !== null) {
                        var DEG = Utils.getTemperatureFormat();  // c for celsius, f for fahrenheit
                        var WINDSPEED = Utils.getSpeedFormat(); // mph or kph

                        var data = {
                            momentTime: momentTime.calendar(),
                            weatherIconName: this.weather_List[0].weatherIconName,
                            weatherDesc: this.weather_List[0].weatherDescription,
                            minTemp: WeatherComponent.convertTemperature(this.mainData_Object.minTemperature) + '°' + DEG,
                            maxTemp: WeatherComponent.convertTemperature(this.mainData_Object.maxTemperature) + '°' + DEG,
                            windSpeed: WeatherComponent.convertSpeed(this.wind_Object.windSpeed) + WINDSPEED
                        };
                        var dayOfWeek = momentTime.weekday() - firstDayOfWeek;

                        shouldUseWindIcon(data);

                        WeatherComponent.addWeather(timeOfDay, dayOfWeek, data);
                    }

                });

                //weatherDiv.addClass('loaded');

            }

            else {

                // If the cache is old or nonexistent, issue a new AJAX request to the controller

                $.ajax({
                    type: 'Get',
                    url: Utils.getPageContext() + '/weather/forecast?lat=' + position.coords.latitude + '&lon=' + position.coords.longitude,
                    success: function (response) {

                        // Store the cache
                        localStorage.weatherCache = JSON.stringify({
                            timestamp: (new Date()).getTime(), // getTime() returns milliseconds
                            data: response
                        });

                        // Call the function again
                        GeoLocation.geolocationSuccess(position);
                    }
                });
            }

        }
        catch (e) {
            showError("We can't find information about your city!");
            window.console && console.error(e);
        }
    };

    geolocationError = function (error) {
        switch (error.code) {
            case error.TIMEOUT:
                showError("A timeout occured! Please try again!");
                break;
            case error.POSITION_UNAVAILABLE:
                showError('We can\'t detect your location. Sorry!');
                break;
            case error.PERMISSION_DENIED:
                showError('Please allow geolocation access for this to work.');
                break;
            case error.UNKNOWN_ERROR:
                showError('An unknown error occured!');
                break;
        }
    };

    shouldUseWindIcon = function (data) {
        if (data.windSpeed >= 25) {
            if (data.weatherIconName.equalsIgnoreCase("01d") || data.weatherIconName.equalsIgnoreCase("02d")) {
                data.weatherIconName = "Cloud-Wind-Sun";
            }
            else if (data.weatherIconName.equalsIgnoreCase("01n") || data.weatherIconName.equalsIgnoreCase("02n")) {
                data.weatherIconName = "Cloud-Wind-Moon";
            }
            else if (data.weatherIconName.equalsIgnoreCase("03d") || data.weatherIconName.equalsIgnoreCase("04d")
                    || data.weatherIconName.equalsIgnoreCase("03n") || data.weatherIconName.equalsIgnoreCase("04n")) {
                data.weatherIconName = "Cloud-Wind";
            }
        }
    };
    
    showError = function(message) {
        alert(message);
    };


    return {
        geolocationSuccess: geolocationSuccess,
        geolocationError: geolocationError
    };

}());

$(function () {

    "use strict";

    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(GeoLocation.geolocationSuccess, GeoLocation.geolocationError);
    }
    else {
        // geolocation not supported in browser
    }


});