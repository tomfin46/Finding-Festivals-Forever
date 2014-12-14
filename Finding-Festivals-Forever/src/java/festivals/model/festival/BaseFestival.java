/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package festivals.model.festival;

import java.util.Map;

/**
 * Implementation of IFestival
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

    /**
     * Construct new BaseFestival with different start and end dates
     * 
     * @param type
     * @param id
     * @param name
     * @param genres
     * @param startDate
     * @param endDate
     * @param location
     * @param website
     * @param flags
     */
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
    
    /**
     * Construct new BaseFestival with the same start and end date
     * 
     * @param type
     * @param id
     * @param name
     * @param genres
     * @param date
     * @param location
     * @param website
     * @param flags
     */
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

    /**
     * Accessor method for type field
     * 
     * @return Festival type
     */
    public FestivalType getType() {
        return type;
    }

    /**
     * Mutator method for type field
     * 
     * @param type Festival type
     */
    public void setType(FestivalType type) {
        this.type = type;
    }

    /**
     * Accessor method for id field
     * 
     * @return Festival id
     */
    public int getId() {
        return id;
    }

    /**
     * Mutator method for id field
     * 
     * @param id Festival id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Accessor method for name field
     * 
     * @return Festival name
     */
    public String getName() {
        return name;
    }

    /**
     * Mutator method for name field
     * 
     * @param name Festival name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Accessor method for genres field
     * 
     * @return Festival genres
     */
    public String getGenres() {
        return genres;
    }

    /**
     * Mutator method for genres field
     * 
     * @param genres Festival genres
     */
    public void setGenres(String genres) {
        this.genres = genres;
    }

    /**
     * Accessor method for startDate field
     * 
     * @return Festival start date
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Mutator method for startDate field
     * 
     * @param startDate Festival start date
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Accessor method for endDate field
     * 
     * @return Festival end date
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Mutator method for endDate field
     * 
     * @param endDate Festival end date
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Accessor method for date field
     * 
     * @return Festival date
     */
    public String getDate() {
        return date;
    }

    /**
     * Mutator method for date field
     * 
     * @param date Festival date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Accessor method for location field
     * 
     * @return Festival location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Mutator method for location field
     * 
     * @param location Festival location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Accessor method for website field
     * 
     * @return Festival website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Mutator method for website field
     * 
     * @param website Festival website
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * Accessor method for flags field
     * 
     * @return Festival flags
     */
    public Map getFlags() {
        return flags;
    }

    /**
     * Mutator method for flags field
     * 
     * @param flags Festival flags
     */
    public void setFlags(Map flags) {
        this.flags = flags;
    }
}
