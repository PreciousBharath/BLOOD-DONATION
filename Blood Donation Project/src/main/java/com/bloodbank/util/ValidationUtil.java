package com.bloodbank.util;

import java.util.regex.Pattern;

public final class ValidationUtil {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9]{10,15}$");

    private ValidationUtil() {
    }

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isValidEmail(String value) {
        return !isBlank(value) && EMAIL_PATTERN.matcher(value.trim()).matches();
    }

    public static boolean isValidPhone(String value) {
        return !isBlank(value) && PHONE_PATTERN.matcher(value.trim()).matches();
    }

    public static boolean isPositiveInt(String value) {
        if (isBlank(value)) {
            return false;
        }
        try {
            return Integer.parseInt(value.trim()) > 0;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
