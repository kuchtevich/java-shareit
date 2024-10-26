package ru.practicum.shareit.booking.repository;

import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.List;

public class BookingRepository {
    List<Booking> findBookingsByItemOwnerIsAndStartBeforeAndEndAfterOrderByStartDesc(User owner, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<Booking> findBookingsByItemOwnerIsAndStartBeforeAndEndAfterOrderByStartDesc(User owner, LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable pageable);

    List<Booking> findBookingsByItemOwnerAndStartAfterOrderByStartDesc(User owner, LocalDateTime localDateTime);

    List<Booking> findBookingsByItemOwnerAndStartAfterOrderByStartDesc(User owner, LocalDateTime localDateTime, Pageable pageable);

    List<Booking> findBookingsByItemOwnerAndEndBeforeOrderByStartDesc(User owner, LocalDateTime localDateTime);

    List<Booking> findBookingsByItemOwnerAndEndBeforeOrderByStartDesc(User owner, LocalDateTime localDateTime, Pageable pageable);

    List<Booking> findBookingsByItemOwnerIsAndStatusIsOrderByStartDesc(User owner, BookingState bookingState);

    List<Booking> findBookingsByItemOwnerIsAndStatusIsOrderByStartDesc(User owner, BookingState bookingState, Pageable pageable);

    List<Booking> findBookingsByItem_IdAndItem_Owner_IdIsOrderByStart(Long itemId, Long userId);

    List<Booking> findBookingsByItemOwnerIsOrderByStartDesc(User owner, Pageable pageable);

    List<Booking> findBookingsByItemOwnerIsOrderByStartDesc(User owner);

    //for booker
    List<Booking> findBookingsByBookerIsAndStartBeforeAndEndAfterOrderByStartDesc(User booker, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<Booking> findBookingsByBookerIsAndStartBeforeAndEndAfterOrderByStartDesc(User booker, LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable pageable);

    List<Booking> findBookingsByBookerIsAndStartIsAfterOrderByStartDesc(User booker, LocalDateTime localDateTime);

    List<Booking> findBookingsByBookerIsAndStartIsAfterOrderByStartDesc(User booker, LocalDateTime localDateTime, Pageable pageable);

    List<Booking> findBookingsByBookerIsAndEndBeforeOrderByStartDesc(User booker, LocalDateTime localDateTime);

    List<Booking> findBookingsByBookerIsAndEndBeforeOrderByStartDesc(User booker, LocalDateTime localDateTime, Pageable pageable);

    List<Booking> findBookingsByItem_IdIsAndStatusIsAndEndIsAfter(Long itemId, BookingState bookingState, LocalDateTime localDateTime);

    List<Booking> findBookingsByBookerIsAndStatusIsOrderByStartDesc(User booker, BookingState bookingState);

    List<Booking> findBookingsByBookerIsAndStatusIsOrderByStartDesc(User booker, BookingState bookingState, Pageable pageable);

    List<Booking> findBookingsByBookerIsOrderByStartDesc(User booker, Pageable pageable);

    List<Booking> findBookingsByBookerIsOrderByStartDesc(User booker);
}
