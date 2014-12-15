package festivals.controller;

import festivals.service.utils.ConfigFileProperties;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.aksingh.java.api.owm.ForecastWeatherData;
import net.aksingh.java.api.owm.OpenWeatherMap;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for weather endpoints
 *
 * @author Tom Finlayson
 */
@Controller
@RequestMapping("/weather")
public class WeatherController {

    /**
     * Fetch weather forecast for supplied location, either city name or
     * latitude and longitude coordinates using Open Weather Map Java API
     *
     * @param city City name to fetch forecast for
     * @param lat Latitude coordinate to fetch forecast for
     * @param lon Longitude coordinate to fetch forecast for
     * @return ForecastWeatherData containing list of forecast objects for
     * supplied location
     */
    @RequestMapping(value = "/forecast", method = RequestMethod.GET)
    public @ResponseBody
    ForecastWeatherData getForecast(@RequestParam(value = "city", required = false) String city, @RequestParam(value = "lat", required = false) float lat, @RequestParam(value = "lon", required = false) float lon) {
        ConfigFileProperties config = ConfigFileProperties.getInstance();
        String weatherApi = config.getPropertyValue("weatherapi");

        OpenWeatherMap owm = new OpenWeatherMap(weatherApi);

        ForecastWeatherData fwd = null;

        if ((city != null && !city.isEmpty()) && (lat == 0.0 && lon == 0.0)) {
            fwd = getForecastByCity(owm, city);
        } else if ((city == null || city.isEmpty()) && (lat != 0.0 && lon != 0.0)) {
            fwd = getForecastByLocation(owm, lat, lon);
        } else if ((city != null && !city.isEmpty()) && (lat != 0.0 && lon != 0.0)) {
            fwd = getForecastByLocation(owm, lat, lon);
        }

        // checking data retrieval was successful or not using the response code
        // response code = 200 means successful retrieval
        if (fwd != null && fwd.hasResponseCode() && fwd.getResponseCode().equalsIgnoreCase("200")) {

            return fwd;
        }

        return null;
    }

    /**
     * Fetch forecast for specified city
     * 
     * @param owm OpenWeatherMap object containing project's valid API key
     * @param city City to query
     * @return Forecast data for provided city
     */
    private ForecastWeatherData getForecastByCity(OpenWeatherMap owm, String city) {
        ForecastWeatherData fwd = null;

        try {
            fwd = owm.forecastWeatherByCityName(city);

        } catch (IOException | JSONException ex) {
            Logger.getLogger(WeatherController.class
                    .getName()).log(Level.SEVERE, "Error fetching ForecastWeatherData for " + city, ex);
        }

        return fwd;
    }

    /**
     * Fetch forecast for specified latitude/longitude location
     * 
     * @param owm OpenWeatherMap object containing project's valid API key
     * @param lat Latitude of location
     * @param lon Longitude of location
     * @return Forecast data for provided location
     */
    private ForecastWeatherData getForecastByLocation(OpenWeatherMap owm, float lat, float lon) {
        ForecastWeatherData fwd = null;

        try {
            fwd = owm.forecastWeatherByCoordinates(lat, lon);

        } catch (IOException | JSONException ex) {
            Logger.getLogger(WeatherController.class
                    .getName()).log(Level.SEVERE, "Error fetching ForecastWeatherData for lat:" + lat + ", lon: " + lon, ex);
        }

        return fwd;
    }
}
