package ru.practicum.shareit.booking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.practicum.shareit.booking.ValidateTime;


import java.time.LocalDateTime;

@Data
@ValidateTime
public class BookingDto {
    @NotNull(message = "Указать дату начала бронирования")
    private LocalDateTime start;
    @NotNull(message = "Указать дату окончания бронирования")
    private LocalDateTime end;
    private Long itemId;
}
