package ru.practicum.shareit.request.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.client.*;
import ru.practicum.shareit.request.dto.ItemRequestsDto;

@RestController
@RequestMapping(path = "/requests")
@RequiredArgsConstructor
@Validated
public class RequestController {
    private final ClientRequest clientRequest;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> itemRequestCreate(@RequestHeader("X-Sharer-User-Id") @NotNull final long userId,
                                                    @RequestBody final ItemRequestsDto itemRequestsDto) {
        return clientRequest.itemRequestCreate(userId, itemRequestsDto);
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
    public ResponseEntity<Object> getById(@PathVariable final long requestId) {
        return clientRequest.getById(requestId);
    }
}
