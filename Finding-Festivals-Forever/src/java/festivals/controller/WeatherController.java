/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package festivals.controller;

import festivals.service.utils.ConfigFileProperties;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.aksingh.java.api.owm.CurrentWeatherData;
import net.aksingh.java.api.owm.ForecastWeatherData;
import net.aksingh.java.api.owm.OpenWeatherMap;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Tom Finlayson
 */
@Controller
@RequestMapping("/weather")
public class WeatherController {

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public @ResponseBody
    CurrentWeatherData getCurrentWeather(@RequestParam(value = "city") String city) {
        try {
            ConfigFileProperties config = ConfigFileProperties.getInstance();
            String weatherApi = config.getPropertyValue("weatherapi");

            OpenWeatherMap owm = new OpenWeatherMap(weatherApi);

            CurrentWeatherData cwd = owm.currentWeatherByCityName(city);

            // checking data retrieval was successful or not using the response code
            // response code = 200 means successful retrieval
            if (cwd.hasResponseCode() && cwd.getResponseCode() == 200) {

                return cwd;
            }

        } catch (IOException | JSONException ex) {
            Logger.getLogger(WeatherController.class.getName()).log(Level.SEVERE, "Error fetching CurrentWeatherData for " + city, ex);
        }

        return null;
    }

    @RequestMapping(value = "/forecast", method = RequestMethod.GET)
    public @ResponseBody ForecastWeatherData getForecast(@RequestParam(value = "city", required = false) String city, @RequestParam(value = "lat", required = false) float lat, @RequestParam(value = "lon", required = false) float lon) {
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
