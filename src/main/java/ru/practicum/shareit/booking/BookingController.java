package ru.practicum.shareit.booking;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDtoTime;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.booking.service.BookingService;
import ru.practicum.shareit.booking.dto.BookingDto;

import java.util.Collection;

@RestController
@RequestMapping(path = "/bookings")
public class BookingController {
    private BookingService bookingService;

    @PostMapping
    public BookingDto create(@RequestHeader("X-Sharer-User-Id") Long userId, @Valid @RequestBody BookingDtoTime bookingDtoTime) {
        return bookingService.create(userId, bookingDtoTime);
    }

    @PatchMapping("/{bookingId}")
    public BookingDto approval(@RequestHeader("X-Sharer-User-Id") Long userId, @PathVariable Long bookingId,
                               @RequestParam Boolean approved
    ) {
        return bookingService.approval(userId, bookingId, approved);
    }

    @GetMapping("/{bookingId}")
    public BookingDto getById(@RequestHeader("X-Sharer-User-Id") Long userId, @PathVariable Long bookingId) {
        return bookingService.getById(userId, bookingId);
    }

    @GetMapping
    public Collection<BookingDto> getAllBookingFromUser(
            @RequestHeader("X-Sharer-User-Id") Long userId,
            @RequestParam(defaultValue = "ALL") Status status
    ) {
        return bookingService.getAllBookingFromUser(userId, status);
    }

    @GetMapping("/owner")
    public Collection<BookingDto> getAllBookingFromOwner(
            @RequestHeader("X-Sharer-User-Id") Long userId,
            @RequestParam(defaultValue = "ALL") Status status
    ) {
        return bookingService.getAllBookingFromOwner(userId, status);
    }
}
