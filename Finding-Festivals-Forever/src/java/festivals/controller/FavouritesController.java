/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package festivals.controller;

import festivals.model.user.User;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Tom
 */
@Controller
public class FavouritesController {

    @Autowired
    DatabaseConnection dbConnection;

    @RequestMapping(value = "/favourites", method = RequestMethod.GET)
    public String favourites(Principal principal, ModelMap model) {
        dbConnection = DatabaseConnection.getInstance();
        return "favourites";
    }

    @RequestMapping(value = "/addToFavourites", method = RequestMethod.GET)
    @ResponseBody
    public boolean addToFavourites(Principal principal, @RequestParam(value = "festival") int festivalId) {
        dbConnection = DatabaseConnection.getInstance();

        if (principal != null) {
            String addFavourite = "INSERT INTO user_favourites (Username, Favourite) VALUES (?, ?);";
            String username = principal.getName();

            dbConnection.updateDB(addFavourite, username, festivalId);
        }
        
        return true;
    }
    
    @RequestMapping(value = "/removeFromFavourites", method = RequestMethod.GET)
    @ResponseBody
    public boolean removeFromFavourites(Principal principal, @RequestParam(value = "festival") int festivalId) {
        dbConnection = DatabaseConnection.getInstance();

        if (principal != null) {
            String addFavourite = "DELETE FROM user_favourites WHERE Username=? AND Favourite=?;";
            String username = principal.getName();

            dbConnection.updateDB(addFavourite, username, festivalId);
        }
        
        return true;
    }
    
    @RequestMapping(value = "/isFavourite", method = RequestMethod.GET)
    @ResponseBody
    public boolean isFavourite(Principal principal, @RequestParam(value = "festival") int festivalId) {
        dbConnection = DatabaseConnection.getInstance();

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
