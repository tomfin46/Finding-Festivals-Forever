package festivals.service.utils;

/**
 * Constants for use in the application
 *
 * @author Tom Finlayson
 */
public class ApplicationConstants {

    /**
     * Empty String
     */
    public static final String EMPTY_STRING = "";

    /**
     * Path for resource files
     */
    public static final String RESOURCES_PATH = "festivals/resources/";

    /**
     * Filename for config file
     */
    public static final String CONFIG_FILENAME = "config.properties";

    /**
     * Google Maps Embed API Base URL
     */
    private static final String GOOGLE_MAPS_EMBED_BASE_URL = "https://www.google.com/maps/embed/v1/";

    /**
     * Google Maps JavaScript API Base URL
     */
    public static final String GOOGLE_MAPS_JS_BASE_URL = "https://maps.googleapis.com/maps/api/js?v=3.exp&key=%s&callback=GoogleMapsComponent.initializeJsComponent";

    /**
     * Google Maps Embed API place type URL
     */
    public static final String GM_PLACE = GOOGLE_MAPS_EMBED_BASE_URL + "place?key=%s&q=%s";

    /**
     * Google Maps Embed API directions type URL
     */
    public static final String GM_DIRECTIONS = GOOGLE_MAPS_EMBED_BASE_URL + "directions?key=%s&origin=%s&destination=%s&avoid=%s";

    /**
     * Google Maps Embed API search type URL
     */
    public static final String GM_SEARCH = GOOGLE_MAPS_EMBED_BASE_URL + "search?key=%s&q=%s";

    /**
     * Google Maps Embed API view type URL
     */
    public static final String GM_VIEW = GOOGLE_MAPS_EMBED_BASE_URL + "/view?key=%s&center=%s&zoom=%s&maptype=%s";

    /**
     * Google Maps API center parameter
     */
    public static final String GM_CENTER_PARAM = "&center=%s,%s";

    /**
     * Google Maps API zoom parameter
     */
    public static final String GM_ZOOM_PARAM = "&zoom=%s";
}
