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
public class BaseFestival implements IFestival {

    private FestivalType type;

    private int id;
    
    private String name;

    private String genres;
    
    private String startDate;
    
    private String endDate;
    
    private String date;

    private Location location;
    
    private String website;
    
    private Map flags;

    public BaseFestival(FestivalType type, int id, String name, String genres, String startDate, String endDate, Location location, String website, Map flags) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.website = website;
        this.flags = flags;
    }
    
    public BaseFestival(FestivalType type, int id, String name, String genres, String date, Location location, String website, Map flags) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.date = date;
        this.location = location;
        this.website = website;
        this.flags = flags;
    }

    public FestivalType getType() {
        return type;
    }

    public void setType(FestivalType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Map getFlags() {
        return flags;
    }

    public void setFlags(Map flags) {
        this.flags = flags;
    }

    
}
