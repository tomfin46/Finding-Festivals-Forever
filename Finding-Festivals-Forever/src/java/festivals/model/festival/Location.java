package festivals.model.festival;

/**
 * Model of location with latitude, longitude and name
 *
 * @author Tom Finlayson
 */
public class Location {

    private float latitude;
    private float longitude;
    private String name;

    /**
     * Default constructor for Location
     */
    public Location() {
        
    }
    
    /**
     * Construct location object
     * 
     * @param latitude Location latitude
     * @param longitude Location longitude
     * @param name Location name
     */
    public Location(float latitude, float longitude, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }

    /**
     * Accessor method for latitude field
     * 
     * @return Location's latitude
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * Mutator method for latitude field
     * 
     * @param latitude Location's latitude
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     * Accessor method for longitude field
     * 
     * @return Location's latitude
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * Mutator method for logitude field
     * 
     * @param longitude Location's longitude
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    /**
     * Accessor method for name field
     * 
     * @return Location's name
     */
    public String getName() {
        return name;
    }

    /**
     * Mutator method for name field
     * 
     * @param name Location's name
     */
    public void setName(String name) {
        this.name = name;
    }
}
