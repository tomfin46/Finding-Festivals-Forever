/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*global document, $ */
var Utils = (function () {

    "use strict";

    var isValidVariable, isValidNonEmptyString, isValidFn,
            getPageContext, getCsrfToken, getTemperatureFormat,
            getSpeedFormat, setUpDynamicBackToTop;

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
    
    getCsrfToken = function () {
        var elem = document.querySelector("#csrfToken");
        if (elem && $(elem).data()) {
            return $(elem).data("csrfToken");
        }
        return "";
    };

    getTemperatureFormat = function () {
        return $('input[name="temp"]:checked').val();
    };

    getSpeedFormat = function () {
        return $('input[name="speed"]:checked').val();
    };

    setUpDynamicBackToTop = function () {
        var offset = 220,
                duration = 500;
        
        $(window).scroll(function () {
            if ($(this).scrollTop() > offset) {
                $('.back-to-top').fadeIn(duration);
            } else {
                $('.back-to-top').fadeOut(duration);
            }
        });

        $('.back-to-top').click(function (event) {
            event.preventDefault();
            $('html, body').animate({scrollTop: 0}, duration);
            return false;
        });
    };

    return {
        isValidVariable: isValidVariable,
        isValidNonEmptyString: isValidNonEmptyString,
        isValidFn: isValidFn,
        getPageContext: getPageContext,
        getCsrfToken: getCsrfToken,
        getTemperatureFormat: getTemperatureFormat,
        getSpeedFormat: getSpeedFormat,
        setUpDynamicBackToTop: setUpDynamicBackToTop
    };
}());