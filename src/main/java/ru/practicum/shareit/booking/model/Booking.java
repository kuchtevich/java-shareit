package ru.practicum.shareit.booking.model;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.item.model.Item;

import jdk.jshell.Snippet;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(exclude = {"id"})
public class Booking {
    @NotNull(message = "Указать id.")
    private long id;
    @NotNull(message = "Указать дату начала бронированя.")
    private LocalDate start;
    @NotNull(message = "Указать дату конца бронированя.")
    private LocalDate end;
    private Item item; //вещь которую бронируют
    private User booker; //пользователь который забронировал
    private Snippet.Status status; //WAITING, APPROVED, REJECTED, CANCELED
}
