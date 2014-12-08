/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*global document, $, Utils */
var GoogleMapsComponent = (function () {

    "use strict";

    var loadJsGMapsScript, setupFields, setSrcUrl, initializeJsComponent, _addMarker,
            map, infoWindow, fields = {};

    loadJsGMapsScript = function () {
        $.ajax({
            type: 'Get',
            url: Utils.getPageContext() + '/maps/jsUrl',
            success: function (url) {
                var gMapsApi = document.createElement('script');
                gMapsApi.type = 'text/javascript';
                gMapsApi.src = url;
                document.body.appendChild(gMapsApi);
            }
        });
    };

    setupFields = function (data) {
        fields.lat = data.lat;
        fields.lon = data.lon;
        fields.zoom = data.zoom;
    };

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

    initializeJsComponent = function () {
        var myLatlng = new google.maps.LatLng(fields.lat, fields.lon);
        var mapOptions = {
            zoom: fields.zoom,
            center: myLatlng
        };

        var mapDiv = document.querySelector('.map-canvas');
        if (mapDiv) {
            map = new google.maps.Map(mapDiv, mapOptions);
            infoWindow = new google.maps.InfoWindow();

            var festivals = document.querySelectorAll(".festival");
            $.each(festivals, function (idx, festivalDiv) {

                var festivalName = festivalDiv.querySelector(".festivalName").innerHTML;
                var genres = festivalDiv.querySelector(".genres").innerHTML;
                var markerData = {
                    lat: festivalDiv.querySelector(".location .latitude").innerHTML,
                    lon: festivalDiv.querySelector(".location .longitude").innerHTML,
                    name: festivalName,
                    content: '<div id="content">' +
                            '<div id="siteNotice">' +
                            '</div>' +
                            '<h3 id="firstHeading" class="firstHeading">' + festivalName + '</h3>' +
                            '<div id="bodyContent">' +
                            '<p>' + genres + '</p>' +
                            '</div>' +
                            '</div>'
                };

                _addMarker(markerData);
            });
        }
    };

    _addMarker = function (markerData) {
        if (map) {
            var markerLatlng = new google.maps.LatLng(markerData.lat, markerData.lon);

            var marker = new google.maps.Marker({
                position: markerLatlng,
                map: map,
                title: markerData.name
            });

            google.maps.event.addListener(marker, 'click', function () {
                infoWindow.setContent(markerData.content);
                infoWindow.open(map, marker);
            });
        }
    };

    return {
        loadJsGMapsScript: loadJsGMapsScript,
        setupFields: setupFields,
        setSrcUrl: setSrcUrl,
        initializeJsComponent: initializeJsComponent
    };
}());