package ru.practicum.shareit.booking.service;
import ru.practicum.shareit.booking.dto.BookingDto;

public interface BookingService{
    BookingDto create(Long userId, Long bookingId, Boolean approved);
    BookingDto approval(Long userId, Long bookingId, Boolean approved);
    BookingDto getById(Long userId, Long bookingId, Boolean approved);
}
