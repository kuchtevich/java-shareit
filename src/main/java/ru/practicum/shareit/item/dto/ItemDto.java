package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.validation.annotation.Validated;


@Data
@EqualsAndHashCode(exclude = {"id"})
@Validated
public class ItemDto {
        private Long id;
        @NotNull
        @NotBlank
        private String name;
        @NotNull(message = "Указать подробное описание вещи")
        private String description; //подробное описание вещи
        @NotNull(message = "Указать статус вещи")
        private Boolean available;
}
