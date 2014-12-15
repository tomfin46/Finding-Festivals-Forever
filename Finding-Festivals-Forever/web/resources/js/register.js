/*global $ */
$(function () {

    "use strict";

    // Ensure register form is validated using jQuery Validator plugin
    $.validate({
        modules: 'location, security',
        onModulesLoaded: function () {
            $('#country').suggestCountry(); // use country suggestions for easy country entry
        }
    });
});
