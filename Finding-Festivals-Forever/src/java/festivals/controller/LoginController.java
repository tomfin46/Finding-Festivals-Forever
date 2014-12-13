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
        try {
            loginResult = tryLogin(favorites);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.addAttribute("result", loginResult);
        return "result";
    }

    @RequestMapping(value = "/favorites", method = RequestMethod.GET)
    public String favorites(@ModelAttribute User user, ModelMap model) {
        dbConnection = DatabaseConnection.getInstance();
        return "favorites";
    }

    //-------------------login
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@ModelAttribute User user, ModelMap model) {
        dbConnection = DatabaseConnection.getInstance();

        LoginResult loginResult = LoginResult.FATAL_ERROR;
        try {
            loginResult = tryLogin(user);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.addAttribute("result", loginResult);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("hashedpassword", Utilities.hashString(user.getPassword()));
        return "result";
    }

    }
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login(@ModelAttribute User user, ModelMap model) {
//        dbConnection = DatabaseConnection.getInstance();
//        return "login";
//    }

    //-------------------register
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@ModelAttribute User user, ModelMap model) {
        dbConnection = DatabaseConnection.getInstance();

        RegisterResult registerResult = RegisterResult.GENERAL_ERROR;

        if (validateUser(user)) {
            try {
                registerResult = tryRegister(user);
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            registerResult = RegisterResult.VALIDATION_ERROR;
        }

        model.addAttribute("result", registerResult);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("hashedpassword", Utilities.hashString(user.getPassword()));

        return "result";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(@ModelAttribute User user, ModelMap model) {
        dbConnection = DatabaseConnection.getInstance();
        return "register";
    }

    public LoginResult tryLogin(User user) throws SQLException {
        String queryUser = "SELECT userid, username, password FROM users WHERE username = ? "; //ck
        LoginResult loginResult = LoginResult.FATAL_ERROR;
        String hashedPassword = Utilities.hashString(user.getPassword());

        try {
            List<Map<String, Object>> res = dbConnection.queryDB(queryUser, Arrays.asList("userid", "username", "password"), user.getUsername());

            if (res.size() > 0) {
                Map<String, Object> r = res.get(0);
                
                String username = (String) r.get("username");
                
                int userid = (int) r.get("userid");
                String passInDB = (String) r.get("password");

                if (passInDB.equals(hashedPassword)) {
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
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, "Possible NullPointer from DB connection still not properly initialised", ex);
        }

        return loginResult;
    }

    private RegisterResult tryRegister(User user) throws SQLException {
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
