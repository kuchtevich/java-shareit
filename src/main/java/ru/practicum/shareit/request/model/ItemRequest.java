package ru.practicum.shareit.request.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(exclude = {"id"})
public class ItemRequest {
    @NotNull(message = "Указать id")
    @Column(name = "request_id")
    private long id;
    @NotNull(message = "Указать описание")
    @Column(name = "description")
    private  String  description;
    @Column(name = "requestor_id")
    private User requestor; //пользователь создавший запрос
    @Column(name = "created")
    @NotNull(message = "Указать время")
    private LocalDate created;
}
