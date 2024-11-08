package src.main.java.ru.practicum.shareit.request.dto;

import jakarta.validation.constraints.NotBlank;

public class ItemRequestDto {
    @NotBlank
    private String description;

}
