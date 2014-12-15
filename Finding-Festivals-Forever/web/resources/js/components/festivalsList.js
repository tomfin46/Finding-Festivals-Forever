/*global document, $, Utils, GeoLocation, WeatherComponent */
var FestivalsList = (function () {

    "use strict";

    var addFestival, _createFestivalDiv, _addFavToggleIfAuthorized, _addDeleteButton;


    addFestival = function (festival, addDelete) {
        var festivalList = document.querySelector(".festivalsList"),
                $festivalList = $(festivalList);

        if (festivalList && $festivalList) {
            festivalList.appendChild(_createFestivalDiv(festival, addDelete));
        }
    };

    _createFestivalDiv = function (festivalData, addDelete) {
        var festivalDiv = document.createElement("div"),
                nameDiv = document.createElement("div"),
                contentDiv = document.createElement("div");

        festivalDiv.classList.add("festival");
        festivalDiv.classList.add(festivalData.id);
        contentDiv.classList.add("festivalData");

        nameDiv.classList.add("festivalName");
        nameDiv.classList.add("btn");
        nameDiv.classList.add("btn-default");
        nameDiv.innerHTML = festivalData.name;
        $(nameDiv).click(function () {

            if ($(contentDiv).css("display") === "none") {
                $(contentDiv).fadeIn("slow");

                var position = {
                    coords: {
                        latitude: festivalData.location.latitude,
                        longitude: festivalData.location.longitude
                    }
                };

                GeoLocation.refreshMapsWithNewPosition(position);
                GeoLocation.refreshWeatherWithNewPosition(position);
                WeatherComponent.setWeatherTitle(festivalData.name);
            }
            else {
                $(contentDiv).fadeOut("slow");
            }

        });

        festivalDiv.appendChild(nameDiv);

        $.each(festivalData, function (key, value) {
            var festivalImage, festivalItemDiv, festivalLocationDiv, websiteDiv;

            if (key === "festivalImage") {
                festivalImage = document.createElement("img");
                $(festivalImage).attr("src", value);
                contentDiv.appendChild(festivalImage);
            }
            else if (key === "location") {
                festivalLocationDiv = document.createElement("div");
                festivalLocationDiv.classList.add(key);
                
                $.each(value, function (key, val) {
                    var locationDiv = document.createElement("div");
                    locationDiv.classList.add(key);
                    locationDiv.innerHTML = val;
                    festivalLocationDiv.appendChild(locationDiv);
                });
                contentDiv.appendChild(festivalLocationDiv);
            }
            else if (key === "website") {
                websiteDiv = document.createElement("a");
                websiteDiv.classList.add(key);
                websiteDiv.innerHTML = value;
                $(websiteDiv).attr("href", value);
                contentDiv.appendChild(websiteDiv);
            }
            else {
                festivalItemDiv = document.createElement("div");
                festivalItemDiv.classList.add(key);
                festivalItemDiv.innerHTML = value;
                contentDiv.appendChild(festivalItemDiv);
            }
        });

        _addFavToggleIfAuthorized(festivalData.id, contentDiv);

        if (addDelete) {
            _addDeleteButton(festivalData.id, contentDiv);
        }

        festivalDiv.appendChild(contentDiv);

        return festivalDiv;
    };

    _addFavToggleIfAuthorized = function (festivalId, contentDiv) {
        var addToFavourites = "Add To Favourites",
                removeFromFavourites = "Remove From Favourites";

        $.ajax({
            type: 'Get',
            url: Utils.getPageContext() + '/isFavourite?festival=' + festivalId,
            success: function (isFavourite) {
                var authorizedToggle = document.querySelector('.authorizeUser .toggleFavourite'),
                        toggleFav;

                if (Utils.isValidVariable(authorizedToggle)) {

                    toggleFav = authorizedToggle.cloneNode(true); //deep clone

                    toggleFav.innerHTML = isFavourite ? removeFromFavourites : addToFavourites;
                    toggleFav.classList.add("btn");
                    toggleFav.classList.add("btn-default");

                    $(toggleFav).click(function () {

                        if (this.innerHTML === addToFavourites) {
                            $.ajax({
                                type: 'Get',
                                url: Utils.getPageContext() + '/addToFavourites?festival=' + festivalId
                            });

                            this.innerHTML = removeFromFavourites;
                        }
                        else {
                            $.ajax({
                                type: 'Get',
                                url: Utils.getPageContext() + '/removeFromFavourites?festival=' + festivalId,
                                success: function () {
                                    if (document.URL.indexOf("favourites") !== -1) {
                                        var festivals = document.querySelectorAll(".festival");
                                        $.each(festivals, function () {
                                            if (this.classList.contains(festivalId)) {
                                                this.parentElement.removeChild(this);
                                            }
                                        });
                                    }
                                }
                            });

                            this.innerHTML = addToFavourites;
                        }
                    });

                    contentDiv.appendChild(toggleFav);
                }
            }
        });
    };

    _addDeleteButton = function (festivalId, contentDiv) {
        var deleteFestival = document.createElement("div");

        deleteFestival.classList.add("btn");
        deleteFestival.classList.add("btn-default");
        deleteFestival.classList.add("deleteFestival");
        deleteFestival.innerHTML = "Delete Festival";

        $(deleteFestival).click(function () {
            $.ajax({
                type: 'Post',
                headers: {
                    'X-CSRF-Token': Utils.getCsrfToken()
                },
                url: Utils.getPageContext() + '/festivals/manage/remove',
                data: {
                    festivalId: festivalId
                },
                success: function () {
                    var festivals = document.querySelectorAll(".festival");
                    $.each(festivals, function () {
                        if (this.classList.contains(festivalId)) {
                            this.parentElement.removeChild(this);
                        }
                    });
                }
            });
        });

        contentDiv.appendChild(deleteFestival);
    };

    return {
        addFestival: addFestival
    };
}());

    