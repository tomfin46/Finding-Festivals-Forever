/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package festivals.controller;

import festivals.resources.CountryConstantsEnum;
import festivals.service.utils.DatabaseConnection;
import java.util.Arrays;
import java.util.List;
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
}
