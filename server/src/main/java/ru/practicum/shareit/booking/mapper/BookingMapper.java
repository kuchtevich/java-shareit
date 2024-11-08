package ru.practicum.shareit.booking.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.item.dto.ItemDto;

@Component
public class BookingMapper {
    public Booking toBooking(final BookingDto bookingDto, final User user, final Item item) {
        Booking booking = new Booking();

        booking.setStart(bookingDto.getStart());
        booking.setEnd(bookingDto.getEnd());
        booking.setItem(item);
        booking.setBooker(user);
        booking.setStatus(Status.WAITING);
        return booking;
    }

    public BookingDtoTime toBookingDtoTime(final Booking booking, final UserDto userDto, final ItemDto itemDto) {
        final BookingDtoTime bookingDtoTime = new BookingDtoTime();

        bookingDtoTime.setId(booking.getId());
        bookingDtoTime.setBooker(userDto);
        bookingDtoTime.setItem(itemDto);
        bookingDtoTime.setStart(booking.getStart());
        bookingDtoTime.setEnd(booking.getEnd());
        bookingDtoTime.setStatus(booking.getStatus());
        return bookingDtoTime;
    }
}
