/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*global document, $, Component, GoogleMapsComponent */

/*
 * Execute when DOM is loaded
 */
$(function () {

    "use strict";

    var initMapsComonent;

    initMapsComonent = function () {
        var elem = document.querySelector(".mapsComponent"),
                gMapsComponent = new Component(elem, GoogleMapsComponent.changeSrcUrl);
        $(elem).data("component", gMapsComponent);
        GoogleMapsComponent.changeSrcUrl(elem);


        $(".locationInput").change(function () {
            var val = $(this).val(),
                    mapComponent = document.querySelector(".mapsComponent");
            $(mapComponent).data("component").set("place", val);
        });
    };
    
    initMapsComonent();
    
    $('#weatherSettings input[type=radio]').change(function() {
        WeatherComponent.convertWeatherUnits(this.value);
    });
});