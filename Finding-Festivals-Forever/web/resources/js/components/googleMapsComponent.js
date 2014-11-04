/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*global console */
(function () {

    "use strict";

    var changeSrcUrl;
    var mapsComponent = document.getElementById("mapsComponent");
    console.log(mapsComponent);
    var place = mapsComponent.dataset.place;
    var context = mapsComponent.dataset.context;
    var params = {
        type: "place",
        place: place
    };
    console.log(params);
    changeSrcUrl(context, params);

    changeSrcUrl = function (element, context, params) {
        var url = context;
        switch (params.type) {
            case "place":
                url += '/maps/place?place=' + params.place;
                break;
        }
console.log(url);
        $.ajax({
            type: 'Get',
            url: url,
            success: function (data) {
                var iframe = element.querySelector("iframe");
                console.log(iframe);
                console.log(data);
                if (iframe !== null) {
                    iframe.setAttribute("src", data);
                }
                else {
                    var newIframe = document.createElement("iframe");
                    newIframe.setAttribute("frameborder", "0");
                    element.apppendChild(newIframe);
                }
            }
        })
    };

}());