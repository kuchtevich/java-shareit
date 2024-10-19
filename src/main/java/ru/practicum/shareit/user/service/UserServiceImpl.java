package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.error.NotFoundException;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.getAll().stream().map(UserMapper::toUserDto).toList();
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.toUser(userDto);
        return UserMapper.toUserDto(userRepository.create(user));
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        userRepository.get(userId).orElseThrow(() ->
                new NotFoundException("Пользователя нет"));
        User user = UserMapper.toUser(userDto);
        return UserMapper.toUserDto(userRepository.update(userId, user));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.delete(userId);
    }

    @Override
    public UserDto getUser(Long userId) {
        User user = userRepository.get(userId).orElseThrow(() -> new NotFoundException("Пользователя нет"));
        return UserMapper.toUserDto(user);
    }
}
