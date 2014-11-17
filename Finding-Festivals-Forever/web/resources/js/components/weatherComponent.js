/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*global $, Utils */
var WeatherComponent = (function () {

    "use strict";

    var addWeather = function (timeOfDay, dayOfWeek, data) {
        var weatherComponent = document.querySelector(".weatherComponent");
        var $weatherComponent = $(weatherComponent);

        if (weatherComponent && $weatherComponent) {
            var kids = $weatherComponent.children();
            var len = kids.length;

            if (len === 0 || len > 6) {
                $weatherComponent.empty();

                var dayDiv = createDayDivWithTimeDiv(timeOfDay, dayOfWeek, data);
                weatherComponent.appendChild(dayDiv);
            }
            else {
                var last = weatherComponent.lastElementChild;

                if (last) {
                    var todElem = last.querySelector("." + getTimeOfDayString(timeOfDay));
                    var sameDay = last.classList.contains("day" + dayOfWeek);

                    // If this time of day alreay exists or dayOfWeek is different then we're in a new day
                    if (todElem || !sameDay) {
                        var dayDiv = createDayDivWithTimeDiv(timeOfDay, dayOfWeek, data);
                        weatherComponent.appendChild(dayDiv);
                    }
                    else {
                        var timeDiv = createTimeDiv(timeOfDay, data);
                        last.appendChild(timeDiv);
                    }

                }
            }
        }
    };

    var convertWeatherUnits = function (newUnit) {
        var weatherComponent = document.querySelector(".weatherComponent");
        var $weatherComponent = $(weatherComponent);

        if (weatherComponent && $weatherComponent) {
            switch (newUnit) {
                case "c":
                case "f":
                    var temps = weatherComponent.querySelectorAll(".minTemp, .maxTemp");

                    $.each(temps, function () {
                        var current = this.innerHTML;
                        var val = current.substr(0, current.length - 2);
                        this.innerHTML = convertTemperature(val) + '°' + Utils.getTemperatureFormat();
                    });
                    break;
                case "mph":
                case "kph":
                    var winds = weatherComponent.querySelectorAll(".windSpeed");

                    $.each(winds, function () {
                        var current = this.innerHTML;
                        var val = current.substr(0, current.length - 3);
                        this.innerHTML = convertSpeed(val) + Utils.getSpeedFormat();
                    });
                    break;
            }
        }
    };

    var convertTemperature = function (temp) {
        // Convert the temperature to either Celsius or Fahrenheit:
        return Math.round(Utils.getTemperatureFormat() === 'f'
                // c to f
                ? ((temp * (180 / 100)) + 32)
                // f to c
                : ((temp - 32) * (5 / 9)));
    };

    var convertSpeed = function (speed) {
        // Convert the speed to either mph or kph:
        return Math.round(Utils.getSpeedFormat() === 'mph'
                // kph to mph
                ? (speed / 1.609344)
                // mph to kph
                : (speed * 1.609344));
    };

    var createDayDivWithTimeDiv = function (timeOfDay, dayOfWeek, data) {
        var dayDiv = document.createElement("div");
        dayDiv.classList.add("day");
        dayDiv.classList.add("day" + dayOfWeek);

        var titleDiv = document.createElement("div");
        titleDiv.classList.add("dayTitle");
        titleDiv.innerHTML = data.momentTime.substr(0, data.momentTime.indexOf(" "));
        dayDiv.appendChild(titleDiv);

        var timeDiv = createTimeDiv(timeOfDay, data);
        dayDiv.appendChild(timeDiv);

        return dayDiv;
    };

    var createTimeDiv = function (timeOfDay, data) {
        var timeDiv = document.createElement("div");
        timeDiv.classList.add("time");
        timeDiv.classList.add(getTimeOfDayString(timeOfDay));

        $.each(data, function (key, value) {
            if (key === "weatherIconName") {
                var weatherIcon = document.createElement("img");

                $(weatherIcon).attr("src", Utils.getPageContext() + "/resources/images/weather/" + value + ".svg");
                timeDiv.appendChild(weatherIcon);
            }
            else {
                var weatherItemDiv = document.createElement("div");
                weatherItemDiv.classList.add(key);
                if (key === "momentTime") {
                    weatherItemDiv.innerHTML = value.substr(value.indexOf("at ") + 3, value.length);
                }
                else {
                    weatherItemDiv.innerHTML = value;
                }
                timeDiv.appendChild(weatherItemDiv);
            }
        });

        return timeDiv;
    };

    var getTimeOfDayString = function (timeOfDay) {
        var todClass;
        switch (timeOfDay) {
            case 0:
                todClass = "morning";
                break;
            case 1:
                todClass = "afternoon";
                break;
            case 2:
                todClass = "evening";
                break;

        }

        return todClass;
    };

    return {
        addWeather: addWeather,
        convertWeatherUnits: convertWeatherUnits,
        convertTemperature: convertTemperature,
        convertSpeed: convertSpeed
    };

}());

    