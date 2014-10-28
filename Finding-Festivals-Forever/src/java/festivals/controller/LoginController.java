/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package festivals.controller;

import festivals.model.user.LoginResult;
import festivals.model.user.User;
import festivals.service.utils.DatabaseConnection;
import festivals.service.utils.Utilities;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

/**
 *
 * @author YatinVadhia
 */
@Controller
public class LoginController {

    @Autowired
    private DatabaseConnection dbConnection;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute User user, ModelMap model) {
        dbConnection = DatabaseConnection.getInstance();

        LoginResult loginResult = tryLogin(user);

        model.addAttribute("result", loginResult);

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
}
