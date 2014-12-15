/*global document, $, google, Utils, GeoLocation */
var GoogleMapsComponent = (function () {

    "use strict";

    var loadJsGMapsScript, setupFields, initializeJsComponent, _addMarker,
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

    initializeJsComponent = function () {
        if (google) {
            var myLatlng = new google.maps.LatLng(fields.lat, fields.lon),
                    mapDiv = document.querySelector('.map-canvas'),
                    festivalDivs = document.querySelectorAll(".festival"),
                    mapOptions = {
                        zoom: fields.zoom,
                        center: myLatlng
                    };

            if (mapDiv) {
                map = new google.maps.Map(mapDiv, mapOptions);
                infoWindow = new google.maps.InfoWindow();

                $.each(festivalDivs, function () {

                    var festivalName = this.querySelector(".festivalName").innerHTML,
                            genres = this.querySelector(".genres").innerHTML,
                            markerData = {
                                lat: this.querySelector(".location .latitude").innerHTML,
                                lon: this.querySelector(".location .longitude").innerHTML,
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
        }
    };

    _addMarker = function (markerData) {
        if (map) {
            var markerLatlng = new google.maps.LatLng(markerData.lat, markerData.lon),
                    marker = new google.maps.Marker({
                        position: markerLatlng,
                        map: map,
                        title: markerData.name
                    });

            google.maps.event.addListener(marker, 'click', function () {
                infoWindow.setContent(markerData.content);
                infoWindow.open(map, marker);

                var festivalDatas = document.querySelectorAll(".festivalData"),
                        position = {
                            coords: {
                                latitude: markerData.lat,
                                longitude: markerData.lon
                            }
                        };

                $.each(festivalDatas, function () {
                    if (this.querySelector(".name").innerHTML === markerData.name && $(this).css("display") === "none") {
                        $(this).fadeIn("slow");
                    }
                });

                GeoLocation.refreshWeatherWithNewPosition(position);
            });
        }
    };

    return {
        loadJsGMapsScript: loadJsGMapsScript,
        setupFields: setupFields,
        initializeJsComponent: initializeJsComponent
    };
}());