/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*global $, Utils */
var WeatherComponent = (function () {

    "use strict";

    var addWeather = function (timeOfDay, data) {
        var weatherComponent = document.querySelector(".weatherComponent");
        var $weatherComponent = $(weatherComponent);

        if (weatherComponent && $weatherComponent) {
            var kids = $weatherComponent.children();
            var len = kids.length;

            if (len === 0 || len > 5) {
                $weatherComponent.empty();

                var dayDiv = createDayDivWithTimeDiv(timeOfDay, data);
                weatherComponent.appendChild(dayDiv);
            }
            else {
                var last = weatherComponent.lastElementChild;

                if (last) {
                    var todElem = last.querySelector("." + getTimeOfDayString(timeOfDay));

                    // If this time of day alreay exists then we're in a new day
                    if (todElem) {
                        var dayDiv = createDayDivWithTimeDiv(timeOfDay, data);
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
                        this.innerHTML = convertTemperature(val);
                    });
                    break;
                case "mph":
                case "kph":
                    var winds = weatherComponent.querySelectorAll(".minTemp, .maxTemp");

                    $.each(winds, function () {
                        var current = this.innerHTML;
                        var val = current.substr(0, current.length - 3);
                        this.innerHTML = convertSpeed(val);
                    });
                    break;
            }
        }
    };


    var convertTemperature = function (fahrenheit) {
        // Convert the temperature to either Celsius or Fahrenheit:
        return Math.round(Utils.getTemperatureFormat() === 'f' ? fahrenheit : ((fahrenheit - 32) * (5 / 9)));
    };

    var convertSpeed = function (mph) {
        // Convert the speed to either mph or kph:
        return Math.round(Utils.getSpeedFormat() === 'mph' ? mph : (mph * 1.609344));
    };

    var createDayDivWithTimeDiv = function (timeOfDay, data) {
        var dayDiv = document.createElement("div");
        var timeDiv = createTimeDiv(timeOfDay, data);

        dayDiv.appendChild(timeDiv);

        return dayDiv;
    };

    var createTimeDiv = function (timeOfDay, data) {
        var timeDiv = document.createElement("div");
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
                weatherItemDiv.innerHTML = value;
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
