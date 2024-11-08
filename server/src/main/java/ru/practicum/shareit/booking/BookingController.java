package ru.practicum.shareit.booking;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.booking.service.BookingService;
import ru.practicum.shareit.booking.dto.BookingDto;

import java.util.Collection;

@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
@Validated
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public BookingDtoTime create(@RequestHeader("X-Sharer-User-Id") Long userId, @RequestBody BookingDto bookingDto) {
        return bookingService.create(userId, bookingDto);
    }

    @PatchMapping("/{bookingId}")
    public BookingDtoTime approval(@RequestHeader("X-Sharer-User-Id") Long userId, @PathVariable Long bookingId,
                               @RequestParam Boolean approved
    ) {
        return bookingService.approval(userId, bookingId, approved);
    }

    @GetMapping("/{bookingId}")
    public BookingDtoTime getById(@RequestHeader("X-Sharer-User-Id") Long userId, @PathVariable Long bookingId) {
        return bookingService.getById(userId, bookingId);
    }

    @GetMapping
    public Collection<BookingDtoTime> getAllBookingFromUser(
            @RequestHeader("X-Sharer-User-Id") Long userId,
            @RequestParam(defaultValue = "ALL") Status status
    ) {
        return bookingService.getAllBookingFromUser(userId, status);
    }

    @GetMapping("/owner")
    public Collection<BookingDtoTime> getAllBookingFromOwner(
            @RequestHeader("X-Sharer-User-Id") Long userId,
            @RequestParam(defaultValue = "ALL") Status status
    ) {
        return bookingService.getAllBookingFromOwner(userId, status);
    }
}
