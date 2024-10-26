package ru.practicum.shareit.booking.mapper;

import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.booking.dto.BookingDtoTime;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.item.model.Item;

public class BookingMapper {
    public static Booking toBooking(BookingDtoTime bookingDtoTime, User user, Item item) {
    Booking booking = new Booking();
    booking.setId(item.getId());
    booking.setEnd(bookingDtoTime.getEnd());
    booking.setStart(bookingDtoTime.getStart());
    booking.setItem(item);
    booking.setBooker(user);
    booking.setStatus(Status.WAITING);
    return booking;
    }

    public static BookingDtoTime bookingDtoTime(Booking booking, UserDto userDto, ItemDto itemDto) {
        BookingDtoTime bookingDtoTime = new BookingDtoTime();
        bookingDtoTime.setBooker(userDto);
        bookingDtoTime.setStatus(booking.getStatus());
        bookingDtoTime.setEnd(booking.getEnd());
        bookingDtoTime.setStart(booking.getStart());
        bookingDtoTime.setItem(itemDto);
        bookingDtoTime.setId(booking.getId());
    }
}
