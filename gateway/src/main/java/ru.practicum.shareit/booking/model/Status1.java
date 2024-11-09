package ru.practicum.shareit.booking.model;

import java.util.Optional;

public enum Status1 {
    ALL,
    PAST,
    CURRENT,
    FUTURE,
    WAITING,
    REJECTED;

    public static Optional<Status1> from(String stringStatus) {
        for (Status1 status : values()) {
            if (status.name().equalsIgnoreCase(stringStatus)) {
                return Optional.of(status);
            }
        }
        return Optional.empty();
    }
}
