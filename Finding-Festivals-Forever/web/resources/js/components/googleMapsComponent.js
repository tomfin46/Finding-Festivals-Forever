/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*global document, $ */
/*var GoogleMapsComponent = (function () {
 
 "use strict";
 
 var setupComponent, changeSrcUrl, updateComponent, binder;
 
 setupComponent = function (mapsComponent) {
 updateComponent(mapsComponent);
 
 binder = new DataBinder(mapsComponent);
 var data = $(mapsComponent).data();
 $.each(mapsComponent.dataset, function (key, value) {
 var message = key + ":change";
 
 binder.on(message, function (evt, component) {
 updateComponent(component);
 });
 });
 };
 
 updateComponent = function (mapsComponent) {
 if (mapsComponent && mapsComponent.dataset && mapsComponent.className === "mapsComponent") {
 // Pull out components data values from the dataset object (declared using data-* attributes in the html
 var type = mapsComponent.dataset.type,
 context = mapsComponent.dataset.context,
 data = {
 type: type
 };
 
 // Add required parameters for components declared type
 switch (type) {
 case "place":
 data.params = {
 place: mapsComponent.dataset.place
 };
 break;
 }
 
 // If we added parameters correctly then proceed to change the url
 if (data.params) {
 changeSrcUrl(mapsComponent, context, data);
 }
 }
 };
 
 changeSrcUrl = function (element, context, data) {
 if (element && data) {
 var url = context;
 
 switch (data.type) {
 case "place":
 url += '/maps/place?place=' + data.params.place;
 break;
 }
 
 $.ajax({
 type: 'Get',
 url: url,
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
 };
 
 return {
 setup: setupComponent
 };
 
 }());*/



function GoogleMapsComponent(element) {
    var binder = new DataBinder(element),
            component = {
                dataAttribute: {},
                set: function (key, value) {
                    this.dataAttribute[key] = value;
                    updateComponent();
                    this._binder.trigger("", [key, value, this]);
                },
                get: function (key) {
                    return this.dataAttribute[key];
                },
                updateComponent: function () {
                    if (this._element && this._element.dataset && this._element.className === "mapsComponent") {
                        var type = this._element.dataset.type,
                                context = this._element.dataset.context,
                                data = {
                                    type: type
                                };

                        // Add required parameters for components declared type
                        switch (type) {
                            case "place":
                                data.params = {
                                    place: this._element.dataset.place
                                };
                                break;
                        }

                        // If we added parameters correctly then proceed to change the url
                        if (data.params) {
                            this.changeSrcUrl(context, data);
                        }
                    }
                },
                changeSrcUrl: function (context, data) {
                    if (this._element && data) {
                        var url = context,
                                self = this;

                        switch (data.type) {
                            case "place":
                                url += '/maps/place?place=' + data.params.place;
                                break;
                        }

                        $.ajax({
                            type: 'Get',
                            url: url,
                            success: function (result) {
                                var iframe = self._element.querySelector("iframe"),
                                        newIframe;

                                if (iframe !== null) {
                                    iframe.setAttribute("src", result);
                                }
                                else {
                                    newIframe = document.createElement("iframe");
                                    newIframe.setAttribute("frameborder", "0");
                                    newIframe.setAttribute("src", result);
                                    self._element.appendChild(newIframe);
                                }
                            }
                        });
                    }
                },
                _element: element,
                _binder: binder
            };

    $.each(element.dataset, function (key, value) {
        var message = key + ":change";

        binder.on(message, function (evt, key, newVal, initiator) {
            if (initiator !== component) {
                component.set(key, newVal);
            }
        });
    });

    component.updateComponent();

    return component;
}