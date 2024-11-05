package ru.practicum.shareit.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    @NotBlank(message = "Необходимо указать имя пользователя.")
    private String name;
    @Email
    @NotNull(message = "Необходимо указать почтовый адресс.")
    private String email;

}
