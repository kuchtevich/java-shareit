package ru.practicum.shareit.booking.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import ru.practicum.shareit.item.model.Item;

import java.time.LocalDate;

public class BookingDto {
    @NotNull(message = "Указать id.")
    private long id;
    @NotNull(message = "Указать дату начала бронирования")
    private LocalDate start;
    @NotNull(message = "Указать дату окончания бронирования")
    private LocalDate end;
}
