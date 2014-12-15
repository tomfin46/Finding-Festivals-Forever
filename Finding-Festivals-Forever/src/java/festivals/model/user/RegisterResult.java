package festivals.model.user;

/**
 * Enumeration for results from trying to register new user
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
