package ru.practicum.shareit.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.service.UserService;


@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }



@PostMapping()
@ResponseStatus(HttpStatus.CREATED)
public UserDto createUser(@Valid @RequestBody final UserDto userDto) {
    return userService.createUser(userDto);
}

@PatchMapping("/{userId}")
public UserDto userUpdate(@PathVariable @Positive final long userId, @RequestBody UserDto userDto) {
    return userService.updateUser(userId, userDto);
}


@DeleteMapping("/{userId}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void deleteUser(@PathVariable @Positive final Long userId) {
    userService.deleteUser(userId);
}
}
