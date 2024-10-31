package ru.practicum.shareit.booking.service;
import io.micrometer.core.instrument.config.validate.ValidationException;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Status;

import java.util.Collection;

public interface BookingService{
    BookingDto create(Long userId, BookingDto bookingDto) throws ValidationException;
    BookingDto approval(Long userId, Long bookingId, Boolean approved);
    BookingDto getById(Long userId, Long bookingId, Boolean approved);
    Collection<BookingDto> getAllBookingFromUser(Long userId, Status status);
    Collection<BookingDto> getAllBookingFromOwner(Long userId, Status status);
}
