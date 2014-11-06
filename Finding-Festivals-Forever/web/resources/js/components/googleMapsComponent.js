/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*global $, Utils */
var GoogleMapsComponent = (function () {

    "use strict";

    var changeSrcUrl = function (element) {
        if (element && $(element).data() && element.className === "mapsComponent") {
            var $elem = $(element),
                    component = $elem.data("component"),
                    context, params;

            if (Utils.isValidVariable(component)) {
                context = component.get("context");
                switch (component.get("type")) {
                    case "place":
                        params = '/maps/place?place=' + component.get("place");
                        break;
                }

                if (Utils.isValidNonEmptyString(context) && Utils.isValidNonEmptyString(params)) {
                    $.ajax({
                        type: 'Get',
                        url: context + params,
                        success: function (result) {
                            var iframe = element.querySelector("iframe"),
                                    newIframe;
                            if (iframe !== null) {
                                iframe.setAttribute("src", result);
                            }
                            else {
                                newIframe = document.createElement("iframe");
                                newIframe.setAttribute("frameborder", "0");
                                newIframe.setAttribute("src", result);
                                element.appendChild(newIframe);
                            }
                        }
                    });
                }
            }
        }
    };

    return {
        changeSrcUrl: changeSrcUrl
    };
}());