package ru.practicum.shareit.booking.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDtoGate;
import ru.practicum.shareit.booking.model.Status1;
import ru.practicum.shareit.booking.BookingClient;


@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
@Validated
public class BookingController {
    private final BookingClient bookingClient;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> create(@RequestHeader("X-Sharer-User-Id") final Long userId,
                                                @RequestBody final BookingDtoGate bookingDto) {
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
                                                         @RequestParam(defaultValue = "ALL") final Status1 status) {
        return bookingClient.getAllBookingsFromUser(userId, status);
    }

    @GetMapping("/owner")
    public ResponseEntity<Object> getAllBookingsFromOwner(@RequestHeader("X-Sharer-User-Id") final Long userId,
                                                          @RequestParam(defaultValue = "ALL") final Status1 status) {
        return bookingClient.getAllBookingsFromOwner(userId, status);
    }



}
