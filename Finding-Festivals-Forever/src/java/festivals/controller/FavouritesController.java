package festivals.controller;

import festivals.service.utils.DatabaseConnection;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for favourites endpoints
 *
 * @author Tom
 */
@Controller
public class FavouritesController {

    @Autowired
    DatabaseConnection dbConnection;

    /**
     * Navigate to the favourites page
     *
     * @return Favourites page file name
     */
    @RequestMapping(value = "/favourites", method = RequestMethod.GET)
    public String favourites() {
        return "favourites";
    }

    /**
     * Add a favourite festival to the currently logged in user
     *
     * @param principal Currently logged in user
     * @param festivalId Id of festival to add to user's favourites
     * @return Update has executed
     */
    @RequestMapping(value = "/addToFavourites", method = RequestMethod.GET)
    @ResponseBody
    public boolean addToFavourites(Principal principal, @RequestParam(value = "festival") int festivalId) {
        dbConnection = DatabaseConnection.getInstance();

        // If the user is logged in then principal is valid
        if (principal != null) {
            String addFavourite = "INSERT INTO user_favourites (Username, Favourite) VALUES (?, ?);";
            String username = principal.getName();

            dbConnection.updateDB(addFavourite, username, festivalId);

            return true;
        }

        return false;
    }

    /**
     * Remove a favourite festival from the currently logged in user
     *
     * @param principal Currently logged in user
     * @param festivalId Id of festival to remove from user's favourites
     * @return Update has executed
     */
    @RequestMapping(value = "/removeFromFavourites", method = RequestMethod.GET)
    @ResponseBody
    public boolean removeFromFavourites(Principal principal, @RequestParam(value = "festival") int festivalId) {
        dbConnection = DatabaseConnection.getInstance();

        // If the user is logged in then principal is valid
        if (principal != null) {
            String addFavourite = "DELETE FROM user_favourites WHERE Username=? AND Favourite=?;";
            String username = principal.getName();

            dbConnection.updateDB(addFavourite, username, festivalId);

            return true;
        }

        return false;
    }

    /**
     * Check if festival is favourite of the current user
     *
     * @param principal Currently logged in user
     * @param festivalId Id of festival to check
     * @return Festival is favourite
     */
    @RequestMapping(value = "/isFavourite", method = RequestMethod.GET)
    @ResponseBody
    public boolean isFavourite(Principal principal, @RequestParam(value = "festival") int festivalId) {
        dbConnection = DatabaseConnection.getInstance();

        // If the user is logged in then principal is valid
        if (principal != null) {
            String addFavourite = "SELECT Username, Favourite FROM user_favourites WHERE Username = ? AND Favourite = ?";
            String username = principal.getName();

            try {
                List<Map<String, Object>> res = dbConnection.queryDB(addFavourite, Arrays.asList("Username", "Favourite"), username, festivalId);
                if (res.size() > 0) {
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(FavouritesController.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }

        return false;
    }
}
