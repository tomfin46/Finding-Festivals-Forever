/*global document,window, $ */

/*
 * Required for Facebook Share
 */
$(function (d, s, id) {
    "use strict";
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) {
        return;
    }
    js = d.createElement(s);
    js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.0";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

/*
 * Required for Twitter Share
 */
$(function (d, s, id) {
        "use strict";
    var t, js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) {
        return;
    }
    js = d.createElement(s);
    js.id = id;
    js.src = "https://platform.twitter.com/widgets.js";
    fjs.parentNode.insertBefore(js, fjs);
    return window.twttr || (t = {_e: [], ready: function (f) {
            t._e.push(f);
        }});
}(document, "script", "twitter-wjs"));