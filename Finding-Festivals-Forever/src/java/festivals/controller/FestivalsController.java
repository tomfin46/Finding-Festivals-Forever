/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package festivals.controller;

import festivals.service.utils.DatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Tom Finlayson
 */
@Controller
@RequestMapping("/")
public class FestivalsController {

    @Autowired
    private DatabaseConnection dbConnection;

    @RequestMapping(method = RequestMethod.GET)
    public String initWebsite(ModelMap model) {
        dbConnection = DatabaseConnection.getInstance();

        return "index";
    }
}
