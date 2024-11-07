package ru.practicum.shareit.user.service;
import ru.practicum.shareit.user.dto.UserDto;
import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto createUser(final UserDto userDto);

    UserDto updateUser(final long userId, final UserDto userDto);

    void deleteUser(final Long userId);

    UserDto getUserById(final long userId); //получение пользователя по id
}
