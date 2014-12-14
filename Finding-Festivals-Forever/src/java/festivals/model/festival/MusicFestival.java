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

    public MusicFestival(int id, String name, String genres, String startDate, String endDate, Location location, String website, Map flags) {
        super(FestivalType.MUSIC, id, name, genres, startDate, endDate, location, website, flags);
    }
    
    public MusicFestival(int id, String name, String genres, String date, Location location, String website, Map flags) {
        super(FestivalType.MUSIC, id, name, genres, date, location, website, flags);
    }
    
}
