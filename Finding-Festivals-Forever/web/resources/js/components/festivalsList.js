/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*global document, $, Utils */
var FestivalsList = (function () {

    "use strict";

    var addFestival, _createFestivalDiv, _addFavToggleIfAuthorized;

    addFestival = function (festival) {
        var festivalList = document.querySelector(".festivalsList"),
                $festivalList = $(festivalList);

        if (festivalList && $festivalList) {
            festivalList.appendChild(_createFestivalDiv(festival));
        }
    };

    _createFestivalDiv = function (festivalData) {
        var festivalDiv = document.createElement("div"),
                nameDiv = document.createElement("div"),
                contentDiv = document.createElement("div");
        
        festivalDiv.classList.add("festival");
        festivalDiv.classList.add(festivalData.id);
        contentDiv.classList.add("festivalData");

        nameDiv.classList.add("festivalName");
        nameDiv.innerHTML = festivalData.name;
        $(nameDiv).click(function () {
            //$(contentDiv).fadeIn("slow");

            $(contentDiv).css("display") === "none"
                    ? $(contentDiv).fadeIn("slow")
                    : $(contentDiv).fadeOut("slow");
        });

        festivalDiv.appendChild(nameDiv);

        $.each(festivalData, function (key, value) {
            var festivalImage, festivalItemDiv, festivalLocationDiv, k;

            if (key === "festivalImage") {
                festivalImage = document.createElement("img");
                $(festivalImage).attr("src", value);
                contentDiv.appendChild(festivalImage);
            }
            else if (key === "location") {
                festivalLocationDiv = document.createElement("div");
                festivalLocationDiv.classList.add(key);
                k = key;
                $.each(value, function (key, val) {
                    var locationDiv = document.createElement("div");
                    locationDiv.classList.add(key);
                    locationDiv.innerHTML = val;
                    festivalLocationDiv.appendChild(locationDiv);
                });
                contentDiv.appendChild(festivalLocationDiv);
            }
            else {
                festivalItemDiv = document.createElement("div");
                festivalItemDiv.classList.add(key);
                festivalItemDiv.innerHTML = value;
                contentDiv.appendChild(festivalItemDiv);
            }
        });

        _addFavToggleIfAuthorized(festivalData.id, contentDiv);

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
                var authorizedToggle = document.querySelector('.authorizeUser .toggleFavourite');

                if (Utils.isValidVariable(authorizedToggle)) {

                    var toggleFav = authorizedToggle.cloneNode(true);

                    toggleFav.innerHTML = isFavourite ? removeFromFavourites : addToFavourites;
                    
                    $(toggleFav).click(function () {

                        if (this.innerHTML === addToFavourites) {
                            $.ajax({
                                type: 'Get',
                                url: Utils.getPageContext() + '/addToFavourites?festival=' + festivalId,
                                success: function () {
                                }
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
                                        $.each(festivals, function (idx, festival) {
                                            if (festival.classList.contains(festivalId)) {
                                                festival.parentElement.removeChild(festival);
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

    return {
        addFestival: addFestival
    };

}());

    