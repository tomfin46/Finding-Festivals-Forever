/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*global document, $, Utils */
var FestivalsList = (function () {

    "use strict";

    var addFestival, _createFestivalDiv;

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

        festivalDiv.appendChild(contentDiv);

        return festivalDiv;
    };

    return {
        addFestival: addFestival
    };

}());

    