package ru.practicum.shareit.booking.dto;


import lombok.Data;
import ru.practicum.shareit.booking.ValidateTime;


import java.time.LocalDateTime;

@Data
@ValidateTime
public class BookingDto {
    private LocalDateTime start;
    private LocalDateTime end;
    private Long itemId;
}
