package ru.practicum.shareit.user.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = {"id"})
public class User {
    private long id;
    @NotBlank(message = "Необходимо указать имя пользователя.")
    private String name;
    @NotNull(message = "Необходимо указать почтовый адресс.")
    @Email(message = "Почта должна иметь символ @")
    private String email;
}
