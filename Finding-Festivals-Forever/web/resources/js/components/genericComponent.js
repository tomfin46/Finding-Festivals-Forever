/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*global document, $, Utils */

function Component(element, update) {

    "use strict";

    var component = {
        dataAttribute: {},
        set: function (key, value) {
            this.dataAttribute[key] = value;
            if (Utils.isValidFn(this._updateFn)) {
                this._updateFn(this._parent);
            }
        },
        get: function (key) {
            return this.dataAttribute[key];
        },
        _parent: element,
        _updateFn: update
    };

    $.each(element.dataset, function (key, value) {
        component.set(key, value);
    });

    return component;
}