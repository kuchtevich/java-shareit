package ru.practicum.shareit.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = {"id"})
public class UserDto {
    private long id;
    @NotBlank(message = "Необходимо указать имя пользователя.")
    private String name;
    @NotNull(message = "Необходимо указать почтовый адресс.")
    private String email;
}
