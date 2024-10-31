package ru.practicum.shareit.booking.dto;
import lombok.Data;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.item.dto.ItemDto;

import java.time.LocalDateTime;

@Data
public class BookingDtoTime {
    private Long id;
    private Long booker;
    private Long item;
    private LocalDateTime start;
    private LocalDateTime end;
    private Status status;
}
