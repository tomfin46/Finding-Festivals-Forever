/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package festivals.controller;

import festivals.model.festival.IFestival;
import festivals.model.festival.Location;
import festivals.model.festival.MusicFestival;
import festivals.resources.CountryConstantsEnum;
import festivals.service.utils.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Tom Finlayson
 */
@Controller
@RequestMapping("/index")
public class FestivalsController {

    @Autowired
    private DatabaseConnection dbConnection;

    @RequestMapping(method = RequestMethod.GET)
    public String initWebsite(ModelMap model) {
        dbConnection = DatabaseConnection.getInstance();
        countryList(model);
        return "index";
    }

    private void countryList(ModelMap model) {
        List<CountryConstantsEnum> countryList = Arrays.asList(CountryConstantsEnum.values());

        model.addAttribute("countryList", countryList);
    }

    @RequestMapping(value = "/festivals", method = RequestMethod.GET)
    public @ResponseBody  List<IFestival> getFestivals() {
        String queryFestivals = "SELECT * FROM festivals;";
        List<IFestival> festivals = new ArrayList<>();
        try {
            ResultSet res = dbConnection.queryDB(queryFestivals);
            while (res.next()) {
                String name = res.getString("Festival_Name");
                List<String> genres = Arrays.asList(res.getString("Genre").split(","));
                String startDate = res.getString("Start_Date");
                String endDate = res.getString("End_Date");
                String locationName = res.getString("Location");
                float locationLat = res.getFloat("Location_Lat");
                float locationLon = res.getFloat("Location_Lon");
                String website = res.getString("Website");
                Map flags = null;

                festivals.add(
                    new MusicFestival(
                            name,
                            genres,
                            startDate,
                            endDate,
                            new Location(locationLat, locationLon, locationName),
                            website,
                            flags
                    )
                );
            }            

        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, "Error manipulating ResultSet", ex);
        } catch (NullPointerException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, "NullPointer from DB connection still not properly initialised", ex);
        }

        return festivals;
    }
}
