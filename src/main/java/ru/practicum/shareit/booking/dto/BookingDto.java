package ru.practicum.shareit.booking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class BookingDto {
    @NotNull(message = "Указать дату начала бронирования")
    private LocalDateTime start;
    @NotNull(message = "Указать дату окончания бронирования")
    private LocalDateTime end;
    private Long itemId;
}
