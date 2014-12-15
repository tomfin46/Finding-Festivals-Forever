package festivals.service.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Utility methods
 *
 * @author Tom
 */
public class Utilities {

    /**
     * Use BCryptPasswordEncoder to hash password
     *
     * @param password Password to encode
     * @return Hashed password
     */
    public static String encodePassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

}
