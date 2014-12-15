
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*global document, $, GoogleMapsComponent, WeatherComponent, FestivalsList */

/*
 * Execute when DOM is loaded
 */
$(function () {

    "use strict";

    GoogleMapsComponent.loadJsGMapsScript();

    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(GeoLocation.geolocationSuccess, GeoLocation.geolocationError);
    }
    else {
        GeoLocation.useDefault();
    }

    $('#weatherSettings input[type=radio]').change(function () {
        WeatherComponent.convertWeatherUnits(this.value);
    });

    $.ajax({
        type: 'Get',
        url: Utils.getPageContext() + '/festivals',
        success: function (festivals) {
            $.each(festivals, function (idx, festival) {
                FestivalsList.addFestival(festival);
            });
        }
    });

    Utils.setUpDynamicBackToTop();
});
