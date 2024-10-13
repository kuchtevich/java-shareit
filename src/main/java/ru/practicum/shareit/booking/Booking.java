package ru.practicum.shareit.booking;

import java.time.LocalDate;

/**
 * TODO Sprint add-bookings.
 */
public class Booking {
    private long id;
    private LocalDate start;
    private LocalDate end;
    private String item; //вещь которую бронируют
    private String booker; //пользователь коорый забронировал
    private String status; //WAITING, APPROVED, REJECTED, CANCELED
}
