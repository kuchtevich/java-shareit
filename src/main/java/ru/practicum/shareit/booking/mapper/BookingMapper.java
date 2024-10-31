package ru.practicum.shareit.booking.mapper;

import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.dto.BookingDtoTime;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.item.model.Item;

public class BookingMapper {
    public static Booking toBooking(BookingDto bookingDto, User user, Item item) {
        Booking booking = new Booking();
        booking.setId(item.getId());
        booking.setEnd(bookingDto.getEnd());
        booking.setStart(bookingDto.getStart());
        booking.setItem(item);
        booking.setBooker(user);
        booking.setStatus(Status.WAITING);
        return booking;
    }

    public static BookingDto toBookingDto(Booking booking) {
        return new BookingDto(
                booking.getId(),
                booking.getStart(),
                booking.getEnd(),
                booking.getItem().getId(),
                booking.getBooker().getId(),
                booking.getStatus()
        );
    }


    public static BookingDtoTime bookingDtoTime(Booking booking) {
        BookingDtoTime bookingDtoTime = new BookingDtoTime();
        bookingDtoTime.setBooker(booking.getBooker().getId());
        bookingDtoTime.setStatus(booking.getStatus());
        bookingDtoTime.setEnd(booking.getEnd());
        bookingDtoTime.setStart(booking.getStart());
        bookingDtoTime.setItem(booking.getItem().getId());
        bookingDtoTime.setId(booking.getId());
        return bookingDtoTime;
    }
}
