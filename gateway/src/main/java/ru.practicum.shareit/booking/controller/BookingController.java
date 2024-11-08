package src.main.java.ru.practicum.shareit.booking.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.booking.service.BookingService;
import ru.practicum.shareit.booking.dto.BookingDto;
import src.main.java.ru.practicum.shareit.booking.BookingClient;

import java.util.Collection;

@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
@Validated
public class BookingController {
    private final BookingService bookingService;
    private final BookingClient bookingClient;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> create(@RequestHeader("X-Sharer-User-Id") final Long userId,
                                                @RequestBody final BookingDto bookingDto) {
        return bookingClient.create(userId, bookingDto);
    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<Object> confirmation(@RequestHeader("X-Sharer-User-Id") final Long userId,
                                                      @PathVariable final Long bookingId,
                                                      @RequestParam final Boolean approved) {
        return bookingClient.confirmation(userId, bookingId, approved);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Object> getById(@RequestHeader("X-Sharer-User-Id") final Long userId,
                                                 @PathVariable final Long bookingId) {
        return bookingClient.getById(userId, bookingId);
    }

    @GetMapping
    public ResponseEntity<Object> getAllBookingsFromUser(@RequestHeader("X-Sharer-User-Id") final Long userId,
                                                         @RequestParam(defaultValue = "ALL") final Status status) {
        return bookingClient.getAllBookingsFromUser(userId, status);
    }

    @GetMapping("/owner")
    public ResponseEntity<Object> getAllBookingsFromOwner(@RequestHeader("X-Sharer-User-Id") final Long userId,
                                                          @RequestParam(defaultValue = "ALL") final Status status) {
        return bookingClient.getAllBookingsFromOwner(userId, status);
    }



}
