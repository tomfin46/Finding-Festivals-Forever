/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package festivals.model;

/**
 *
 * @author Tom Finlayson
 */
public class User {
    
    private String Username;
    
    public User(String username)
    {
        this.Username = username;
    }
    
    public String GetUsername()
    {
        return Username;
    }
}
