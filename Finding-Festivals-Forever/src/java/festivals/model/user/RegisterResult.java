/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package festivals.model.user;

/**
 *
 * @author Tom Finlayson
 */
public enum RegisterResult {
    SUCCESS, USER_ALREADY_EXISTS, DATABASE_OUT_OF_SPACE, VALIDATION_ERROR, GENERAL_ERROR, FATAL_ERROR
}
