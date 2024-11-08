package src.main.java.ru.practicum.shareit.booking.model;

import java.util.Optional;

public enum Status {
    ALL,
    PAST,
    CURRENT,
    FUTURE,
    WAITING,
    REJECTED;

    public static Optional<Status> from(String stringState) {
        for (Status status : values()) {
            if (status.name().equalsIgnoreCase(stringState)) {
                return Optional.of(status);
            }
        }
        return Optional.empty();
    }
}
