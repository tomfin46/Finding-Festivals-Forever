/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var Utils = (function () {

    "use strict";

    var isValidVariable, isValidNonEmptyString, isValidFn;

    isValidVariable = function (variable) {
        return variable !== null && variable !== 'undefined';
    };

    isValidNonEmptyString = function (string) {
        return isValidVariable(string) && typeof string === 'string' && string !== '';
    };

    isValidFn = function (fn) {
        return isValidVariable(fn) && typeof fn === 'function';
    };

    return {
        isValidVariable: isValidVariable,
        isValidNonEmptyString: isValidNonEmptyString,
        isValidFn: isValidFn
    };
}());