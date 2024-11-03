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
    private final UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userRepository.findAll().contains(userMapper.toUser(userDto))) {
            throw new DuplicatedDataException("Этот пользователь уже существует.");
        }
        final User user = userRepository.save(userMapper.toUser(userDto));
        return userMapper.toUserDto(user);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDtoUpdate) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Пользователя нет"));
        if (userDtoUpdate.getName() != null) {
            user.setName(userDtoUpdate.getName());
        }
        if (userDtoUpdate.getEmail() != null) {
            user.setEmail(userDtoUpdate.getEmail());
        }
        return UserMapper.toUserDto(update);
    }

    @Override
    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Пользователя нет")
        );
        return UserMapper.toUserDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователя нет"));
        userRepository.delete(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toListDto(users);
    }
}
