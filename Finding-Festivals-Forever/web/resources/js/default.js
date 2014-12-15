/*global document, navigator, $, GoogleMapsComponent, GeoLocation, WeatherComponent, FestivalsList, Utils */

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
            $.each(festivals, function () {
                FestivalsList.addFestival(this);
            });
        }
    });

    Utils.setUpDynamicBackToTop();
});
