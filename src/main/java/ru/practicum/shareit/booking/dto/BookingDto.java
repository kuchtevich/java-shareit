package ru.practicum.shareit.booking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.user.dto.UserDto;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BookingDto {
    @NotNull(message = "Указать дату начала бронирования")
    private LocalDateTime start;
    @NotNull(message = "Указать дату окончания бронирования")
    private LocalDateTime end;
    private Long itemId;
}
