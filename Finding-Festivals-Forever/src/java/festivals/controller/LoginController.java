/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package festivals.controller;

import festivals.model.User;

/**
 *
 * @author YatinVadhia
 */
public class LoginController {
    
    private static User CurrentUser;
    
    public boolean Authenticate(String username, String password)
    {
        CurrentUser = new User(username);
        
        return true;
    }
}
