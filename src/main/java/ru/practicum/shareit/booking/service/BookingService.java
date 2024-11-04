package ru.practicum.shareit.booking.service;

import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingDtoTime;
import ru.practicum.shareit.booking.model.Status;

import java.util.Collection;

public interface BookingService {
    BookingDto create(final Long userId, BookingDtoTime bookingDtoTime);

    BookingDto approval(final Long userId, final Long bookingId, final Boolean approved);

    BookingDto getById(final Long userId, final Long bookingId);

    Collection<BookingDto> getAllBookingFromUser(final Long userId, final Status status);

    Collection<BookingDto> getAllBookingFromOwner(final Long userId, final Status status);

}
