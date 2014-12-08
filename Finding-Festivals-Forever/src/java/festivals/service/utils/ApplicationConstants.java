/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package festivals.service.utils;

/**
 *
 * @author Tom Finlayson
 */
public class ApplicationConstants {

    public static final String EMPTY_STRING = "";
    public static final String RESOURCES_PATH = "festivals/resources/";
    public static final String CONFIG_FILENAME = "config.properties";

    /*
     Google Maps API
     */
    private static final String GOOGLE_MAPS_EMBED_BASE_URL = "https://www.google.com/maps/embed/v1/";
    public static final String GOOGLE_MAPS_JS_BASE_URL = "https://maps.googleapis.com/maps/api/js?v=3.exp&key=%s&callback=GoogleMapsComponent.initializeJsComponent";
    public static final String GM_PLACE = GOOGLE_MAPS_EMBED_BASE_URL + "place?key=%s&q=%s";
    public static final String GM_DIRECTIONS = GOOGLE_MAPS_EMBED_BASE_URL + "directions?key=%s&origin=%s&destination=%s&avoid=%s";
    public static final String GM_SEARCH = GOOGLE_MAPS_EMBED_BASE_URL + "search?key=%s&q=%s";
    public static final String GM_VIEW = GOOGLE_MAPS_EMBED_BASE_URL + "/view?key=%s&center=%s&zoom=%s&maptype=%s";
    public static final String GM_CENTER_PARAM = "&center=%s,%s";
    public static final String GM_ZOOM_PARAM = "&zoom=%s";


    /*
     Weather API
     */
    private static final String OPEN_WEATHER_MAP_BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static final String OWM_CURRENT_WEATHER = OPEN_WEATHER_MAP_BASE_URL + "weather?q=%s";
    public static final String OWM_FORECAST = OPEN_WEATHER_MAP_BASE_URL + "forecast?q=%s";

}
