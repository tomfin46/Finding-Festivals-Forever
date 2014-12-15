package festivals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for home endpoints
 *
 * @author Tom
 */
@Controller
@RequestMapping("/index")
public class HomeController {

    /**
     * Navigate to the index page
     *
     * @return index page file name
     */
    @RequestMapping(method = RequestMethod.GET)
    public String homePage() {
        return "index";
    }

}
