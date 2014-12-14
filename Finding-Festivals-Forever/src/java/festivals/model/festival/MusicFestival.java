/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package festivals.model.festival;

import java.util.Map;

/**
 * Implementation of IFestival for music festival type
 *
 * @author Tom Finlayson
 */
public class MusicFestival extends BaseFestival {

    /**
     * Construct new MusicFestival with different start and end dates
     * 
     * @param id
     * @param name
     * @param genres
     * @param startDate
     * @param endDate
     * @param location
     * @param website
     * @param flags
     */
    public MusicFestival(int id, String name, String genres, String startDate, String endDate, Location location, String website, Map flags) {
        super(FestivalType.MUSIC, id, name, genres, startDate, endDate, location, website, flags);
    }
    
    /**
     * Construct new MusicFestival with same start and end date
     * 
     * @param id
     * @param name
     * @param genres
     * @param date
     * @param location
     * @param website
     * @param flags
     */
    public MusicFestival(int id, String name, String genres, String date, Location location, String website, Map flags) {
        super(FestivalType.MUSIC, id, name, genres, date, location, website, flags);
    }
}
