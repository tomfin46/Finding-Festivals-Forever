/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*global document, $, Component, GoogleMapsComponent, WeatherComponent */

/*
 * Execute when DOM is loaded
 */
$(function () {

    "use strict";
    
    $('#weatherSettings input[type=radio]').change(function() {
        WeatherComponent.convertWeatherUnits(this.value);
    });
});