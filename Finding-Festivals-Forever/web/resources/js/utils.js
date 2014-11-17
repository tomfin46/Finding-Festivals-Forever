/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*global document, $ */
var Utils = (function () {

    "use strict";

    var isValidVariable, isValidNonEmptyString, isValidFn,
            getPageContext, getTemperatureFormat, getSpeedFormat;

    isValidVariable = function (variable) {
        return variable !== null && typeof variable !== 'undefined';
    };

    isValidNonEmptyString = function (string) {
        return isValidVariable(string) && typeof string === 'string' && string !== '';
    };

    isValidFn = function (fn) {
        return isValidVariable(fn) && typeof fn === 'function';
    };

    getPageContext = function () {
        var elem = document.querySelector("#pageContextPath");
        if (elem && $(elem).data()) {
            return $(elem).data("pageContext");
        }
        return "";
    };
    
    getTemperatureFormat = function() {
        return $('input[name="temp"]:checked').val();
    };
    
    getSpeedFormat = function() {
        return $('input[name="speed"]:checked').val();
    };

    return {
        isValidVariable: isValidVariable,
        isValidNonEmptyString: isValidNonEmptyString,
        isValidFn: isValidFn,
        getPageContext: getPageContext,
        getTemperatureFormat: getTemperatureFormat,
        getSpeedFormat: getSpeedFormat
    };
}());