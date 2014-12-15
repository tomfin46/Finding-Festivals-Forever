package festivals.controller;

import festivals.model.festival.BaseFestival;
import festivals.model.festival.IFestival;
import festivals.model.festival.Location;
import festivals.model.festival.MusicFestival;
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
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for festival endpoints
 *
 * @author Tom Finlayson
 */
@Controller
@RequestMapping("/festivals")
public class FestivalsController {

    @Autowired
    private DatabaseConnection dbConnection;

    /**
     * Fetch a list of all the festivals currently stored in the database
     *
     * @return List of IFestivals
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<IFestival> getFestivals() {
        String queryFestivals = "SELECT * FROM festivals;";
        return constructFestivalsList(queryFestivals);
    }

    /**
     * Fetch a list of all the festivals currently stored in the database that
     * the currently logged in user has favourited
     *
     * @param principal Currently logged in user
     * @return List of IFestivals
     */
    @RequestMapping(value = "/favourites", method = RequestMethod.GET)
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

    /**
     * Navigate to the manage festivals page
     * Restricted to only users with administrator access
     *
     * @return Manage Festivals page file name
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String manageFestivals() {
        return "festivalsadmin";
    }

    /**
     * Add new festival to the database
     * Restricted to only users with administrator access
     *
     * @param festival Festival to add
     * @return Manage Festivals page file name
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/manage/add", method = RequestMethod.POST)
    public String addFestival(BaseFestival festival) {

        String insertFestival = "INSERT INTO festivals (Festival_Name, Genre, Start_Date, End_Date, Location, Location_Lat, Location_Lon, Website) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        dbConnection.executeSQL(insertFestival,
                festival.getName(), festival.getGenres(), festival.getStartDate(), festival.getEndDate(),
                festival.getLocation().getName(), festival.getLocation().getLatitude(), festival.getLocation().getLongitude(), festival.getWebsite());

        return "festivalsadmin";
    }

    /**
     * Remove festival from the database
     * Restricted to only users with administrator access
     *
     * @param festivalId Festival to remove
     * @return Manage Festivals page file name
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/manage/remove", method = RequestMethod.POST)
    public String removeFestival(int festivalId) {

        String removeFavourite = "DELETE FROM festivals WHERE Festivals_ID=?";

        dbConnection.updateDB(removeFavourite, festivalId);

        return "festivalsadmin";
    }

    /**
     * Construct a list of festivals to be returned to the client
     * 
     * @param sqlQuery query for which festivals to return
     * @param params parameters for SQL query
     * @return List of festivals for client
     */
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
