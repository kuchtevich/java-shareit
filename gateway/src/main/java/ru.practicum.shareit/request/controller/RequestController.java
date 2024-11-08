package src.main.java.ru.practicum.shareit.request.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.main.java.ru.practicum.shareit.request.client.ClientRequest;
import src.main.java.ru.practicum.shareit.request.dto.ItemRequestDto;

@RestController
@RequestMapping(path = "/requests")
@RequiredArgsConstructor
@Validated
public class RequestController {
    private final ClientRequest clientRequest;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> itemRequestCreate(@RequestHeader("X-Sharer-User-Id") @NotNull final long userId,
                                                    @RequestBody final ItemRequestDto itemRequestDto) {
        return clientRequest.itemRequestCreate(userId, itemRequestDto);
    }

    @GetMapping
    public ResponseEntity<Object> getAllRequestByUser(@RequestHeader("X-Sharer-User-Id") final long userId) {
        return clientRequest.getAllRequestByUser(userId);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllRequests(@RequestHeader("X-Sharer-User-Id") final long userId) {
        return clientRequest.getAllRequests(userId);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<Object> getRequestById(@PathVariable final long requestId) {
        return clientRequest.getRequestById(requestId);
    }
}
