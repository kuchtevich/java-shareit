package src.main.java.ru.practicum.shareit.booking.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import ru.practicum.shareit.booking.ValidateTime;


import java.time.LocalDateTime;

@Data
@ValidateTime
public class BookingDto {
    @NotBlank(message = "Указать дату начала бронирования")
    @Future(message = "Дата должна быть указана в будущем времени")
    private LocalDateTime start;
    @NotBlank(message = "Указать дату окончания бронирования")
    @Future(message = "Дата должна быть указана в будущем времени")
    private LocalDateTime end;
    @NotBlank(message = "Не может быть пустым")
    private Long itemId;
}
