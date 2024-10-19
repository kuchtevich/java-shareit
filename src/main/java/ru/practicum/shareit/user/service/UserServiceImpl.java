package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
        return userRepository.findAll();
    }

@Override
    public UserDto createUser(UserDto userDto) {
    User user = UserMapper.toUser(userDto);
    return UserMapper.toUserDto(userRepository.save(user));
}

@Override
public UserDto updateUser(long userId, UserDto userDto) {
    User user = userRepository.findById(id).orElseThrow(() ->
            new NotFoundException("Пользователя нет"));
    if (userDtoUpdate.getName() != null) {
        user.setName(userDtoUpdate.getName());
    }
    if (userDtoUpdate.getEmail() != null) {
        user.setEmail(userDtoUpdate.getEmail());
    }
    return UserMapper.toUserDto(userRepository.save(user));
}

@Override
public void deleteUser(Long userId) {
    userRepository.deleteById(id);
}
}
