package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.error.NotFoundException;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.error.DuplicatedDataException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto createUser(final UserDto userDto) {
        if (userRepository.findAll().contains(userMapper.toUser(userDto))) {
            log.warn("Пользователь с id {} уже добавлен.", userDto.getId());
            throw new DuplicatedDataException("Этот пользователь уже существует.");
        }
        final User user = userRepository.save(userMapper.toUser(userDto));
        return userMapper.toUserDto(user);
    }

    @Override
    public UserDto updateUser(final long userId, final UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException("Пользователя нет"));
        if (userDto.getName() != null) {
            user.setName(userDto.getName());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        final User updateUser = userRepository.save(user);
        return userMapper.toUserDto(updateUser);
    }

    @Override
    public UserDto getUserById(final long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException("Пользователя нет")
        );
        log.info("Получение пользователя по id.");
        return userMapper.toUserDto(user);
    }

    @Override
    public void deleteUser(final Long userId) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователя нет"));
        userRepository.delete(user);
        log.info("Пользователь {} удален.", userId);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toListDto(users);
    }
}
