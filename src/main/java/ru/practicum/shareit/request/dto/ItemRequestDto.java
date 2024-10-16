package ru.practicum.shareit.request.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.practicum.shareit.request.model.ItemRequest;

@Data
@EqualsAndHashCode(exclude = {"id"})
public class ItemRequestDto {
    @NotNull(message = "Указать id")
    private long id;
    @NotNull(message = "Указать имя пользователя")
    private String name;
    @NotNull(message = "Указать подробное описание вещи")
    private String description;
    @NotNull(message = "Указать статус вещи")
    private Boolean available;
    private ItemRequest request;
}
