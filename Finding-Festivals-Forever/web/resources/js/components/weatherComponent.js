/*global document, $, Utils */
var WeatherComponent = (function () {

    "use strict";

    var setWeatherTitle, addWeather, convertWeatherUnits, convertTemperature, convertSpeed,
            _createDayDivWithTimeDiv, _createTimeDiv, _getTimeOfDayString;

    setWeatherTitle = function(newTitle) {
        document.querySelector(".weatherSubTitle").innerHTML = newTitle;        
    };

    addWeather = function (timeOfDay, dayOfWeek, data) {
        var weatherComponent = document.querySelector(".weatherComponent"),
                $weatherComponent = $(weatherComponent),
                kids, len,
                dayDiv, timeDiv,
                last, todElem, sameDay;

        if (weatherComponent && $weatherComponent) {
            kids = $weatherComponent.children();
            len = kids.length;

            if (len === 0 || len > 6 || dayOfWeek === 0) {
                $weatherComponent.empty();

                dayDiv = _createDayDivWithTimeDiv(timeOfDay, dayOfWeek, data);
                weatherComponent.appendChild(dayDiv);
            }
            else {
                last = weatherComponent.lastElementChild;

                if (last) {
                    todElem = last.querySelector("." + _getTimeOfDayString(timeOfDay));
                    sameDay = last.classList.contains("day" + dayOfWeek);

                    // If this time of day alreay exists or dayOfWeek is different then we're in a new day
                    if (todElem || !sameDay) {
                        dayDiv = _createDayDivWithTimeDiv(timeOfDay, dayOfWeek, data);
                        weatherComponent.appendChild(dayDiv);
                    }
                    else {
                        timeDiv = _createTimeDiv(timeOfDay, data);
                        last.appendChild(timeDiv);
                    }

                }
            }
        }
    };

    convertWeatherUnits = function (newUnit) {
        var weatherComponent = document.querySelector(".weatherComponent"),
                $weatherComponent = $(weatherComponent),
                temps, winds;

        if (weatherComponent && $weatherComponent) {
            switch (newUnit) {
                case "c":
                case "f":
                    temps = weatherComponent.querySelectorAll(".minTemp, .maxTemp");

                    if (temps) {
                        $.each(temps, function () {
                            var current = this.innerHTML,
                                    val = current.substr(0, current.length - 2);
                            this.innerHTML = convertTemperature(val) + '°' + Utils.getTemperatureFormat();
                        });
                    }
                    break;
                case "mph":
                case "kph":
                    winds = weatherComponent.querySelectorAll(".windSpeed");

                    if (winds) {
                        $.each(winds, function () {
                            var current = this.innerHTML,
                                    val = current.substr(0, current.length - 3);
                            this.innerHTML = convertSpeed(val) + Utils.getSpeedFormat();
                        });
                    }
                    break;
            }
        }
    };

    convertTemperature = function (temp) {
        // Convert the temperature to either Celsius or Fahrenheit:
        return Math.round(Utils.getTemperatureFormat() === 'f'
                // c to f
                ? ((temp * (180 / 100)) + 32)
                // f to c
                : ((temp - 32) * (5 / 9)));
    };

    convertSpeed = function (speed) {
        // Convert the speed to either mph or kph:
        return Math.round(Utils.getSpeedFormat() === 'mph'
                // kph to mph
                ? (speed / 1.609344)
                // mph to kph
                : (speed * 1.609344));
    };

    _createDayDivWithTimeDiv = function (timeOfDay, dayOfWeek, data) {
        var dayDiv = document.createElement("div"),
                titleDiv = document.createElement("div"),
                timeDiv = _createTimeDiv(timeOfDay, data);

        dayDiv.classList.add("day");
        dayDiv.classList.add("day" + dayOfWeek);

        titleDiv.classList.add("dayTitle");
        titleDiv.innerHTML = data.momentTime.substr(0, data.momentTime.indexOf(" "));
        dayDiv.appendChild(titleDiv);

        dayDiv.appendChild(timeDiv);

        return dayDiv;
    };

    _createTimeDiv = function (timeOfDay, data) {
        var timeDiv = document.createElement("div");
        timeDiv.classList.add("time");
        timeDiv.classList.add(_getTimeOfDayString(timeOfDay));

        $.each(data, function (key, value) {
            var weatherIcon, weatherItemDiv;
            
            if (key === "weatherIconName") {
                weatherIcon = document.createElement("img");

                $(weatherIcon).attr("src", Utils.getPageContext() + "/resources/images/weather/" + value + ".svg");
                timeDiv.appendChild(weatherIcon);
            }
            else {
                weatherItemDiv = document.createElement("div");
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

    _getTimeOfDayString = function (timeOfDay) {
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
        setWeatherTitle: setWeatherTitle,
        addWeather: addWeather,
        convertWeatherUnits: convertWeatherUnits,
        convertTemperature: convertTemperature,
        convertSpeed: convertSpeed
    };

}());

    