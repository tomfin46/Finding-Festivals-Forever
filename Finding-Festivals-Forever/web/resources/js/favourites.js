/*global $, FestivalsList, Utils */
$(function () {

    "use strict";

    $.ajax({
        type: 'Get',
        url: Utils.getPageContext() + '/festivals/favourites',
        success: function (festivals) {
            $.each(festivals, function () {
                FestivalsList.addFestival(this);
            });
        }
    });
});


