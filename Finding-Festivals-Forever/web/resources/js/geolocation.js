/*global window, console, $, navigator, localStorage, alert, Utils, GoogleMapsComponent, WeatherComponent, moment */

var GeoLocation = (function () {

    "use strict";

    var geolocationSuccess, geolocationError, initWeatherComponent, refreshWeatherWithNewPosition,
            initMapsComponent, refreshMapsWithNewPosition, shouldUseWindIcon, showError, useDefault;

    geolocationSuccess = function (position) {
        try {
            initWeatherComponent(position);
            initMapsComponent(position);
        }
        catch (e) {
            showError("We can't find information about your city!");
            if (window.console) {
                console.error(e);
            }
            GeoLocation.useDefault();
        }
    };

    geolocationError = function (error) {
        switch (error.code) {
            case error.TIMEOUT:
                showError("A timeout occured! Please try again!");
                break;
            case error.POSITION_UNAVAILABLE:
                showError('We can\'t detect your location. Sorry!');
                GeoLocation.useDefault();
                break;
            case error.PERMISSION_DENIED:
                showError('Please allow geolocation access for this to work.');
                GeoLocation.useDefault();
                break;
            case error.UNKNOWN_ERROR:
                showError('An unknown error occured!');
                GeoLocation.useDefault();
                break;
        }
    };

    initWeatherComponent = function (position) {
        var cache = localStorage.weatherCache && JSON.parse(localStorage.weatherCache),
                date = new Date(),
                thirtyMin = 30 * 60 * 1000,
                offset, firstTime, firstDayOfWeek,
                DEG, WINDSPEED;

        // If the cache is newer than 30 minutes, use the cache
        if (cache && cache.timestamp && cache.timestamp > date.getTime() - thirtyMin) {

            // Get the offset from UTC (turn the offset minutes into ms)
            offset = date.getTimezoneOffset() * 60 * 1000;

            firstTime = new Date(cache.data.forecast_List[0].dateTime - offset);
            firstDayOfWeek = firstTime.getDay();

            DEG = Utils.getTemperatureFormat(); // c for celsius, f for fahrenheit
            WINDSPEED = Utils.getSpeedFormat(); // mph or kph

            $.each(cache.data.forecast_List, function () {

                // Get the local time of this forecast (the api returns it in utc)
                var localTime = new Date(this.dateTime - offset),
                        momentTime = moment(localTime),
                        timeOfDay = null,
                        data, dayOfWeek;

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

                    data = {
                        momentTime: momentTime.calendar(),
                        weatherIconName: this.weather_List[0].weatherIconName,
                        weatherDesc: this.weather_List[0].weatherDescription,
                        minTemp: WeatherComponent.convertTemperature(this.mainData_Object.minTemperature) + '°' + DEG,
                        maxTemp: WeatherComponent.convertTemperature(this.mainData_Object.maxTemperature) + '°' + DEG,
                        windSpeed: WeatherComponent.convertSpeed(this.wind_Object.windSpeed) + WINDSPEED
                    };
                    dayOfWeek = momentTime.weekday() - firstDayOfWeek;

                    shouldUseWindIcon(data);

                    WeatherComponent.addWeather(timeOfDay, dayOfWeek, data);
                }

            });
        }
        else { // If the cache is old or nonexistent, request new data from weather controller then recall success handler
            $.ajax({
                type: 'Get',
                url: Utils.getPageContext() + '/weather/forecast?lat=' + position.coords.latitude + '&lon=' + position.coords.longitude,
                success: function (response) {

                    localStorage.weatherCache = JSON.stringify({
                        timestamp: (new Date()).getTime(),
                        data: response
                    });

                    GeoLocation.geolocationSuccess(position);
                }
            });
        }

    };

    refreshWeatherWithNewPosition = function (position) {
        localStorage.weatherCache = null;
        initWeatherComponent(position);
    };

    initMapsComponent = function (position) {
        var data = {
            type: 'search',
            query: 'music festivals in england',
            lat: position.coords.latitude,
            lon: position.coords.longitude,
            zoom: 10
        };

        GoogleMapsComponent.setupFields(data);
    };

    refreshMapsWithNewPosition = function (position) {
        initMapsComponent(position);
        GoogleMapsComponent.initializeJsComponent();
    };

    shouldUseWindIcon = function (data) {
        // If wind speed is high enough then change weather icon to incorporate wind
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

    showError = function (message) {
        alert(message);
    };

    useDefault = function () {
        // use london as a default location for when geolocation is not supported, denied or errors out
        var position = {
            coords: {
                latitude: 51.5072,
                longitude: 0.1275
            }
        };
        GeoLocation.geolocationSuccess(position);
    };

    return {
        geolocationSuccess: geolocationSuccess,
        geolocationError: geolocationError,
        useDefault: useDefault,
        refreshWeatherWithNewPosition: refreshWeatherWithNewPosition,
        refreshMapsWithNewPosition: refreshMapsWithNewPosition
    };

}());