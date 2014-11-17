/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*global document, $, Utils */
var GoogleMapsComponent = (function () {

    "use strict";

    var setSrcUrl;

    setSrcUrl = function (data) {
        var mapsComponent = document.querySelector(".mapsComponent"),
                params;

        switch (data.type) {
            case "place":
                params = '/maps/place?place=' + data.place;
                break;
            case "search":
                params = '/maps/search?query=' + data.query;
                if (data.lat && data.lon) {
                    params += '&lat=' + data.lat + '&lon=' + data.lon;
                }
                if (data.zoom) {
                    params += '&zoom=' + data.zoom;
                }
                break;
        }

        $.ajax({
            type: 'Get',
            url: Utils.getPageContext() + params,
            success: function (result) {
                var iframe = mapsComponent.querySelector("iframe"),
                        newIframe;
                if (iframe !== null) {
                    iframe.setAttribute("src", result);
                }
                else {
                    newIframe = document.createElement("iframe");
                    newIframe.setAttribute("frameborder", "0");
                    newIframe.setAttribute("src", result);
                    mapsComponent.appendChild(newIframe);
                }
            }
        });
    };

    return {
        setSrcUrl: setSrcUrl
    };
}());