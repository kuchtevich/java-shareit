package ru.practicum.shareit.user.repository;

import ru.practicum.shareit.user.model.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository {
    List<User> getAll();

    User create(User user);

    User update(Long id,User user);

    Optional<User> get(Long id);

    void delete(Long id);
}
