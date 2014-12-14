/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package festivals.model.user;

/**
 * Model for User
 *
 * @author Tom Finlayson
 */
public class User {
    
    private String username;
    private String pass;
    private String name;
    private String email;
    private String postcode;
    private String country;
        
    /**
     * Accessor method for username field
     * 
     * @return User's username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * Mutator method for username field
     * 
     * @param username User's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Accessor method for pass field
     * 
     * @return User's password
     */
    public String getPass() {
        return pass;
    }

    /**
     * Mutator method for pass field
     * 
     * @param pass User's password
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * Accessor method for name field
     * 
     * @return User's name
     */
    public String getName() {
        return name;
    }

    /**
     * Mutator method for name field
     * 
     * @param name User's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Accessor method for email field
     * 
     * @return User's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Mutator method for email field
     * 
     * @param email User's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Accessor method for postcode field
     * 
     * @return User's postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Mutator method for postcode field
     * 
     * @param postcode User's postcode
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * Accessor method for country field
     * 
     * @return User's country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Mutator method for country field
     * 
     * @param country User's country
     */
    public void setCountry(String country) {
        this.country = country;
    }
}
