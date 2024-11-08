package ru.practicum.shareit.booking.service;

import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Status;

import java.util.Collection;

public interface BookingService {
    BookingDtoTime create(final Long userId, BookingDto bookingDto);

    BookingDtoTime approval(final Long userId, final Long bookingId, final Boolean approved);

    BookingDtoTime getById(final Long userId, final Long bookingId);

    Collection<BookingDtoTime> getAllBookingFromUser(final Long userId, final Status status);

    Collection<BookingDtoTime> getAllBookingFromOwner(final Long userId, final Status status);

}
