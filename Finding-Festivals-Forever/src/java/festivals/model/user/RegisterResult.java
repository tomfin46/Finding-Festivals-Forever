/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package festivals.model.user;

/**
 * Enum for results from trying to register new user
 *
 * @author Tom Finlayson
 */
public enum RegisterResult {

    /**
     * Registered successfully
     */
    SUCCESS,

    /**
     * Username already exists in database
     */
    USER_ALREADY_EXISTS,

    /**
     * General error when trying to register new user
     */
    GENERAL_ERROR,

    /**
     * Fatal error when trying to register new user
     */
    FATAL_ERROR
}
