package ru.practicum.shareit.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.validation.annotation.Validated;

@Data
public class UserDto {
    private Long id;
    @NotBlank(message = "Необходимо указать имя пользователя.")
    private String name;
    @Email
    @NotNull(message = "Необходимо указать почтовый адресс.")
    private String email;

}
