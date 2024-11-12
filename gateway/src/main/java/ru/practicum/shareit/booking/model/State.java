package ru.practicum.shareit.booking.model;

import java.util.Optional;

public enum State {
    ALL,
    PAST,
    CURRENT,
    FUTURE,
    WAITING,
    REJECTED;

    public static Optional<State> from(String stringStatus) {
        for (State status : values()) {
            if (status.name().equalsIgnoreCase(stringStatus)) {
                return Optional.of(status);
            }
        }
        return Optional.empty();
    }
}
