package ru.practicum.shareit.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.shareit.booking.dto.BookingDtoGate;
import ru.practicum.shareit.client.*;
import ru.practicum.shareit.booking.model.Status1;


import java.util.Map;

@Service
public class BookingClient extends BaseClient {
    private static final String API_PREFIX = "/bookings";

    @Autowired
    public BookingClient(@Value("${shareit-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
                        .build()
        );
    }

    public ResponseEntity<Object> create(final long userId, final BookingDtoGate bookingDtoGate) {
        return post("", userId, bookingDtoGate);
    }

    public ResponseEntity<Object> confirmation(final long userId, final long bookingId, final boolean approved) {
        final Map<String, Object> parameters = Map.of("approved", approved);
        return patch("/" + bookingId + "?approved={approved}", userId, parameters, null);
    }

    public ResponseEntity<Object> getById(final long userId, final long bookingId) {
        return get("/" + bookingId, userId);
    }

    public ResponseEntity<Object> getAllBookingsFromUser(final long userId, final Status1 status) {
        final Map<String, Object> parameters = Map.of("state", status.name());
        return get("?state={state}", userId, parameters);
    }

    public ResponseEntity<Object> getAllBookingsFromOwner(final long userId, final Status1 status) {
        final Map<String, Object> parameters = Map.of("state", status.name());
        return get("/owner?state={state}", userId, parameters);
    }
}
