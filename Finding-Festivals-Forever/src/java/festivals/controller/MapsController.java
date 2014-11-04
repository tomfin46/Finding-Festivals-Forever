/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package festivals.controller;

import festivals.service.utils.ApplicationConstants;
import festivals.service.utils.ConfigFileProperties;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Tom Finlayson
 */
@Controller
@RequestMapping("/maps")
public class MapsController {
    
    
    @RequestMapping(value="/place", method = RequestMethod.GET)
    public String getPlaceUrl(@RequestParam(value="place") String place) {
        ConfigFileProperties config = ConfigFileProperties.getInstance();
        String mapsAPI = config.getPropertyValue("mapsapi");
        String gmPlace = ApplicationConstants.GM_PLACE;
        try {
            String encodedPlace = URLEncoder.encode(place, "UTF_8");
            
            gmPlace = String.format(gmPlace, mapsAPI, encodedPlace);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MapsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gmPlace;
    }
    
//        public String place(@RequestParam(value="place", defaultValue="World") String name) {

}
