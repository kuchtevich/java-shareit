package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDtoItem {
    private Long id;
    @NotBlank(message = "Указать текст")
    private String text;
    private String authorName;
    private LocalDateTime created;
}
