/*global $, FestivalsList, Utils */
$(function () {

    "use strict";
    
    $('#tabs a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });

    $.ajax({
        type: 'Get',
        url: Utils.getPageContext() + '/festivals',
        success: function (festivals) {
            $.each(festivals, function () {
                FestivalsList.addFestival(this, true);
            });
        }
    });
});