package ru.practicum.shareit.user.service;
import ru.practicum.shareit.user.dto.UserDto;
import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto createUser(final UserDto usersDto);

    UserDto updateUser(final long userId, final UserDto usersDto);

    void deleteUser(final Long userId);

    UserDto getUserById(final long userId); //получение пользователя по id
}
