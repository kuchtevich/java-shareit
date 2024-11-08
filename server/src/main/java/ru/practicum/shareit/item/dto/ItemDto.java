package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ItemDto {
        private Long id;
        @NotBlank(message = "Указать имя.")
        private String name;
        @NotBlank(message = "Указать подробное описание вещи")
        private String description;
        @NotNull(message = "Указать статус вещи")
        private Boolean available;
        private BookingDtoTime lastBooking;
        private BookingDtoTime nextBooking;
}
