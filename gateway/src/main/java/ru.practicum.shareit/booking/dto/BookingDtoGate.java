package ru.practicum.shareit.booking.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class BookingDtoGate {
    @NotBlank(message = "Указать дату начала бронирования")
    @Future(message = "Дата должна быть указана в будущем времени")
    private LocalDateTime start;
    @NotBlank(message = "Указать дату окончания бронирования")
    @Future(message = "Дата должна быть указана в будущем времени")
    private LocalDateTime end;
    @NotBlank(message = "Не может быть пустым")
    private Long itemId;
}
