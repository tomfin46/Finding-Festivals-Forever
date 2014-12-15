package festivals.controller;

import festivals.service.utils.ApplicationConstants;
import festivals.service.utils.ConfigFileProperties;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for maps endpoints
 *
 * @author Tom Finlayson
 */
@Controller
@RequestMapping("/maps")
public class MapsController {

    /**
     * Fetch a URL of Google Maps API place type
     *
     * @param place the place to insert into URL
     * @return Valid Google Maps API URL
     */
    @RequestMapping(value = "/place", method = RequestMethod.GET)
    public @ResponseBody
    String getPlaceUrl(@RequestParam(value = "place") String place) {
        ConfigFileProperties config = ConfigFileProperties.getInstance();
        String mapsAPI = config.getPropertyValue("mapsapi");
        String gmPlace = ApplicationConstants.GM_PLACE;
        try {
            String encodedPlace = URLEncoder.encode(place, "UTF-8");

            gmPlace = String.format(gmPlace, mapsAPI, encodedPlace);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MapsController.class.getName()).log(Level.SEVERE, "Error encoding place into URL compliant string", ex);
        }
        return gmPlace;
    }

    /**
     * Fetch a URL of Google Maps API search type
     *
     * @param query Search query
     * @param lat Latitude for map to focus on
     * @param lon Longitude for map to focus on
     * @param zoom Zoom level for map
     * @return Valid Google Maps API URL
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public @ResponseBody
    String getSearchUrl(@RequestParam(value = "query") String query,
            @RequestParam(value = "lat", required = false) String lat,
            @RequestParam(value = "lon", required = false) String lon,
            @RequestParam(value = "zoom", required = false) String zoom) {

        ConfigFileProperties config = ConfigFileProperties.getInstance();
        String mapsAPI = config.getPropertyValue("mapsapi");
        String gmSearch = ApplicationConstants.GM_SEARCH;

        gmSearch = String.format(gmSearch, mapsAPI, query);

        if (lat != null && !lat.isEmpty() && lon != null && !lon.isEmpty()) {
            gmSearch += String.format(ApplicationConstants.GM_CENTER_PARAM, lat, lon);
        }
        if (zoom != null && !zoom.isEmpty()) {
            gmSearch += String.format(ApplicationConstants.GM_ZOOM_PARAM, zoom);
        }

        return gmSearch;
    }

    /**
     * Fetch the Google Maps JavaScript API URL
     *
     * @return JavaScript API URL
     */
    @RequestMapping(value = "/jsUrl")
    public @ResponseBody
    String getJavascriptUrl() {
        ConfigFileProperties config = ConfigFileProperties.getInstance();
        return String.format(ApplicationConstants.GOOGLE_MAPS_JS_BASE_URL, config.getPropertyValue("mapsapi"));
    }
}
