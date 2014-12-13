/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package festivals.controller;

import festivals.model.user.LoginResult;
import festivals.model.user.RegisterResult;
import festivals.model.user.User;
import festivals.service.utils.DatabaseConnection;
import festivals.service.utils.Utilities;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author YatinVadhia
 */
@Controller
public class LoginController {

    @Autowired
    private DatabaseConnection dbConnection;

    //-------------------favourites
    @RequestMapping(value = "/favorites", method = RequestMethod.POST)
    public String favoritesPost(@ModelAttribute User user, ModelMap model, User favorites) {
        dbConnection = DatabaseConnection.getInstance();
        LoginResult loginResult = LoginResult.FATAL_ERROR;
        loginResult = tryLogin(favorites);
        model.addAttribute("result", loginResult);
        return "result";
    }

    @RequestMapping(value = "/favorites", method = RequestMethod.GET)
    public String favorites(@ModelAttribute User user, ModelMap model) {
        dbConnection = DatabaseConnection.getInstance();
        return "favorites";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }

    //-------------------login
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute User user, ModelMap model) {
        dbConnection = DatabaseConnection.getInstance();

        LoginResult loginResult = tryLogin(user);

        model.addAttribute("result", loginResult);

        model.addAttribute("username", user.getUsername());
        model.addAttribute("hashedpassword", Utilities.hashString(user.getPassword()));

        return "result";
    }
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login(@ModelAttribute User user, ModelMap model) {
//        dbConnection = DatabaseConnection.getInstance();
//        return "login";
//    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user, ModelMap model) {
        dbConnection = DatabaseConnection.getInstance();

        RegisterResult registerResult = RegisterResult.GENERAL_ERROR;

        if (validateUser(user)) {
            registerResult = tryRegister(user);
        } else {
            registerResult = RegisterResult.VALIDATION_ERROR;
        }

        model.addAttribute("result", registerResult);

        model.addAttribute("username", user.getUsername());
        model.addAttribute("hashedpassword", Utilities.hashString(user.getPassword()));

        return "result";
    }

    private LoginResult tryLogin(User user) {
        String queryUser = "SELECT * FROM users WHERE username='" + user.getUsername() + "';";
        LoginResult loginResult = LoginResult.FATAL_ERROR;
        String hashedPassword = Utilities.hashString(user.getPassword());

        try {
            ResultSet res = dbConnection.queryDB(queryUser);

            if (res.isBeforeFirst()) {
                // Assumption made that only one row would be returned and that we don't have duplicated usernames
                res.next();

                String dbPass = res.getString("password");
                if (dbPass.equals(hashedPassword)) {
                    loginResult = LoginResult.SUCCESS;
                } else {
                    loginResult = LoginResult.PASSWORD_INCORRECT;
                }
            } else {
                // Empty ResultSet therefore user doesn't exist
                loginResult = LoginResult.USER_DOES_NOT_EXIST;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, "Error manipulating ResultSet", ex);
        } catch (NullPointerException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, "NullPointer from DB connection still not properly initialised", ex);
        }

        return loginResult;
    }

    private RegisterResult tryRegister(User user) {
        RegisterResult registerResult = RegisterResult.FATAL_ERROR;

        LoginResult loginResult = tryLogin(user);

        if (loginResult != LoginResult.USER_DOES_NOT_EXIST) {
            registerResult = RegisterResult.USER_ALREADY_EXISTS;
        } else {
            String hashedPassword = Utilities.hashString(user.getPassword());
            String insertUser = "INSERT INTO users (username, password, name, email, country, postcode) "
                    + "VALUES ('" + user.getUsername() + "','" + hashedPassword + "','" + user.getName() + "','" + user.getEmail() + "','" + user.getCountry() + "','" + user.getPostcode() + "');";

            try {
                boolean success = dbConnection.executeSQL(insertUser);

                if (success) {
                    registerResult = RegisterResult.SUCCESS;
                } else {
                    registerResult = RegisterResult.GENERAL_ERROR;
                }
            } catch (NullPointerException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, "NullPointer from DB connection still not properly initialised", ex);
            }
        }

        return registerResult;
    }

    private boolean validateUser(User user) {
        return true;
    }
}
