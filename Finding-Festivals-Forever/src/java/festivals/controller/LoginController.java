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

    /**
     * Create a connection to Login page, handle exceptions using the configured
     * enum and retrieve the information submitted by the user (username and
     * password)
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("logout", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(@ModelAttribute User user, ModelMap model) {
        dbConnection = DatabaseConnection.getInstance();
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@ModelAttribute User user, ModelMap model) {
        dbConnection = DatabaseConnection.getInstance();

        RegisterResult registerResult = RegisterResult.GENERAL_ERROR;

        try {
            registerResult = tryRegister(user);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String result;
        switch (registerResult) {
            case SUCCESS:
                result = "success";
                break;
            case USER_ALREADY_EXISTS:
                result = "alreadyexists";
                break;
            case DATABASE_OUT_OF_SPACE:
            case VALIDATION_ERROR:
            case GENERAL_ERROR:
            case FATAL_ERROR:
            default:
                result = "error";
                break;
        }

        model.addAttribute("result", result);

        return "result";
    }

    private RegisterResult tryRegister(User user) throws SQLException {
        RegisterResult registerResult = RegisterResult.FATAL_ERROR;

        if (userAlreadyExists(user)) {
            registerResult = RegisterResult.USER_ALREADY_EXISTS;
        } else {
            String hashedPassword = Utilities.encodePassword(user.getPass());
            String insertUser = "INSERT INTO users (username, password, enabled) VALUES (?,?,1);";
            String insertRole = "INSERT INTO user_roles (username, ROLE) VALUES (?, 'ROLE_USER');";

            try {
                boolean success = dbConnection.executeSQL(insertUser, user.getUsername(), hashedPassword);
                success = success && dbConnection.executeSQL(insertRole, user.getUsername());

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

    private boolean userAlreadyExists(User user) {
        String queryUser = "SELECT username, password, enabled FROM users WHERE username = ? ";
        try {
            List<Map<String, Object>> res = dbConnection.queryDB(queryUser, Arrays.asList("username", "password", "enabled"), user.getUsername());
            return res.size() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
