/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*global document, $, GoogleMapsComponent */

/*
 * Execute when DOM is loaded
 */
$(function () {

    "use strict";

    // Find all map components in the document and initialise them
    /*$(".mapsComponent").each(function () {
        GoogleMapsComponent.setup(this);
    });*/
    
    var gMapsComponent = new GoogleMapsComponent(document.querySelector(".mapsComponent"));
    

    $(".locationInput").change(function () {
        var val = $(this).val();
        var elem = document.querySelector(".mapsComponent");
        elem.setAttribute("data-place", $(this).val());
        //$(".mapsComponent").attr("data-place", $(this).val());
    });
});