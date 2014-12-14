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
import java.math.BigDecimal;
import java.security.Principal;
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
    @ResponseBody
    public List<IFestival> getFestivals() {
        String queryFestivals = "SELECT * FROM festivals;";
        List<IFestival> festivals = new ArrayList<>();

        festivals = constructFestivalsList(queryFestivals);

        return festivals;
    }

    @RequestMapping(value = "/festivals/favourites", method = RequestMethod.GET)
    @ResponseBody
    public List<IFestival> getFestivalsFavourites(Principal principal) {
        String queryFavourites = "SELECT f.Festivals_ID, f.Festival_Name, f.Genre, f.Start_Date, f.End_Date, f.Location, f.Location_Lat, f.Location_Lon, f.Website "
                + "FROM festivals f "
                + "INNER JOIN user_favourites uf "
                + "ON f.Festivals_ID = uf.Favourite JOIN users u "
                + "ON uf.username = u.username "
                + "WHERE u.username = ?";

        List<IFestival> festivals = new ArrayList<>();

        if (principal != null) {
            String username = principal.getName();
            festivals = constructFestivalsList(queryFavourites, username);
        }

        return festivals;
    }

    private List<IFestival> constructFestivalsList(String sqlQuery, Object... params) {
        List<IFestival> festivals = new ArrayList<>();

        try {
            List<Map<String, Object>> res = dbConnection.queryDB(sqlQuery, Arrays.asList("Festivals_ID", "Festival_Name", "Genre", "Start_Date", "End_Date", "Location", "Location_Lat", "Location_Lon", "Website"), params);

            for (Map<String, Object> r : res) {

                int id = (int) r.get("Festivals_ID");
                String name = (String) r.get("Festival_Name");
                String genres = (String) r.get("Genre");
                String startDate = (String) r.get("Start_Date");
                String endDate = (String) r.get("End_Date");
                String locationName = (String) r.get("Location");
                float locationLat = ((BigDecimal) r.get("Location_Lat")).floatValue();
                float locationLon = ((BigDecimal) r.get("Location_Lon")).floatValue();
                String website = (String) r.get("Website");
                Map flags = null;

                if (startDate.equalsIgnoreCase(endDate)) {
                    festivals.add(
                            new MusicFestival(
                                    id,
                                    name,
                                    genres,
                                    startDate,
                                    new Location(locationLat, locationLon, locationName),
                                    website,
                                    flags
                            )
                    );
                } else {
                    festivals.add(
                            new MusicFestival(
                                    id,
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
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, "Error executing SQL query", ex);
        } catch (NullPointerException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, "NullPointer from DB connection still not properly initialised", ex);
        }

        return festivals;
    }
}
