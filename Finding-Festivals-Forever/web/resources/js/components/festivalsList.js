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
            $(contentDiv).css("display") === "none"
                    ? $(contentDiv).css("display", "block")
                    : $(contentDiv).css("display", "none");
        });

        festivalDiv.appendChild(nameDiv);

        $.each(festivalData, function (key, value) {
            var festivalImage, festivalItemDiv, festivalObjectDiv, k;

            if (key === "festivalImage") {
                festivalImage = document.createElement("img");
                $(festivalImage).attr("src", value);
                contentDiv.appendChild(festivalImage);
            }
            else if (key === "location" || key === "genres") {
                festivalObjectDiv = document.createElement("div");
                festivalObjectDiv.classList.add(key);
                k = key;
                $.each(value, function (key, val) {
                    var objectDiv = document.createElement("div");
                    if (k === "location") {
                        objectDiv.classList.add(key);
                    }
                    else if (k === "genres") {
                        objectDiv.classList.add("genre");
                    }
                    objectDiv.innerHTML = val;
                    festivalObjectDiv.appendChild(objectDiv);
                });
                contentDiv.appendChild(festivalObjectDiv);
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

    