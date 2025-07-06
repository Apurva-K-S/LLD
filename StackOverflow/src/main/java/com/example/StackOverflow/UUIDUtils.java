package com.example.StackOverflow;

import java.util.UUID;

public class UUIDUtils {
    public static UUID safeParseUUID(String input) {
        try {
            return UUID.fromString(input);
        } catch (IllegalArgumentException | NullPointerException e) {
            return null; // or throw custom exception if preferred
        }
    }
}