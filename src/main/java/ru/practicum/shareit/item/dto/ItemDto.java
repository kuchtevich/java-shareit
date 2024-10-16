package ru.practicum.shareit.item.dto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.practicum.shareit.request.model.ItemRequest;

@Data
@EqualsAndHashCode(exclude = {"id"})
public class ItemDto {
        @NotNull(message = "Указать id")
        private long id;
        @NotNull(message = "Указать имя пользователя")
        private String name;
        @NotNull(message = "Указать подробное описание вещи")
        private String description; //подробное описание вещи
        @NotNull(message = "Указать статус вещи")
        private Boolean available; //статус
        private ItemRequest request; //ссылка на соотв. запрос
}
