package ru.practicum.shareit.request.dto;

import jakarta.validation.constraints.NotBlank;

public class ItemRequestsDto {
    @NotBlank
    private String description;

}
