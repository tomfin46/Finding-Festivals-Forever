/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function DataBinder(element) {
    // Use a jQuery object as simple PubSub
    var pubSub = $({});

    $.each($(element).data(), function (key, value) {
        var message = key + ":change";

        // Listen to change events on elements with the data-binding attribute and proxy
        // them to the PubSub, so that the change is "broadcasted" to all connected objects
        $(document).on("change", "[data-" + key + "]", function (evt) {
            var domElement = $(this);

            pubSub.trigger(message, [domElement]);
        });

        //PubSub propagates changes to all bound elements, setting value of
        // input tags or HTML content of other tags
        pubSub.on(message, function (evt, element) {
           //element.setAttribute("data-" + dataProperty, newValue);
        });
    });



    return pubSub;
}
