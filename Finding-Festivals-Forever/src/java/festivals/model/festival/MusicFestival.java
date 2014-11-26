/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package festivals.model.festival;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Tom Finlayson
 */
public class MusicFestival extends BaseFestival {

    public MusicFestival(String name, List<String> genres, String startDate, String endDate, Location location, String website, Map flags) {
        super(FestivalType.MUSIC, name, genres, startDate, endDate, location, website, flags);
    }

}
