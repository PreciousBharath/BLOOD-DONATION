package com.bloodbank.util;

import org.mindrot.jbcrypt.BCrypt;

public final class PasswordUtil {
    private PasswordUtil() {
    }

    public static String hash(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(10));
    }

    public static boolean matches(String plainTextPassword, String hashedPassword) {
        return hashedPassword != null && BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
