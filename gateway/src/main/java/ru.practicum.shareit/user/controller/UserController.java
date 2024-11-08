package src.main.java.ru.practicum.shareit.user.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import src.main.java.ru.practicum.shareit.user.controller.Client;
import src.main.java.ru.practicum.shareit.user.dto.UserDto;


@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final Client client;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        return client.getAllUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable @Positive final long userId) {
        return client.getUserById(userId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> userCreate(@Valid @RequestBody final UserDto userDto) {
        return client.create(userDto);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Object> update(@PathVariable @Positive final long userId, @RequestBody final UserDto userDto) {
        return client.update(userId, userDto);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Positive final Long userId) {
        client.delete(userId);
    }

}
