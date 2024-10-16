package ru.practicum.shareit.request.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(exclude = {"id"})
public class ItemRequest {
    @NotNull(message = "Указать id")
    private long id;
    @NotNull(message = "Указать описание")
    private  String  description;
    private User requestor; //пользователь создавший запрос
    @NotNull(message = "Указать время")
    private LocalDate created;
}
