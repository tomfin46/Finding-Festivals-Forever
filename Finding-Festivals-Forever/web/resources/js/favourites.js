/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {

    "use strict";

    $.ajax({
        type: 'Get',
        url: Utils.getPageContext() + '/index/festivals/favourites',
        success: function (festivals) {
            $.each(festivals, function (idx, festival) {
                FestivalsList.addFestival(festival);
            });
        }
    });
});


