
package com.example.demo.util;

import java.time.LocalDate;

public class ValidationUtil {

    private ValidationUtil() {
        // Utility class - prevent instantiation
    }

    public static void validateNotNull(Object value, String message) {
        if (value == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void validateAppointmentDate(LocalDate date) {
        if (date == null || date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("appointmentDate cannot be past");
        }
    }
}
