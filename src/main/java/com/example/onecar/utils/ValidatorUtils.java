package com.example.onecar.utils;

public class ValidatorUtils {
    public static boolean validId(Long id) {
        return id != null && id > 0;
    }

    public static boolean notValidId(Long id) {
        return !validId(id);
    }

    public static boolean notBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }

    public static boolean equalIgnoreCase(String s1, String s2) {
        if(s1 == null) {
            return s2 == null;
        }

        return s1.toLowerCase().trim().equals(s2.toLowerCase().trim());
    }
}
