/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package festivals.controller;

import festivals.service.utils.ConfigReader;
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
@RequestMapping("/festivals")
public class FestivalsController {
    
    @Autowired
    private ConfigReader configReader;
    
    @RequestMapping(method = RequestMethod.GET)
    public String loadConfig(ModelMap model) {
        configReader = new ConfigReader();
        
        String apikey = configReader.getPropertyValue("mapsapi");
        
        model.addAttribute("apikey", apikey);
        
        return "index";
    }
}
